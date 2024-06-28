package net.gardenbotanical.item.client;

import net.gardenbotanical.item.custom.DyeMixerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;


public class DyeMixerItemRenderer extends GeoItemRenderer<DyeMixerItem> {
    public DyeMixerItemRenderer() {
        super(new DyeMixerItemModel());
    }
}
