package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;


public class GardenBotanicalItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public GardenBotanicalItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(
                GardenBotanicalBlocks.BOUVARDIA.asItem(),
                GardenBotanicalBlocks.BRUNIA.asItem(),
                GardenBotanicalBlocks.GERBERA.asItem(),
                GardenBotanicalBlocks.HERBAL_PEONY.asItem(),
                GardenBotanicalBlocks.VERONICA.asItem(),
                GardenBotanicalBlocks.DULL_PINK_TULIP.asItem(),
                GardenBotanicalBlocks.POINSETTIA.asItem(),
                GardenBotanicalBlocks.SETARIA.asItem(),
                GardenBotanicalBlocks.ALOE_TRASKI.asItem(),
                GardenBotanicalBlocks.ASTER.asItem()
        );
    }
}
