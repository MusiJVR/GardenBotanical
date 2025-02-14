package net.gardenbotanical;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.gardenbotanical.datagen.*;
import net.gardenbotanical.world.feature.GardenBotanicalConfiguredFeatures;
import net.gardenbotanical.world.feature.GardenBotanicalPlacedFeatures;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;


public class GardenBotanicalDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        GardenBotanical.LOGGER.info("Generate Data for " + GardenBotanical.MOD_ID);
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(GardenBotanicalItemTagProvider::new);
        pack.addProvider(GardenBotanicalBlockTagProvider::new);
        pack.addProvider(GardenBotanicalLootTableProvider::new);
        pack.addProvider(GardenBotanicalModelProvider::new);
        pack.addProvider(GardenBotanicalRecipeProvider::new);
        pack.addProvider(GardenBotanicalWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, GardenBotanicalConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, GardenBotanicalPlacedFeatures::bootstrap);
    }
}
