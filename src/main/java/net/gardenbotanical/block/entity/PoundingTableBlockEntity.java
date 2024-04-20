package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.recipe.PoundingTableRecipe;
import net.gardenbotanical.screen.PoundingTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
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

    public ItemStack getRenderStack() {
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

    @Override
    public void markDirty() {
        if (!world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for (int i = 0; i < inventory.size(); i++) {
                data.writeItemStack(inventory.get(i));
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                GardenBotanicalNetwork.ITEM_SYNC_PACKET.send(player, data);
            }
        }

        super.markDirty();
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
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("pounding_table.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("pounding_table.progress");
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
        if (world.isClient()) {
            return;
        }

        if (this.isFuelSlotFlint(INPUT_SLOT_FLINT) && fuel <= 0) {
            this.decreaseStackFuel(INPUT_SLOT_FLINT);
            markDirty(world, pos, state);
        }

        if (this.isOutputSlotEmptyOrReceivable(OUTPUT_SLOT_POWDER) && fuel > 0) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.decreaseFuel();
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT_PETAL, 1);

        putItemInOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_POWDER);
    }

    private void putItemInOutputSlot(ItemStack result, int slot) {
        this.setStack(slot, new ItemStack(result.getItem(), getStack(slot).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void decreaseStackFuel(int slot) {
        fuel = 5;
        inventory.get(slot).decrement(1);
    }

    private void decreaseFuel() {
        fuel--;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<PoundingTableRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent()
                && canInsertAmountIntoOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_POWDER)
                && canInsertItemIntoOutputSlot(recipe.get().getOutput(null).getItem(), OUTPUT_SLOT_POWDER);
    }

    private Optional<PoundingTableRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(PoundingTableRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item, int slot) {
        return this.getStack(slot).getItem() == item || this.getStack(slot).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result, int slot) {
        return this.getStack(slot).getCount() + result.getCount() <= getStack(slot).getMaxCount();
    }

    private boolean isFuelSlotFlint(int slot) {
        return inventory.get(slot).isOf(Items.FLINT);
    }

    private boolean isOutputSlotEmptyOrReceivable(int slot) {
        return this.getStack(slot).isEmpty() || this.getStack(slot).getCount() < this.getStack(slot).getMaxCount();
    }
}