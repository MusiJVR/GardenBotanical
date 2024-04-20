package net.gardenbotanical.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.entity.AnimatedBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public Identifier getModelResource(AnimatedBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/test.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/test.png");
    }

    @Override
    public Identifier getAnimationResource(AnimatedBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/test.animation.json");
    }
}
