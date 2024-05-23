package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.ColorizerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.ColorizerSyncPacket;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.gardenbotanical.util.interfaces.ImplementedInventory;
import net.gardenbotanical.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;


public class ColorizerBlockEntity extends BlockEntity implements GeoBlockEntity, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final int INPUT_SLOT_ITEM = 0;
    public static final int INPUT_SLOT_DYE = 1;
    public static final int OUTPUT_SLOT_ITEM = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 167;

    public ColorizerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ColorizerBlockEntity.this.progress;
                    case 1 -> ColorizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ColorizerBlockEntity.this.progress = value;
                    case 1 -> ColorizerBlockEntity.this.maxProgress = value;
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

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    public ItemStack getOutputItem() {
        return inventory.get(OUTPUT_SLOT_ITEM);
    }

    public ItemStack getInputItem() {
        return inventory.get(INPUT_SLOT_ITEM);
    }

    public ItemStack getInputDye() {
        return inventory.get(INPUT_SLOT_DYE);
    }

    public void clearOutputSlot() {
        inventory.set(OUTPUT_SLOT_ITEM, ItemStack.EMPTY);
    }

    public boolean slotIsEmpty(int slot) {
        return inventory.get(slot).isEmpty();
    }

    private void setProcessState(BlockState state, boolean value) {
        this.progress++;
        if (world != null) {
            world.setBlockState(pos, state.with(ColorizerBlock.PROCESS, value));
        }
    }

    private void resetProgress(BlockState state) {
        setProcessState(state, false);
        this.progress = 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
    }

    public ItemStack getItemRender() {
        if (!slotIsEmpty(INPUT_SLOT_ITEM)) {
            return getInputItem();
        } else if (!slotIsEmpty(OUTPUT_SLOT_ITEM)) {
            return getOutputItem();
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean renderFluid() {
        return !slotIsEmpty(INPUT_SLOT_DYE);
    }

    public int getFluidColor() {
        return Utils.checkDisplayColorNbt(getInputDye(), GardenBotanical.DEFAULT_DYE_COLOR);
    }

    public void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.COLORIZER_SYNC_PACKET.send(player, ColorizerSyncPacket.write(inventory, getPos()));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        if (!slotIsEmpty(INPUT_SLOT_ITEM) && !slotIsEmpty(INPUT_SLOT_DYE)) {
            if (hasRecipe()) {
                setProcessState(state, true);
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    craftArmor();
                    resetProgress(state);
                }
            } else {
                resetProgress(state);
            }
        } else {
            resetProgress(state);
            markDirty(world, pos, state);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (state.getAnimatable().world != null) {
                BlockState blockState = state.getAnimatable().world.getBlockState(state.getAnimatable().pos);
                if (blockState.isOf(GardenBotanicalBlocks.COLORIZER))
                    if (blockState.get(ColorizerBlock.PROCESS)) {
                        state.setAnimation(RawAnimation.begin().then("process", Animation.LoopType.LOOP));
                        return PlayState.CONTINUE;
                    }
            }
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private boolean hasRecipe() {
        return getInputItem().isIn(GardenBotanicalTags.COLORIZER_ITEM_TYPES) && getInputDye().isOf(GardenBotanicalItems.DYE) && slotIsEmpty(OUTPUT_SLOT_ITEM);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && slot == OUTPUT_SLOT_ITEM;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {OUTPUT_SLOT_ITEM};
        } else {
            return new int[] {};
        }
    }

    private void craftArmor() {
        int outputColorItem;
        if (Utils.checkDisplayColorNbt(getInputItem(), GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR) != GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR) {
            outputColorItem = Utils.blendColors(Utils.checkDisplayColorNbt(getInputItem(), GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR), Utils.checkDisplayColorNbt(getInputDye(), GardenBotanical.DEFAULT_DYE_COLOR));
        } else {
            outputColorItem = Utils.checkDisplayColorNbt(getInputDye(), GardenBotanical.DEFAULT_DYE_COLOR);
        }

        ItemStack outputItem = getInputItem().copy();
        this.removeStack(INPUT_SLOT_ITEM);
        this.removeStack(INPUT_SLOT_DYE);

        NbtCompound nbtOutputItem = outputItem.getOrCreateSubNbt("display");
        nbtOutputItem.putInt("color", outputColorItem);

        putItemInOutputSlot(outputItem, OUTPUT_SLOT_ITEM);
    }

    private void putItemInOutputSlot(ItemStack itemStack, int slot) {
        this.setStack(slot, itemStack);
    }
}