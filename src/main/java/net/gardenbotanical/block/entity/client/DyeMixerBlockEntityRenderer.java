package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class DyeMixerBlockEntityRenderer extends GeoBlockRenderer<DyeMixerBlockEntity> {
    public DyeMixerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new DyeMixerBlockEntityModel());
        addRenderLayer(new DyeMixerBlockEntityRenderLayer(this));
    }
}
