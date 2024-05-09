package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.BlockColorizerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.BlockColorizerSyncPacket;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.gardenbotanical.util.ColorUtils;
import net.gardenbotanical.util.ImplementedInventory;
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


public class BlockColorizerBlockEntity extends BlockEntity implements GeoBlockEntity, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final int INPUT_SLOT_BLOCK = 0;
    public static final int INPUT_SLOT_DYE = 1;
    public static final int OUTPUT_SLOT_BLOCK = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 140;

    public BlockColorizerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.BLOCK_COLORIZER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockColorizerBlockEntity.this.progress;
                    case 1 -> BlockColorizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockColorizerBlockEntity.this.progress = value;
                    case 1 -> BlockColorizerBlockEntity.this.maxProgress = value;
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

    public ItemStack getOutputBlock() {
        return inventory.get(OUTPUT_SLOT_BLOCK);
    }

    public ItemStack getInputBlock() {
        return inventory.get(INPUT_SLOT_BLOCK);
    }

    public ItemStack getInputDye() {
        return inventory.get(INPUT_SLOT_DYE);
    }

    public void clearOutputSlot() {
        inventory.set(OUTPUT_SLOT_BLOCK, ItemStack.EMPTY);
    }

    public boolean slotIsEmpty(int slot) {
        return inventory.get(slot).isEmpty();
    }

    private void setProcessState(BlockState state, boolean value) {
        this.progress++;
        if (world != null) {
            world.setBlockState(pos, state.with(BlockColorizerBlock.PROCESS, value));
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

    public ItemStack getBlockRender() {
        if (!slotIsEmpty(INPUT_SLOT_BLOCK)) {
            return getInputBlock();
        } else if (!slotIsEmpty(OUTPUT_SLOT_BLOCK)) {
            return getOutputBlock();
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.BLOCK_COLORIZER_SYNC_PACKET.send(player, BlockColorizerSyncPacket.write(inventory, getPos()));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        if (!slotIsEmpty(INPUT_SLOT_BLOCK) && !slotIsEmpty(INPUT_SLOT_DYE)) {
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
            if (state.getAnimatable().world != null)
                if (state.getAnimatable().world.getBlockState(state.getAnimatable().pos).isOf(GardenBotanicalBlocks.BLOCK_COLORIZER))
                    if (state.getAnimatable().getWorld().getBlockState(state.getAnimatable().pos).get(BlockColorizerBlock.PROCESS)) {
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

    private boolean hasRecipe() {
        return getInputBlock().isIn(GardenBotanicalTags.BLOCK_COLORIZER_BLOCK_TYPES) && getInputDye().isOf(GardenBotanicalItems.DYE) && slotIsEmpty(OUTPUT_SLOT_BLOCK);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && slot == OUTPUT_SLOT_BLOCK;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {OUTPUT_SLOT_BLOCK};
        } else {
            return new int[] {};
        }
    }

    private void craftArmor() {
        NbtCompound nbtBlock = inventory.get(INPUT_SLOT_BLOCK).getOrCreateSubNbt("display");
        NbtCompound nbtDye = inventory.get(INPUT_SLOT_DYE).getOrCreateSubNbt("display");

        int outputColorBlock;
        if (ColorUtils.checkColorNbt(nbtBlock, GardenBotanical.DEFAULT_DYE_COLOR) != GardenBotanical.DEFAULT_DYE_COLOR) {
            outputColorBlock = ColorUtils.blendColors(ColorUtils.checkColorNbt(nbtBlock, GardenBotanical.DEFAULT_DYE_COLOR), ColorUtils.checkColorNbt(nbtDye, GardenBotanical.DEFAULT_DYE_COLOR));
        } else {
            outputColorBlock = ColorUtils.checkColorNbt(nbtDye, GardenBotanical.DEFAULT_DYE_COLOR);
        }

        this.removeStack(INPUT_SLOT_BLOCK);
        this.removeStack(INPUT_SLOT_DYE);

        ItemStack outputBlock = new ItemStack(GardenBotanicalBlocks.DYEBLE_MATERIAL);
        NbtCompound nbtOutputBlock = outputBlock.getOrCreateSubNbt("display");
        nbtOutputBlock.putInt("material", 0);
        nbtOutputBlock.putInt("color", outputColorBlock);

        putItemInOutputSlot(outputBlock, OUTPUT_SLOT_BLOCK);
    }

    private void putItemInOutputSlot(ItemStack itemStack, int slot) {
        this.setStack(slot, itemStack);
    }
}