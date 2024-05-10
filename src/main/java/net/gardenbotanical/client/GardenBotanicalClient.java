package net.gardenbotanical.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.block.entity.client.ColorizerBlockEntityRenderer;
import net.gardenbotanical.block.entity.client.DyeMixerBlockEntityRenderer;
import net.gardenbotanical.block.entity.client.PoundingTableBlockEntityRenderer;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.screen.GardenBotanicalScreenHandlers;
import net.gardenbotanical.screen.PoundingTableScreen;
import net.gardenbotanical.screen.PreparationTableScreen;
import net.gardenbotanical.util.ColorUtils;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Items;
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
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DULL_PINK_TULIP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POINSETTIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SETARIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.ALOE_TRASKI, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.ASTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BOUVARDIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_GERBERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BRUNIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_HERBAL_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_VERONICA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_DULL_PINK_TULIP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_POINSETTIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_SETARIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_ALOE_TRASKI, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_ASTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BOUVARDIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BRUNIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.GERBERA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.HERBAL_PEONY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.VERONICA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DULL_PINK_TULIP_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POINSETTIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SETARIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.ALOE_TRASKI_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.ASTER_CROP, RenderLayer.getCutout());

        HandledScreens.register(GardenBotanicalScreenHandlers.PREPARATION_TABLE_SCREEN_HANDLER, PreparationTableScreen::new);
        HandledScreens.register(GardenBotanicalScreenHandlers.POUNDING_TABLE_SCREEN_HANDLER, PoundingTableScreen::new);

        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.POUNDING_TABLE_BLOCK_ENTITY, PoundingTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, DyeMixerBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, ColorizerBlockEntityRenderer::new);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            NbtCompound nbtCompound = stack.getOrCreateSubNbt("display");
            return ColorUtils.checkColorNbt(nbtCompound, GardenBotanical.DEFAULT_DYE_COLOR);
        }, GardenBotanicalItems.DYE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            NbtCompound nbtCompound = stack.getOrCreateSubNbt("display");
            return ColorUtils.checkColorNbt(nbtCompound, GardenBotanical.DEFAULT_DYE_COLOR);
        }, Items.PAPER);
    }
}
