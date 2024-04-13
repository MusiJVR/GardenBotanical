package net.gardenbotanical.world.feature;

import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

import java.util.List;


public class GardenBotanicalPlacedFeatures {
    public static final RegistryKey<PlacedFeature> BOUVARDIA_PLACED = registerKey("bouvardia_placed");
    public static final RegistryKey<PlacedFeature> BRUNIA_PLACED = registerKey("brunia_placed");
    public static final RegistryKey<PlacedFeature> GERBERA_PLACED = registerKey("gerbera_placed");
    public static final RegistryKey<PlacedFeature> HERBAL_PEONY_PLACED = registerKey("herbal_peony_placed");
    public static final RegistryKey<PlacedFeature> VERONICA_PLACED = registerKey("veronica_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        //47 demo
        register(context, BOUVARDIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BOUVARDIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(2), GardenBotanicalBlocks.BOUVARDIA));
        register(context, BRUNIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BRUNIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(2), GardenBotanicalBlocks.BRUNIA));
        register(context, GERBERA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.GERBERA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(2), GardenBotanicalBlocks.GERBERA));
        register(context, HERBAL_PEONY_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.HERBAL_PEONY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(2), GardenBotanicalBlocks.HERBAL_PEONY));
        register(context, VERONICA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.VERONICA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(2), GardenBotanicalBlocks.VERONICA));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GardenBotanical.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}