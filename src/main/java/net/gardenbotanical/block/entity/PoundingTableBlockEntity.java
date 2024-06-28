package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.InventorySyncPacket;
import net.gardenbotanical.recipe.PoundingTableRecipe;
import net.gardenbotanical.screen.PoundingTableScreenHandler;
import net.gardenbotanical.util.interfaces.InventoryInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class PoundingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, InventoryInterface {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT_FLINT = 0;
    private static final int INPUT_SLOT_PETAL = 1;
    private static final int OUTPUT_SLOT_POWDER = 2;

    private final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int fuel = 0;

    public PoundingTableBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.POUNDING_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PoundingTableBlockEntity.this.progress;
                    case 1 -> PoundingTableBlockEntity.this.maxProgress;
                    case 2 -> PoundingTableBlockEntity.this.fuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PoundingTableBlockEntity.this.progress = value;
                    case 1 -> PoundingTableBlockEntity.this.maxProgress = value;
                    case 2 -> PoundingTableBlockEntity.this.fuel = value;
                }
            }

            @Override
            public int size() {
                return 3;
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
            return new int[] {OUTPUT_SLOT_POWDER};
        } else {
            return side == Direction.UP ? new int[] {INPUT_SLOT_PETAL} : new int[] {INPUT_SLOT_FLINT};
        }
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot == INPUT_SLOT_FLINT) {
            return stack.isOf(Items.FLINT);
        }
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && slot == OUTPUT_SLOT_POWDER;
    }

    // NBT
    @Override
    public void writeNbt(NbtCompound nbt) {
        InventoryInterface.super.writeNbt(nbt);
        nbt.putInt("progress", progress);
        nbt.putInt("fuel", fuel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        InventoryInterface.super.readNbt(nbt);
        progress = nbt.getInt("progress");
        fuel = nbt.getInt("fuel");
    }

    // MAIN
    public void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.INVENTORY_SYNC_PACKET.send(player, InventorySyncPacket.write(inventory, getPos()));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        if (getStack(INPUT_SLOT_FLINT).isOf(Items.FLINT) && fuel <= 0) {
            fuel = 5;
            getStack(INPUT_SLOT_FLINT).decrement(1);
            markDirty(world, pos, state);
        }

        if (canSlotReceive(OUTPUT_SLOT_POWDER) && fuel > 0) {
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
        fuel--;
        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();
        removeStack(INPUT_SLOT_PETAL, 1);
        putStack(OUTPUT_SLOT_POWDER, recipe.get().getOutput(null));
    }

    private boolean hasRecipe() {
        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertItem(recipe.get().getOutput(null), OUTPUT_SLOT_POWDER);
    }

    private Optional<PoundingTableRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(PoundingTableRecipe.Type.INSTANCE, inv, getWorld());
    }

    // PROGRESS
    private void resetProgress() {
        this.progress = 0;
    }

    // SCREEN
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PoundingTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        return Text.translatable("block.gardenbotanical.pounding_table");
    }

    // RENDER
    public ItemStack getItemStackRender() {
        if (getStack(OUTPUT_SLOT_POWDER).isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            return getStack(OUTPUT_SLOT_POWDER);
        }
    }
}
