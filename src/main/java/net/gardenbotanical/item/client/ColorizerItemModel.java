package net.gardenbotanical.item.client;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.custom.ColorizerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


public class ColorizerItemModel extends GeoModel<ColorizerItem> {
    @Override
    public Identifier getModelResource(ColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "geo/colorizer_geo.json");
    }

    @Override
    public Identifier getTextureResource(ColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "textures/block/colorizer.png");
    }

    @Override
    public Identifier getAnimationResource(ColorizerItem animatable) {
        return new Identifier(GardenBotanical.MOD_ID, "animations/colorizer_animation.json");
    }
}
