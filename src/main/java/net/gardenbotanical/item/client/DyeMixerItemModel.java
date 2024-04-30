package net.gardenbotanical.item.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.custom.DyeMixerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class DyeMixerItemModel extends GeoModel<DyeMixerItem> {
    @Override
    public Identifier getModelResource(DyeMixerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/dye_mixer_geo.json");
    }

    @Override
    public Identifier getTextureResource(DyeMixerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/dye_mixer.png");
    }

    @Override
    public Identifier getAnimationResource(DyeMixerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/dye_mixer_animation.json");
    }
}