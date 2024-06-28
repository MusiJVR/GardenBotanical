package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.ColorizerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.InventorySyncPacket;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.gardenbotanical.util.Utils;
import net.gardenbotanical.util.interfaces.InventoryInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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


public class ColorizerBlockEntity extends BlockEntity implements GeoBlockEntity, InventoryInterface {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    public static final int INPUT_SLOT_ITEM = 0;
    public static final int INPUT_SLOT_DYE = 1;
    public static final int OUTPUT_SLOT_ITEM = 2;

    private int progress = 0;
    private int maxProgress = 167;

    public ColorizerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // INVENTORY METHODS
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

    // NBT
    @Override
    public void writeNbt(NbtCompound nbt) {
        InventoryInterface.super.writeNbt(nbt);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        InventoryInterface.super.readNbt(nbt);
        progress = nbt.getInt("progress");
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

    // RECIPES
    private boolean hasRecipe() {
        return getStack(INPUT_SLOT_ITEM).isIn(GardenBotanicalTags.COLORIZER_ITEM_TYPES) && getStack(INPUT_SLOT_DYE).isOf(GardenBotanicalItems.DYE) && slotIsEmpty(OUTPUT_SLOT_ITEM);
    }

    private void craftArmor() {
        int outputColorItem;
        if (Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_ITEM), GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR) != GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR) {
            outputColorItem = Utils.blendColors(Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_ITEM), GardenBotanical.DEFAULT_LEATHER_ARMOR_COLOR), Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_DYE), GardenBotanical.DEFAULT_DYE_COLOR));
        } else {
            outputColorItem = Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_DYE), GardenBotanical.DEFAULT_DYE_COLOR);
        }

        ItemStack outputItem = getStack(INPUT_SLOT_ITEM).copy();
        removeStack(INPUT_SLOT_ITEM);
        removeStack(INPUT_SLOT_DYE);

        NbtCompound nbtOutputItem = outputItem.getOrCreateSubNbt("display");
        nbtOutputItem.putInt("color", outputColorItem);

        setStack(OUTPUT_SLOT_ITEM, outputItem);
    }

    // PROGRESS
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

    // RENDER
    public ItemStack getItemRender() {
        if (!slotIsEmpty(INPUT_SLOT_ITEM)) {
            return getStack(INPUT_SLOT_ITEM);
        } else if (!slotIsEmpty(OUTPUT_SLOT_ITEM)) {
            return getStack(OUTPUT_SLOT_ITEM);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean renderFluid() {
        return !slotIsEmpty(INPUT_SLOT_DYE);
    }

    public int getFluidColor() {
        return Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_DYE), GardenBotanical.DEFAULT_DYE_COLOR);
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
}
