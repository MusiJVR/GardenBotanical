package net.gardenbotanical.block;

import net.gardenbotanical.block.entity.BlockColorizerBlockEntity;
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


public class BlockColorizerBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(3, 2, 3, 13, 12, 13),
            Block.createCuboidShape(0, 12, 0, 16, 25, 16)
    );
    public static final BooleanProperty PROCESS = BooleanProperty.of("process");

    protected BlockColorizerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        setDefaultState(getDefaultState().with(PROCESS, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockColorizerBlockEntity(pos, state);
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
            if (blockEntity instanceof BlockColorizerBlockEntity) {
                ItemScatterer.spawn(world, pos, (BlockColorizerBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockColorizerBlockEntity entity = (BlockColorizerBlockEntity) world.getBlockEntity(pos);
            ItemStack itemStack = player.getStackInHand(hand);
            if (!entity.slotIsEmpty(BlockColorizerBlockEntity.OUTPUT_SLOT_BLOCK)) {
                Vec3d vec3d = Vec3d.add(pos, 0.5, 1.5, 0.5).addRandom(world.random, 0.7F);
                ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), entity.getOutputBlock());
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
                entity.clearOutputSlot();
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1f, 1f);
            } else {
                if (itemStack.isIn(GardenBotanicalTags.BLOCK_COLORIZER_BLOCK_TYPES)) {
                    if (entity.slotIsEmpty(BlockColorizerBlockEntity.INPUT_SLOT_BLOCK)) {
                        entity.getItems().set(BlockColorizerBlockEntity.INPUT_SLOT_BLOCK, itemStack.copyWithCount(1));
                        itemStack.decrement(1);
                        world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    }
                } else if (itemStack.isOf(GardenBotanicalItems.DYE)) {
                    if (entity.slotIsEmpty(BlockColorizerBlockEntity.INPUT_SLOT_DYE) && !entity.slotIsEmpty(BlockColorizerBlockEntity.INPUT_SLOT_BLOCK)) {
                        entity.getItems().set(BlockColorizerBlockEntity.INPUT_SLOT_DYE, itemStack.copyWithCount(1));
                        itemStack.decrement(1);
                        world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, SoundCategory.BLOCKS, 1f, 1f);
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, GardenBotanicalBlockEntities.BLOCK_COLORIZER_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}