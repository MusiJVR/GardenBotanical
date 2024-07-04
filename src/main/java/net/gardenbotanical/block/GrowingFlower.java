package net.gardenbotanical.block;

import net.gardenbotanical.GardenBotanical;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;


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
}
