package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.tag.GardenBotanicalTags;
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
        getOrCreateTagBuilder(GardenBotanicalTags.FLOWER_POWDERS).add(
                GardenBotanicalItems.POWDERED_BOUVARDIA,
                GardenBotanicalItems.POWDERED_BRUNIA,
                GardenBotanicalItems.POWDERED_GERBERA,
                GardenBotanicalItems.POWDERED_HERBAL_PEONY,
                GardenBotanicalItems.POWDERED_VERONICA,
                GardenBotanicalItems.POWDERED_DULL_PINK_TULIP,
                GardenBotanicalItems.POWDERED_POINSETTIA,
                GardenBotanicalItems.POWDERED_SETARIA,
                GardenBotanicalItems.POWDERED_ALOE_TRASKI,
                GardenBotanicalItems.POWDERED_ASTER
        );
    }
}
