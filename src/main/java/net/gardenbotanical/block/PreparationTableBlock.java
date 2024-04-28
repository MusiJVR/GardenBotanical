package net.gardenbotanical.block;

import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.block.entity.PreparationTableBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class PreparationTableBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 14, 0, 16, 15, 16),
            Block.createCuboidShape(1, 13, 1, 15, 14, 15),
            Block.createCuboidShape(0, 0, 0, 2, 15, 2),
            Block.createCuboidShape(14, 0, 0, 16, 15, 2),
            Block.createCuboidShape(0, 0, 14, 2, 15, 16),
            Block.createCuboidShape(14, 0, 14, 16, 15, 16),
            Block.createCuboidShape(2, 6, 1, 14, 7, 2),
            Block.createCuboidShape(14, 6, 2, 15, 7, 14),
            Block.createCuboidShape(1, 6, 2, 2, 7, 14),
            Block.createCuboidShape(2, 6, 14, 14, 7, 15)
    );

    public PreparationTableBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
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

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PreparationTableBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PreparationTableBlockEntity) {
                ItemScatterer.spawn(world, pos, (PreparationTableBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((PreparationTableBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, GardenBotanicalBlockEntities.PREPARATION_TABLE_BLOCK_ENTITY, (world1, pos, state1, blockEnity) -> blockEnity.tick(world1, pos, state1));
    }
}