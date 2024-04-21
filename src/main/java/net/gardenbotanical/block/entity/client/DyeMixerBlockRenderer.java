package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class DyeMixerBlockRenderer extends GeoBlockRenderer<DyeMixerBlockEntity> {
    public DyeMixerBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new DyeMixerBlockModel());
    }
}