package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class GardenBotanicalItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public GardenBotanicalItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(GardenBotanicalBlock.BOUVARDIA.asItem(), GardenBotanicalBlock.BRUNIA.asItem(),
                GardenBotanicalBlock.GERBERA.asItem(), GardenBotanicalBlock.HERBAL_PEONY.asItem(), GardenBotanicalBlock.VERONICA.asItem());
    }
}
