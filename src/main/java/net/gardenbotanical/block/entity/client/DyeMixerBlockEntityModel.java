package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class DyeMixerBlockEntityModel extends GeoModel<DyeMixerBlockEntity> {
    @Override
    public Identifier getModelResource(DyeMixerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/dye_mixer_geo.json");
    }

    @Override
    public Identifier getTextureResource(DyeMixerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/dye_mixer.png");
    }

    @Override
    public Identifier getAnimationResource(DyeMixerBlockEntity animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/dye_mixer_animation.json");
    }
}