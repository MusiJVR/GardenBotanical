package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.gardenbotanical.block.DyeMixerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.recipe.DyeMixerRecipe;
import net.gardenbotanical.util.FluidStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Optional;


public class DyeMixerBlockEntity extends BlockEntity implements GeoBlockEntity, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>() {
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
    };

    private static final int INPUT_SLOT_POWDER = 0;
    private static final int OUTPUT_SLOT_DYE = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public DyeMixerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress = value;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public void setFluidLevel(FluidVariant fluidVariant, long fluidLevel) {
        this.fluidStorage.variant = fluidVariant;
        this.fluidStorage.amount = fluidLevel;
    }

    public ItemStack getWater() {
        if (fluidStorageIsFull()) {
            return new ItemStack(GardenBotanicalItems.WATER_DYE_MIXER);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    public boolean fluidStorageIsFull() {
        return fluidStorage.amount == 1000;
    }

    public void fillFluidStorage() {
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.insert(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(FluidConstants.BUCKET), transaction);
            transaction.commit();
        }
    }

    public void extractFluidStorage() {
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(FluidConstants.BUCKET), transaction);
            transaction.commit();
        }
    }

    public ItemStack getOutputSlotDye() {
        return inventory.get(OUTPUT_SLOT_DYE);
    }

    public void clearOutputSlot() {
        inventory.set(OUTPUT_SLOT_DYE, ItemStack.EMPTY);
    }

    public boolean inputSlotIsEmpty() {
        return inventory.get(INPUT_SLOT_POWDER).isEmpty();
    }

    public boolean outputSlotIsEmpty() {
        return inventory.get(OUTPUT_SLOT_DYE).isEmpty();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (state.getAnimatable().world != null)
                if (state.getAnimatable().world.getBlockState(state.getAnimatable().pos).isOf(GardenBotanicalBlocks.DYE_MIXER))
                    if (state.getAnimatable().getWorld().getBlockState(state.getAnimatable().pos).get(DyeMixerBlock.PROCESS)) {
                        state.setAnimation(RawAnimation.begin().then("process", Animation.LoopType.LOOP));
                        return PlayState.CONTINUE;
                    } else {
                        return PlayState.STOP;
                    }
                else
                    return PlayState.STOP;
            else
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

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("dye_mixer.progress", progress);
        nbt.put("dye_mixer.variant", fluidStorage.variant.toNbt());
        nbt.putLong("dye_mixer.fluid", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("dye_mixer.progress");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("dye_mixer.variant"));
        fluidStorage.amount = nbt.getLong("dye_mixer.fluid");
    }

    private void updateClientData() {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());

        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.FLUID_SYNC_PACKET.send(player, data);
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        updateClientData();
        if (fluidStorageIsFull()) {
            if (hasRecipe()) {
                setProcessState(world, pos, state, true);
                progress++;
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    extractFluidStorage();
                    craftItem();
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

    private void craftItem() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT_POWDER, 1);

        putItemInOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_DYE);
    }

    private void putItemInOutputSlot(ItemStack result, int slot) {
        this.setStack(slot, new ItemStack(result.getItem(), getStack(slot).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertItemIntoOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_DYE);
    }

    private Optional<DyeMixerRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(DyeMixerRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack itemStack, int slot) {
        return (this.getStack(slot).getItem() == itemStack.getItem() || this.getStack(slot).isEmpty())
                && (this.getStack(slot).getCount() + itemStack.getCount() <= getStack(slot).getMaxCount());
    }

    private void setProcessState(World world, BlockPos pos, BlockState state, boolean value) {
        world.setBlockState(pos, state.with(DyeMixerBlock.PROCESS, value));
    }

    private void resetProgress(BlockState state) {
        setProcessState(world, pos, state, false);
        this.progress = 0;
    }
}