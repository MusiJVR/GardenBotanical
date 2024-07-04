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
import net.gardenbotanical.util.Utils;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Items;


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
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SNOW_HYDRANGEA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.CHICORY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.IVY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.AQUILEGIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DRY_VIOLA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BIG_DANDELION, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.AHSOKA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BURGUNDY_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.CALENDULA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.STRONGYLODON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SCULK_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DIANTHUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DARK_RED_PHLOX, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.PINK_MATTHIOLA, RenderLayer.getCutout());

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
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_SNOW_HYDRANGEA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_CHICORY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_IVY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_AQUILEGIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BLUE_MOUNTAIN_TULIP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_DRY_VIOLA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BIG_DANDELION, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_AHSOKA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_BURGUNDY_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_CALENDULA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_STRONGYLODON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_SCULK_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_DIANTHUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_DARK_RED_PHLOX, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.POTTED_PINK_MATTHIOLA, RenderLayer.getCutout());

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
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SNOW_HYDRANGEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.CHICORY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.IVY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.AQUILEGIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DRY_VIOLA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BIG_DANDELION_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.AHSOKA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.BURGUNDY_ROSE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.CALENDULA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.STRONGYLODON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.SCULK_FLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DIANTHUS_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.DARK_RED_PHLOX_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GardenBotanicalBlocks.PINK_MATTHIOLA_CROP, RenderLayer.getCutout());

        HandledScreens.register(GardenBotanicalScreenHandlers.PREPARATION_TABLE_SCREEN_HANDLER, PreparationTableScreen::new);
        HandledScreens.register(GardenBotanicalScreenHandlers.POUNDING_TABLE_SCREEN_HANDLER, PoundingTableScreen::new);

        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.POUNDING_TABLE_BLOCK_ENTITY, PoundingTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.DYE_MIXER_BLOCK_ENTITY, DyeMixerBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(GardenBotanicalBlockEntities.COLORIZER_BLOCK_ENTITY, ColorizerBlockEntityRenderer::new);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Utils.checkDisplayColorNbt(stack, GardenBotanical.DEFAULT_DYE_COLOR), GardenBotanicalItems.DYE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Utils.checkDisplayColorNbt(stack, GardenBotanical.DEFAULT_DYE_COLOR), Items.PAPER);
    }
}
