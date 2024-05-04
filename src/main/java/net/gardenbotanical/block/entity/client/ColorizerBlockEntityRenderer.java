package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.ColorizerBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class ColorizerBlockEntityRenderer extends GeoBlockRenderer<ColorizerBlockEntity> {
    public ColorizerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new ColorizerBlockEntityModel());
    }
}
