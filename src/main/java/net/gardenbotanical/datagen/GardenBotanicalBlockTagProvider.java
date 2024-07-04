package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;


public class GardenBotanicalBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public GardenBotanicalBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                GardenBotanicalBlocks.BOUVARDIA,
                GardenBotanicalBlocks.BRUNIA,
                GardenBotanicalBlocks.GERBERA,
                GardenBotanicalBlocks.HERBAL_PEONY,
                GardenBotanicalBlocks.VERONICA,
                GardenBotanicalBlocks.DULL_PINK_TULIP,
                GardenBotanicalBlocks.POINSETTIA,
                GardenBotanicalBlocks.SETARIA,
                GardenBotanicalBlocks.ALOE_TRASKI,
                GardenBotanicalBlocks.ASTER,
                GardenBotanicalBlocks.SNOW_HYDRANGEA,
                GardenBotanicalBlocks.CHICORY,
                GardenBotanicalBlocks.IVY,
                GardenBotanicalBlocks.AQUILEGIA,
                GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP,
                GardenBotanicalBlocks.DRY_VIOLA,
                GardenBotanicalBlocks.BIG_DANDELION,
                GardenBotanicalBlocks.AHSOKA,
                GardenBotanicalBlocks.BURGUNDY_ROSE,
                GardenBotanicalBlocks.CALENDULA,
                GardenBotanicalBlocks.STRONGYLODON,
                GardenBotanicalBlocks.SCULK_FLOWER,
                GardenBotanicalBlocks.DIANTHUS,
                GardenBotanicalBlocks.DARK_RED_PHLOX,
                GardenBotanicalBlocks.PINK_MATTHIOLA
        );

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(
                GardenBotanicalBlocks.POTTED_BOUVARDIA,
                GardenBotanicalBlocks.POTTED_BRUNIA,
                GardenBotanicalBlocks.POTTED_GERBERA,
                GardenBotanicalBlocks.POTTED_HERBAL_PEONY,
                GardenBotanicalBlocks.POTTED_VERONICA,
                GardenBotanicalBlocks.POTTED_DULL_PINK_TULIP,
                GardenBotanicalBlocks.POTTED_POINSETTIA,
                GardenBotanicalBlocks.POTTED_SETARIA,
                GardenBotanicalBlocks.POTTED_ALOE_TRASKI,
                GardenBotanicalBlocks.POTTED_ASTER,
                GardenBotanicalBlocks.POTTED_SNOW_HYDRANGEA,
                GardenBotanicalBlocks.POTTED_CHICORY,
                GardenBotanicalBlocks.POTTED_IVY,
                GardenBotanicalBlocks.POTTED_AQUILEGIA,
                GardenBotanicalBlocks.POTTED_BLUE_MOUNTAIN_TULIP,
                GardenBotanicalBlocks.POTTED_DRY_VIOLA,
                GardenBotanicalBlocks.POTTED_BIG_DANDELION,
                GardenBotanicalBlocks.POTTED_AHSOKA,
                GardenBotanicalBlocks.POTTED_BURGUNDY_ROSE,
                GardenBotanicalBlocks.POTTED_CALENDULA,
                GardenBotanicalBlocks.POTTED_STRONGYLODON,
                GardenBotanicalBlocks.POTTED_SCULK_FLOWER,
                GardenBotanicalBlocks.POTTED_DIANTHUS,
                GardenBotanicalBlocks.POTTED_DARK_RED_PHLOX,
                GardenBotanicalBlocks.POTTED_PINK_MATTHIOLA
        );
    }
}
