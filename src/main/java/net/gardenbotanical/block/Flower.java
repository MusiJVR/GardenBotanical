package net.gardenbotanical.block;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.List;

//Test Flower class

public class Flower extends FlowerBlock {
    private final List<Block> plantBlocks;

    public Flower(List<Block> plantBlocks, StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
        this.plantBlocks = plantBlocks;
    }
    /*protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);
    private final List<Block> plantBlocks;

    public Flower(List<Block> plantBlocks, AbstractBlock.Settings settings) {
        super(settings);

    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public StatusEffect getEffectInStew() {
        return null;
    }

    @Override
    public int getEffectInStewDuration() {
        return 0;
    }*/

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        for (Block block : plantBlocks) {
            if (floor.isOf(block)) return true;
        }
        return false;
    }
}
