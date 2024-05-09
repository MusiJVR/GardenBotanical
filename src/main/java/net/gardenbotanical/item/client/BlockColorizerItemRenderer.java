package net.gardenbotanical.item.client;

import net.gardenbotanical.item.custom.BlockColorizerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;


public class BlockColorizerItemRenderer extends GeoItemRenderer<BlockColorizerItem> {
    public BlockColorizerItemRenderer() {
        super(new BlockColorizerItemModel());
    }
}