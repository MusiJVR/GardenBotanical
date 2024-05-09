package net.gardenbotanical.item.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.custom.BlockColorizerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class BlockColorizerItemModel extends GeoModel<BlockColorizerItem> {
    @Override
    public Identifier getModelResource(BlockColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/block_colorizer_geo.json");
    }

    @Override
    public Identifier getTextureResource(BlockColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/block_colorizer.png");
    }

    @Override
    public Identifier getAnimationResource(BlockColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/block_colorizer_animation.json");
    }
}