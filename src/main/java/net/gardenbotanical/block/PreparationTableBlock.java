package net.gardenbotanical.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;


public class PreparationTableBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 14, 0, 16, 15, 16),
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
        return null;
    }
}