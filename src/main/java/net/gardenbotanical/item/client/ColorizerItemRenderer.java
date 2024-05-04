package net.gardenbotanical.item.client;

import net.gardenbotanical.item.custom.ColorizerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;


public class ColorizerItemRenderer extends GeoItemRenderer<ColorizerItem> {
    public ColorizerItemRenderer() {
        super(new ColorizerItemModel());
    }
}