package net.gardenbotanical.block;

import net.gardenbotanical.GardenBotanical;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Iterator;


public class GrowingFlower extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(5, 0, 5, 11, 4, 11),
            Block.createCuboidShape(5, 0, 5, 11, 6, 11),
            Block.createCuboidShape(5, 0, 5, 11, 8, 11),
            Block.createCuboidShape(5, 0, 5, 11, 9, 11),
            Block.createCuboidShape(5, 0, 5, 11, 10, 11),
            Block.createCuboidShape(5, 0, 5, 11, 10, 11)
    };

    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = IntProperty.of("age", 0, 5);
    private final String seedsId;
    private final Block floorBlock;

    public GrowingFlower(String seedsId, Settings settings, Block floorBlock) {
        super(settings);
        this.seedsId = seedsId;
        this.floorBlock = floorBlock;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[getAge(state)];
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return Registries.ITEM.get(new Identifier(GardenBotanical.MOD_ID, this.seedsId));
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(this.floorBlock);
    }

    //Test methods
    /*@Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.floorBlock.equals(Blocks.NETHERRACK)) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = 1.0F;
                if (isLavaNearby(world, pos)) {
                    f += 0.75F;
                }

                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
                }
            }
        } else {
            super.randomTick(state, world, pos, random);
        }
    }

    private static boolean isLavaNearby(WorldView world, BlockPos pos) {
        Iterator var2 = BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();

        BlockPos blockPos;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            blockPos = (BlockPos)var2.next();
        } while(!world.getFluidState(blockPos).isIn(FluidTags.LAVA));

        return true;
    }*/
}
