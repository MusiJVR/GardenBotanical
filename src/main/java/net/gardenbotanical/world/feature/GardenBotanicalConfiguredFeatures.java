package net.gardenbotanical.world.feature;


import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;


public class GardenBotanicalConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BOUVARDIA = registerKey("bouvardia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BRUNIA = registerKey("brunia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GERBERA = registerKey("gerbera");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HERBAL_PEONY = registerKey("herbal_peony");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERONICA = registerKey("veronica");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DULL_PINK_TULIP = registerKey("dull_pink_tulip");
    public static final RegistryKey<ConfiguredFeature<?, ?>> POINSETTIA = registerKey("poinsettia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SETARIA = registerKey("setaria");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ALOE_TRASKI = registerKey("aloe_traski");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ASTER = registerKey("aster");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOW_HYDRANGEA = registerKey("snow_hydrangea");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHICORY = registerKey("chicory");
    public static final RegistryKey<ConfiguredFeature<?, ?>> IVY = registerKey("ivy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AQUILEGIA = registerKey("aquilegia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_MOUNTAIN_TULIP = registerKey("blue_mountain_tulip");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DRY_VIOLA = registerKey("dry_viola");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_DANDELION = registerKey("big_dandelion");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AHSOKA = registerKey("ahsoka");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURGUNDY_ROSE = registerKey("burgundy_rose");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CALENDULA = registerKey("calendula");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STRONGYLODON = registerKey("strongylodon");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SCULK_FLOWER = registerKey("sculk_flower");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DIANTHUS = registerKey("dianthus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_RED_PHLOX = registerKey("dark_red_phlox");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_MATTHIOLA = registerKey("pink_matthiola");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, BOUVARDIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                8, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BOUVARDIA)))
        ));
        register(context, BRUNIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                24, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BRUNIA)))
        ));
        register(context, GERBERA, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.GERBERA)))
        ));
        register(context, HERBAL_PEONY, Feature.FLOWER, new RandomPatchFeatureConfig(
                24, 3, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.HERBAL_PEONY)))
        ));
        register(context, VERONICA, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 2, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.VERONICA)))
        ));
        register(context, DULL_PINK_TULIP, Feature.FLOWER, new RandomPatchFeatureConfig(
                36, 5, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.DULL_PINK_TULIP)))
        ));
        register(context, POINSETTIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                1, 0, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.POINSETTIA)))
        ));
        register(context, SETARIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 2, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.SETARIA)))
        ));
        register(context, ALOE_TRASKI, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 2, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.ALOE_TRASKI)))
        ));
        register(context, ASTER, Feature.FLOWER, new RandomPatchFeatureConfig(
                24, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.ASTER)))
        ));
        register(context, SNOW_HYDRANGEA, Feature.FLOWER, new RandomPatchFeatureConfig(
                6, 1, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.SNOW_HYDRANGEA)))
        ));
        register(context, CHICORY, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 3, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.CHICORY)))
        ));
        register(context, IVY, Feature.FLOWER, new RandomPatchFeatureConfig(
                3, 2, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.IVY)))
        ));
        register(context, AQUILEGIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 4, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.AQUILEGIA)))
        ));
        register(context, BLUE_MOUNTAIN_TULIP, Feature.FLOWER, new RandomPatchFeatureConfig(
                10, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP)))
        ));
        register(context, DRY_VIOLA, Feature.FLOWER, new RandomPatchFeatureConfig(
                16, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.DRY_VIOLA)))
        ));
        register(context, BIG_DANDELION, Feature.FLOWER, new RandomPatchFeatureConfig(
                20, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BIG_DANDELION)))
        ));
        register(context, AHSOKA, Feature.FLOWER, new RandomPatchFeatureConfig(
                16, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.AHSOKA)))
        ));
        register(context, BURGUNDY_ROSE, Feature.FLOWER, new RandomPatchFeatureConfig(
                8, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BURGUNDY_ROSE)))
        ));
        register(context, CALENDULA, Feature.FLOWER, new RandomPatchFeatureConfig(
                12, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.CALENDULA)))
        ));
        register(context, STRONGYLODON, Feature.FLOWER, new RandomPatchFeatureConfig(
                6, 2, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.STRONGYLODON)))
        ));
        //Test generation
        /*register(context, SCULK_FLOWER, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationFeatureConfig(
                WeightedBlockStateProvider.of(GardenBotanicalBlocks.SCULK_FLOWER), 2, 2
        ));*/
        register(context, SCULK_FLOWER, Feature.FLOWER, new RandomPatchFeatureConfig(
                5, 2, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.SCULK_FLOWER)))
        ));
        register(context, DIANTHUS, Feature.FLOWER, new RandomPatchFeatureConfig(
                14, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.DIANTHUS)))
        ));
        register(context, DARK_RED_PHLOX, Feature.FLOWER, new RandomPatchFeatureConfig(
                14, 4, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.DARK_RED_PHLOX)))
        ));
        register(context, PINK_MATTHIOLA, Feature.FLOWER, new RandomPatchFeatureConfig(
                6, 3, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.PINK_MATTHIOLA)))
        ));
    }

    //Test method
    /*private static void register(Registerable<ConfiguredFeature<?,?>> context, RegistryKey<ConfiguredFeature<?,?>> key, Feature<NetherForestVegetationFeatureConfig> feature, RandomPatchFeatureConfig configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }*/

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(GardenBotanical.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
