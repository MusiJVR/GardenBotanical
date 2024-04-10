package net.gardenbotanical.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.minecraft.client.render.RenderLayer;


public class GardenBotanicalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GardenBotanical.LOGGER.info("Initialize " + GardenBotanical.MOD_ID + " client...");

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.BOUVARDIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.GERBERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.BRUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.HERBAL_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.VERONICA, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.POTTED_BOUVARDIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.POTTED_GERBERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.POTTED_BRUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.POTTED_HERBAL_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlock.POTTED_VERONICA, RenderLayer.getCutout());
    }
}