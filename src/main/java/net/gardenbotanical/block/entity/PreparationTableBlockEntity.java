package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gardenbotanical.recipe.PreparationTableRecipe;
import net.gardenbotanical.screen.PreparationTableScreenHandler;
import net.gardenbotanical.util.Utils;
import net.gardenbotanical.util.interfaces.InventoryInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class PreparationTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, InventoryInterface {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT_PETAL = 1;
    private static final int OUTPUT_SLOT_SEEDS = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public PreparationTableBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.PREPARATION_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PreparationTableBlockEntity.this.progress;
                    case 1 -> PreparationTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PreparationTableBlockEntity.this.progress = value;
                    case 1 -> PreparationTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // INVENTORY METHODS
    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {OUTPUT_SLOT_PETAL, OUTPUT_SLOT_SEEDS};
        } else if (side == Direction.UP) {
            return new int[] {INPUT_SLOT};
        } else {
            return new int[] {};
        }
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && slot != INPUT_SLOT;
    }

    // NBT
    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
    }

    // MAIN
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        if (canSlotReceive(OUTPUT_SLOT_PETAL) && canSlotReceive(OUTPUT_SLOT_SEEDS)) {
            if (hasRecipe()) {
                progress++;
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    // RECIPES
    private void craftItem() {
        Optional<PreparationTableRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT, 1);

        putStack(OUTPUT_SLOT_PETAL, Utils.applyBonus(recipe.get().getOutputPetals(), recipe.get().getOutputPetalBonus()));
        putStack(OUTPUT_SLOT_SEEDS, Utils.applyBonus(recipe.get().getOutputSeeds(), recipe.get().getOutputSeedsBonus()));
    }

    private boolean hasRecipe() {
        Optional<PreparationTableRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() &&
                canInsertItem(recipe.get().getOutputPetals(), OUTPUT_SLOT_PETAL) && canInsertItem(recipe.get().getOutputSeeds(), OUTPUT_SLOT_SEEDS);
    }

    private Optional<PreparationTableRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(PreparationTableRecipe.Type.INSTANCE, inv, getWorld());
    }

    // PROGRESS
    private void resetProgress() {
        this.progress = 0;
    }

    // SCREEN
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PreparationTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.gardenbotanical.preparation_table");
    }
}
