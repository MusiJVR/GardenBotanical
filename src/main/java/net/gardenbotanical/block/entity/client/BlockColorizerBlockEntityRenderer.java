package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.BlockColorizerBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class BlockColorizerBlockEntityRenderer extends GeoBlockRenderer<BlockColorizerBlockEntity> {
    public BlockColorizerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new BlockColorizerBlockEntityModel());
        addRenderLayer(new BlockColorizerBlockEntityRenderLayerBlock(this));
    }
}
