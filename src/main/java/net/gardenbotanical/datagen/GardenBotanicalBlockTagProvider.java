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
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(GardenBotanicalBlocks.POTTED_BOUVARDIA, GardenBotanicalBlocks.POTTED_BRUNIA,
                GardenBotanicalBlocks.POTTED_GERBERA, GardenBotanicalBlocks.POTTED_VERONICA, GardenBotanicalBlocks.POTTED_HERBAL_PEONY);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(GardenBotanicalBlocks.BOUVARDIA, GardenBotanicalBlocks.BRUNIA,
                GardenBotanicalBlocks.GERBERA, GardenBotanicalBlocks.HERBAL_PEONY, GardenBotanicalBlocks.VERONICA);
    }
}
