package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class GardenBotanicalBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public GardenBotanicalBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(GardenBotanicalBlock.POTTED_BOUVARDIA, GardenBotanicalBlock.POTTED_BRUNIA,
                GardenBotanicalBlock.POTTED_GERBERA, GardenBotanicalBlock.POTTED_VERONICA, GardenBotanicalBlock.POTTED_HERBAL_PEONY);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(GardenBotanicalBlock.BOUVARDIA, GardenBotanicalBlock.BRUNIA,
                GardenBotanicalBlock.GERBERA, GardenBotanicalBlock.HERBAL_PEONY, GardenBotanicalBlock.VERONICA);
    }
}
