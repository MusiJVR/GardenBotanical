package net.gardenbotanical.block.entity;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.DyeMixerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.recipe.DyeMixerRecipe;
import net.gardenbotanical.recipe.PoundingTableRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
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

    private static final int INPUT_SLOT_POWDER = 0;
    private static final int OUTPUT_SLOT_DYE = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int water = 0;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public DyeMixerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress;
                    case 2 -> DyeMixerBlockEntity.this.water;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress = value;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress = value;
                    case 2 -> DyeMixerBlockEntity.this.water = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public ItemStack waterIsEmpty() {
        if (water == 1) {
            return new ItemStack(GardenBotanicalItems.WATER_DYE_MIXER);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean getWaterLevel() {
        return water == 1;
    }

    public void incrementWaterLevel() {
        water = 1;
    }

    public ItemStack getOutputSlotDye() {
        return inventory.get(OUTPUT_SLOT_DYE);
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
        nbt.putInt("dye_mixer.water", water);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("dye_mixer.progress");
        water = nbt.getInt("dye_mixer.water");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (water == 1) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (this.hasCraftingFinished()) {
                    this.decreaseWater();
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

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void decreaseWater() {
        water = 0;
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

        return recipe.isPresent()
                && canInsertAmountIntoOutputSlot(recipe.get().getOutput(null), OUTPUT_SLOT_DYE)
                && canInsertItemIntoOutputSlot(recipe.get().getOutput(null).getItem(), OUTPUT_SLOT_DYE);
    }

    private Optional<DyeMixerRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(DyeMixerRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item, int slot) {
        return this.getStack(slot).getItem() == item || this.getStack(slot).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result, int slot) {
        return this.getStack(slot).getCount() + result.getCount() <= getStack(slot).getMaxCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}