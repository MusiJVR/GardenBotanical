package net.gardenbotanical.world.feature;


import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;


public class GardenBotanicalConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BOUVARDIA = registerKey("bouvardia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BRUNIA = registerKey("brunia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GERBERA = registerKey("gerbera");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HERBAL_PEONY = registerKey("herbal_peony");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERONICA = registerKey("veronica");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // 36 5 5 demo
        register(context, BOUVARDIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                128, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BOUVARDIA)))
        ));
        register(context, BRUNIA, Feature.FLOWER, new RandomPatchFeatureConfig(
                128, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.BRUNIA)))
        ));
        register(context, GERBERA, Feature.FLOWER, new RandomPatchFeatureConfig(
                128, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.GERBERA)))
        ));
        register(context, HERBAL_PEONY, Feature.FLOWER, new RandomPatchFeatureConfig(
                128, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.HERBAL_PEONY)))
        ));
        register(context, VERONICA, Feature.FLOWER, new RandomPatchFeatureConfig(
                128, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GardenBotanicalBlocks.VERONICA)))
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(GardenBotanical.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
