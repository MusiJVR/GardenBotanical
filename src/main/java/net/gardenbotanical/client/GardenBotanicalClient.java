package net.gardenbotanical.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.screen.GardenBotanicalScreenHandlers;
import net.gardenbotanical.screen.PoundingTableScreen;
import net.gardenbotanical.screen.PreparationTableScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;


public class GardenBotanicalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GardenBotanical.LOGGER.info("Initialize " + GardenBotanical.MOD_ID + " client...");

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BOUVARDIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.GERBERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BRUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.HERBAL_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.VERONICA, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BOUVARDIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_GERBERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BRUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_HERBAL_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_VERONICA, RenderLayer.getCutout());

        HandledScreens.register(GardenBotanicalScreenHandlers.PREPARATION_TABLE_SCREEN_HANDLER, PreparationTableScreen::new);
        HandledScreens.register(GardenBotanicalScreenHandlers.POUNDING_TABLE_SCREEN_HANDLER, PoundingTableScreen::new);
    }
}