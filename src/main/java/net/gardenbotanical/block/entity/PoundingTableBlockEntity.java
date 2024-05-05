package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.PoundingTableSyncPacket;
import net.gardenbotanical.recipe.PoundingTableRecipe;
import net.gardenbotanical.screen.PoundingTableScreenHandler;
import net.gardenbotanical.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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


public class PoundingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT_FLINT = 0;
    private static final int INPUT_SLOT_PETAL = 1;
    private static final int OUTPUT_SLOT_POWDER = 2;

    protected final PropertyDelegate propertyDelegate;
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

    public ItemStack getItemStackRender() {
        if (this.getStack(OUTPUT_SLOT_POWDER).isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            return this.getStack(OUTPUT_SLOT_POWDER);
        }
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    public void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.PT_SYNC_PACKET.send(player, PoundingTableSyncPacket.write(inventory, getPos()));
        }
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
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
        nbt.putInt("fuel", fuel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
        fuel = nbt.getInt("fuel");
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.gardenbotanical.pounding_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PoundingTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        if (isFuelSlotFlint(INPUT_SLOT_FLINT) && fuel <= 0) {
            decreaseStackFuel(INPUT_SLOT_FLINT);
            markDirty(world, pos, state);
        }

        if (isOutputSlotEmptyOrReceivable(OUTPUT_SLOT_POWDER) && fuel > 0) {
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

    private void craftItem() {
        fuel--;

        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT_PETAL, 1);

        putItemInOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_POWDER);
    }

    private void putItemInOutputSlot(ItemStack result, int slot) {
        this.setStack(slot, new ItemStack(result.getItem(), getStack(slot).getCount() + result.getCount()));
    }

    private void decreaseStackFuel(int slot) {
        fuel = 5;
        inventory.get(slot).decrement(1);
    }

    private boolean hasRecipe() {
        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertItemIntoOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_POWDER);
    }

    private Optional<PoundingTableRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(PoundingTableRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack itemStack, int slot) {
        return (this.getStack(slot).getItem() == itemStack.getItem() || this.getStack(slot).isEmpty())
                && (this.getStack(slot).getCount() + itemStack.getCount() <= getStack(slot).getMaxCount());
    }

    private boolean isFuelSlotFlint(int slot) {
        return inventory.get(slot).isOf(Items.FLINT);
    }

    private boolean isOutputSlotEmptyOrReceivable(int slot) {
        return this.getStack(slot).isEmpty() || this.getStack(slot).getCount() < this.getStack(slot).getMaxCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}