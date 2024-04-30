package net.gardenbotanical.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.block.entity.client.DyeMixerBlockEntityRenderer;
import net.gardenbotanical.block.entity.client.PoundingTableBlockEntityRenderer;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.screen.GardenBotanicalScreenHandlers;
import net.gardenbotanical.screen.PoundingTableScreen;
import net.gardenbotanical.screen.PreparationTableScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.nbt.NbtCompound;


public class GardenBotanicalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GardenBotanical.LOGGER.info("Initialize " + GardenBotanical.MOD_ID + " client...");
        GardenBotanicalNetwork.registerS2CPacket();

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

        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.POUNDING_TABLE_BLOCK_ENTITY, PoundingTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, DyeMixerBlockEntityRenderer::new);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            NbtCompound nbtCompound = stack.getNbt();
            if (nbtCompound != null) {
                return nbtCompound.getInt("color");
            }
            return 0x3F76E4;
        }, GardenBotanicalItems.WATER_DYE_MIXER);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            NbtCompound nbtCompound = stack.getNbt();
            if (nbtCompound != null)
                return nbtCompound.getInt("color");
            return 0xFFFFFF;
        }, GardenBotanicalItems.DYE);
    }
}