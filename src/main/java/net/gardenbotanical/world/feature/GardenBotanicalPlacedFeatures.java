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
    public static final RegistryKey<PlacedFeature> DULL_PINK_TULIP_PLACED = registerKey("dull_pink_tulip_placed");
    public static final RegistryKey<PlacedFeature> POINSETTIA_PLACED = registerKey("poinsettia_placed");
    public static final RegistryKey<PlacedFeature> SETARIA_PLACED = registerKey("setaria_placed");
    public static final RegistryKey<PlacedFeature> ALOE_TRASKI_PLACED = registerKey("aloe_traski_placed");
    public static final RegistryKey<PlacedFeature> ASTER_PLACED = registerKey("aster_placed");
    public static final RegistryKey<PlacedFeature> SNOW_HYDRANGEA_PLACED = registerKey("snow_hydrangea_placed");
    public static final RegistryKey<PlacedFeature> CHICORY_PLACED = registerKey("chicory_placed");
    public static final RegistryKey<PlacedFeature> IVY_PLACED = registerKey("ivy_placed");
    public static final RegistryKey<PlacedFeature> AQUILEGIA_PLACED = registerKey("aquilegia_placed");
    public static final RegistryKey<PlacedFeature> BLUE_MOUNTAIN_TULIP_PLACED = registerKey("blue_mountain_tulip_placed");
    public static final RegistryKey<PlacedFeature> DRY_VIOLA_PLACED = registerKey("dry_viola_placed");
    public static final RegistryKey<PlacedFeature> BIG_DANDELION_PLACED = registerKey("big_dandelion_placed");
    public static final RegistryKey<PlacedFeature> AHSOKA_PLACED = registerKey("ahsoka_placed");
    public static final RegistryKey<PlacedFeature> BURGUNDY_ROSE_PLACED = registerKey("burgundy_rose_placed");
    public static final RegistryKey<PlacedFeature> CALENDULA_PLACED = registerKey("calendula_placed");
    public static final RegistryKey<PlacedFeature> STRONGYLODON_PLACED = registerKey("strongylodon_placed");
    public static final RegistryKey<PlacedFeature> SCULK_FLOWER_PLACED = registerKey("sculk_flower_placed");
    public static final RegistryKey<PlacedFeature> DIANTHUS_PLACED = registerKey("dianthus_placed");
    public static final RegistryKey<PlacedFeature> DARK_RED_PHLOX_PLACED = registerKey("dark_red_phlox_placed");
    public static final RegistryKey<PlacedFeature> PINK_MATTHIOLA_PLACED = registerKey("pink_matthiola_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, BOUVARDIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BOUVARDIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(15), GardenBotanicalBlocks.BOUVARDIA));
        register(context, BRUNIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BRUNIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), GardenBotanicalBlocks.BRUNIA));
        register(context, GERBERA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.GERBERA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.GERBERA));
        register(context, HERBAL_PEONY_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.HERBAL_PEONY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.HERBAL_PEONY));
        register(context, VERONICA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.VERONICA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(30), GardenBotanicalBlocks.VERONICA));
        register(context, DULL_PINK_TULIP_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.DULL_PINK_TULIP),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.DULL_PINK_TULIP));
        register(context, POINSETTIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.POINSETTIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(15), GardenBotanicalBlocks.POINSETTIA));
        register(context, SETARIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.SETARIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(35), GardenBotanicalBlocks.SETARIA));
        register(context, ALOE_TRASKI_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.ALOE_TRASKI),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.ALOE_TRASKI));
        register(context, ASTER_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.ASTER),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(15), GardenBotanicalBlocks.ASTER));
        register(context, SNOW_HYDRANGEA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.SNOW_HYDRANGEA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(30), GardenBotanicalBlocks.SNOW_HYDRANGEA));
        register(context, CHICORY_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.CHICORY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.CHICORY));
        register(context, IVY_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.IVY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(25), GardenBotanicalBlocks.IVY));
        register(context, AQUILEGIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.AQUILEGIA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(15), GardenBotanicalBlocks.AQUILEGIA));
        register(context, BLUE_MOUNTAIN_TULIP_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BLUE_MOUNTAIN_TULIP),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP));
        register(context, DRY_VIOLA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.DRY_VIOLA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.DRY_VIOLA));
        register(context, BIG_DANDELION_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BIG_DANDELION),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(25), GardenBotanicalBlocks.BIG_DANDELION));
        register(context, AHSOKA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.AHSOKA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), GardenBotanicalBlocks.AHSOKA));
        register(context, BURGUNDY_ROSE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.BURGUNDY_ROSE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(15), GardenBotanicalBlocks.BURGUNDY_ROSE));
        register(context, CALENDULA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.CALENDULA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.CALENDULA));
        register(context, STRONGYLODON_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.STRONGYLODON),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(30), GardenBotanicalBlocks.STRONGYLODON));
        register(context, SCULK_FLOWER_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.SCULK_FLOWER),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(5), GardenBotanicalBlocks.SCULK_FLOWER));
        register(context, DIANTHUS_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.DIANTHUS),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(30), GardenBotanicalBlocks.DIANTHUS));
        register(context, DARK_RED_PHLOX_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.DARK_RED_PHLOX),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), GardenBotanicalBlocks.DARK_RED_PHLOX));
        register(context, PINK_MATTHIOLA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(GardenBotanicalConfiguredFeatures.PINK_MATTHIOLA),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(RarityFilterPlacementModifier.of(20), GardenBotanicalBlocks.PINK_MATTHIOLA));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GardenBotanical.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
