package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.entity.ColorizerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class ColorizerBlockEntityModel extends GeoModel<ColorizerBlockEntity> {
    @Override
    public Identifier getModelResource(ColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/colorizer_geo.json");
    }

    @Override
    public Identifier getTextureResource(ColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/colorizer.png");
    }

    @Override
    public Identifier getAnimationResource(ColorizerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/colorizer_animation.json");
    }
}
