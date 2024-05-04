package net.gardenbotanical.block.entity;

import net.gardenbotanical.block.ColorizerBlock;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
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

    public static final int INPUT_SLOT_ARMOR = 0;
    public static final int INPUT_SLOT_DYE = 1;
    public static final int OUTPUT_SLOT_ARMOR = 2;

    public ColorizerBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (state.getAnimatable().world != null)
                if (state.getAnimatable().world.getBlockState(state.getAnimatable().pos).isOf(GardenBotanicalBlocks.COLORIZER))
                    if (state.getAnimatable().getWorld().getBlockState(state.getAnimatable().pos).get(ColorizerBlock.PROCESS)) {
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
}
