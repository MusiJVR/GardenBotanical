package net.gardenbotanical.block;

import net.gardenbotanical.block.entity.ColorizerBlockEntity;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class ColorizerBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(1, 0, 1, 15, 21, 15));
    public static final BooleanProperty PROCESS = BooleanProperty.of("process");

    protected ColorizerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        setDefaultState(getDefaultState().with(PROCESS, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ColorizerBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(PROCESS);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ColorizerBlockEntity) {
                ItemScatterer.spawn(world, pos, (ColorizerBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            ColorizerBlockEntity entity = (ColorizerBlockEntity) world.getBlockEntity(pos);
            ItemStack itemStack = player.getStackInHand(hand);
            if (!entity.slotIsEmpty(ColorizerBlockEntity.OUTPUT_SLOT_ARMOR)) {
                Vec3d vec3d = Vec3d.add(pos, 0.5, 1.01, 0.5).addRandom(world.random, 0.7F);
                ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), entity.getOutputArmor());
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
                entity.clearOutputSlot();
                world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.BLOCKS, 1f, 1f);
            } else {
                if (itemStack.isIn(GardenBotanicalTags.COLORIZER_ARMOR_TYPES)) {
                    if (entity.slotIsEmpty(ColorizerBlockEntity.INPUT_SLOT_ARMOR)) {
                        entity.getItems().set(ColorizerBlockEntity.INPUT_SLOT_ARMOR, itemStack.copyWithCount(1));
                        itemStack.decrement(1);
                    }
                } else if (itemStack.isOf(GardenBotanicalItems.DYE)) {
                    if (entity.slotIsEmpty(ColorizerBlockEntity.INPUT_SLOT_DYE)) {
                        entity.getItems().set(ColorizerBlockEntity.INPUT_SLOT_DYE, itemStack.copyWithCount(1));
                        itemStack.decrement(1);
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
