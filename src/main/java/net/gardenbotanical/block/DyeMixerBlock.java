package net.gardenbotanical.block;

import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
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
        Direction direction = state.get(Properties.HORIZONTAL_FACING);
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            DyeMixerBlockEntity entity = (DyeMixerBlockEntity) world.getBlockEntity(pos);
            ItemStack itemStack = player.getStackInHand(hand);

            if (!entity.outputSlotIsEmpty()) {
                Vec3d vec3d = Vec3d.add(pos, 0.5, 1.01, 0.5).addRandom(world.random, 0.7F);
                ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), entity.getOutputSlotDye());
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else if (entity.getWaterLevel()) {
                if (entity.inputSlotIsEmpty()) {
                    if (itemStack.getItem() == GardenBotanicalItems.POWDERED_BOUVARDIA) {
                        entity.getItems().set(0, new ItemStack(itemStack.getItem(), 1));
                        player.setStackInHand(hand, new ItemStack(itemStack.getItem(), itemStack.getCount() - 1));
                    }
                }
            } else if (!entity.getWaterLevel()) {
                if (itemStack.getItem() == Items.WATER_BUCKET) {
                    entity.incrementWaterLevel();
                    player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
                    world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.AMBIENT, 1f, 1f);
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, (world1, pos, state1, blockEnity) -> blockEnity.tick(world1, pos, state1));
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