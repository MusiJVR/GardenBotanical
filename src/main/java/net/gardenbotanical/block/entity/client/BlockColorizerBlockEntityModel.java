package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.entity.BlockColorizerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class BlockColorizerBlockEntityModel extends GeoModel<BlockColorizerBlockEntity> {
    @Override
    public Identifier getModelResource(BlockColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/block_colorizer_geo.json");
    }

    @Override
    public Identifier getTextureResource(BlockColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/block_colorizer.png");
    }

    @Override
    public Identifier getAnimationResource(BlockColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/block_colorizer_animation.json");
    }
}