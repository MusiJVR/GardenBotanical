package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.DyeMixerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.FluidStorageSyncPacket;
import net.gardenbotanical.network.packet.S2C.InventorySyncPacket;
import net.gardenbotanical.recipe.DyeMixerRecipe;
import net.gardenbotanical.util.FluidStack;
import net.gardenbotanical.util.Utils;
import net.gardenbotanical.util.interfaces.FluidStorageInterface;
import net.gardenbotanical.util.interfaces.InventoryInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Optional;


public class DyeMixerBlockEntity extends BlockEntity implements GeoBlockEntity, InventoryInterface, FluidStorageInterface {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FluidStack.convertDropletsToMb(FluidConstants.BUCKET);
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
        }

        @Override
        protected boolean canInsert(FluidVariant variant) {
            return variant.isOf(Fluids.WATER);
        }

        @Override
        protected boolean canExtract(FluidVariant variant) {
            return variant.isOf(Fluids.WATER);
        }
    };

    public static final int INPUT_SLOT_POWDER = 0;
    public static final int INPUT_SLOT_FIRST_DYE = 1;
    public static final int INPUT_SLOT_SECOND_DYE = 2;
    public static final int OUTPUT_SLOT_DYE = 3;

    private int progress = 0;
    private int maxProgress = 100;

    public DyeMixerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public SingleVariantStorage<FluidVariant> getFluidStorage() {
        return fluidStorage;
    }

    // INVENTORY METHODS
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && slot == OUTPUT_SLOT_DYE;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {OUTPUT_SLOT_DYE};
        } else {
            return new int[] {};
        }
    }

    // NBT
    @Override
    public void writeNbt(NbtCompound nbt) {
        InventoryInterface.super.writeNbt(nbt);
        FluidStorageInterface.super.writeNbt(nbt);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        InventoryInterface.super.readNbt(nbt);
        FluidStorageInterface.super.readNbt(nbt);
        progress = nbt.getInt("progress");
    }

    // MAIN
    private void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.INVENTORY_SYNC_PACKET.send(player, InventorySyncPacket.write(inventory, getPos()));
            GardenBotanicalNetwork.FLUID_STORAGE_SYNC_PACKET.send(player, FluidStorageSyncPacket.write(fluidStorage, getPos()));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        if (fluidIsFull()) {
            if (hasRecipe()) {
                setProcessState(state, true);
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    extractFluid();
                    craftItem();
                    resetProgress(state);
                }
            } else if (hasDyeRecipe()) {
                setProcessState(state, true);
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    extractFluid();
                    mixDyes();
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
    private void craftItem() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();
        removeStack(INPUT_SLOT_POWDER);
        setStack(OUTPUT_SLOT_DYE, recipe.get().getOutput(null));
    }

    private void mixDyes() {
        int outputColorDye = Utils.blendColors(
                Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_FIRST_DYE), GardenBotanical.DEFAULT_DYE_COLOR),
                Utils.checkDisplayColorNbt(getStack(INPUT_SLOT_SECOND_DYE), GardenBotanical.DEFAULT_DYE_COLOR)
        );

        removeStack(INPUT_SLOT_FIRST_DYE);
        removeStack(INPUT_SLOT_SECOND_DYE);

        ItemStack outputDye = new ItemStack(GardenBotanicalItems.DYE);
        NbtCompound nbtOutputDye = outputDye.getOrCreateNbt();
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("color", outputColorDye);
        nbtOutputDye.put("display", nbt);

        setStack(OUTPUT_SLOT_DYE, outputDye);
    }

    private boolean hasDyeRecipe() {
        return !slotIsEmpty(INPUT_SLOT_FIRST_DYE) && !slotIsEmpty(INPUT_SLOT_SECOND_DYE);
    }

    private boolean hasRecipe() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertItem(recipe.get().getOutput(null), OUTPUT_SLOT_DYE);
    }

    private Optional<DyeMixerRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            if (i == INPUT_SLOT_FIRST_DYE || i == INPUT_SLOT_SECOND_DYE) {
                continue;
            }
            inv.setStack(i, this.getStack(i));
        }

        return world.getRecipeManager().getFirstMatch(DyeMixerRecipe.Type.INSTANCE, inv, world);
    }

    // PROGRESS
    private void setProcessState(BlockState state, boolean value) {
        this.progress++;

        if (value) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeBlockPos(pos);
            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                GardenBotanicalNetwork.SPAWN_PARTICLE_PACKET.send(player, data);
            }
        }

        if (world != null) {
            world.setBlockState(pos, state.with(DyeMixerBlock.PROCESS, value));
        }
    }

    private void resetProgress(BlockState state) {
        setProcessState(state, false);
        this.progress = 0;
    }

    // RENDER
    public int getFluidColor() {
        if (!slotIsEmpty(OUTPUT_SLOT_DYE))
            return Utils.checkDisplayColorNbt(getStack(OUTPUT_SLOT_DYE), GardenBotanical.DEFAULT_DYE_COLOR);
        return -1;
    }

    public boolean renderFluid() {
        return fluidIsFull() || !slotIsEmpty(OUTPUT_SLOT_DYE);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (state.getAnimatable().world != null) {
                BlockState blockState = state.getAnimatable().world.getBlockState(state.getAnimatable().pos);
                if (blockState.isOf(GardenBotanicalBlocks.DYE_MIXER))
                    if (blockState.get(DyeMixerBlock.PROCESS)) {
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

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
