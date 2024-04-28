package net.gardenbotanical.block;

import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;


public class DyeMixerBlock extends BlockWithEntity {
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(4, 0, 3, 13, 15, 12));
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(3, 0, 4, 12, 15, 13));
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(Block.createCuboidShape(3, 0, 3, 12, 15, 12));
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(Block.createCuboidShape(4, 0, 4, 13, 15, 13));
    public static final BooleanProperty PROCESS = BooleanProperty.of("process");

    protected DyeMixerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        setDefaultState(getDefaultState().with(PROCESS, false));
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
        Direction direction = (Direction) state.get(Properties.HORIZONTAL_FACING);
        if (direction == Direction.NORTH) {
            return NORTH_SHAPE;
        } else if (direction == Direction.SOUTH) {
            return SOUTH_SHAPE;
        } else if (direction == Direction.WEST) {
            return WEST_SHAPE;
        } else if (direction == Direction.EAST) {
            return EAST_SHAPE;
        } else {
            return NORTH_SHAPE;
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeMixerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}