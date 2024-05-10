package net.gardenbotanical.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.GrowingFlower;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class GardenBotanicalModelProvider extends FabricModelProvider {
    public GardenBotanicalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BOUVARDIA, GardenBotanicalBlocks.POTTED_BOUVARDIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BRUNIA, GardenBotanicalBlocks.POTTED_BRUNIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.GERBERA, GardenBotanicalBlocks.POTTED_GERBERA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.HERBAL_PEONY, GardenBotanicalBlocks.POTTED_HERBAL_PEONY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.VERONICA, GardenBotanicalBlocks.POTTED_VERONICA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.DULL_PINK_TULIP, GardenBotanicalBlocks.POTTED_DULL_PINK_TULIP, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.POINSETTIA, GardenBotanicalBlocks.POTTED_POINSETTIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.SETARIA, GardenBotanicalBlocks.POTTED_SETARIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.ALOE_TRASKI, GardenBotanicalBlocks.POTTED_ALOE_TRASKI, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.ASTER, GardenBotanicalBlocks.POTTED_ASTER, BlockStateModelGenerator.TintType.NOT_TINTED);

        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.BOUVARDIA_CROP, GardenBotanicalBlocks.BOUVARDIA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.BRUNIA_CROP, GardenBotanicalBlocks.BRUNIA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.GERBERA_CROP, GardenBotanicalBlocks.GERBERA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.HERBAL_PEONY_CROP, GardenBotanicalBlocks.HERBAL_PEONY, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.VERONICA_CROP, GardenBotanicalBlocks.VERONICA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.DULL_PINK_TULIP_CROP, GardenBotanicalBlocks.DULL_PINK_TULIP, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.POINSETTIA_CROP, GardenBotanicalBlocks.POINSETTIA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.SETARIA_CROP, GardenBotanicalBlocks.SETARIA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.ALOE_TRASKI_CROP, GardenBotanicalBlocks.ALOE_TRASKI, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.ASTER_CROP, GardenBotanicalBlocks.ASTER, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GardenBotanicalItems.GARDEN_PRUNER, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.DYE, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.BOUVARDIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BRUNIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.GERBERA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.HERBAL_PEONY_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.VERONICA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.DULL_PINK_TULIP_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POINSETTIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.SETARIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.ALOE_TRASKI_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.ASTER_PETAL, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BOUVARDIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BRUNIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_GERBERA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_HERBAL_PEONY, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_VERONICA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_DULL_PINK_TULIP, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_POINSETTIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_SETARIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_ALOE_TRASKI, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_ASTER, Models.GENERATED);
    }

    private void registerGrowingFlower(BlockStateModelGenerator blockStateModelGenerator, Block crop, Block flower, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier identifier = (Identifier) int2ObjectMap.computeIfAbsent(i, (j) -> {
                    if (i == ageTextureIndices.length - 1) {
                        Identifier flowerId = Registries.BLOCK.getId(flower);
                        return createSubModel(blockStateModelGenerator, crop, "block/" + flowerId.getPath(), "_stage" + i, Models.CROSS, TextureMap::cross);
                    } else {
                        return createSubModel(blockStateModelGenerator, crop, "block/growing_flower_stage" + i, "_stage" + i, Models.CROSS, TextureMap::cross);
                    }
                });
                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.registerItemModel(crop.asItem());
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
        }
    }

    private Identifier createSubModel(BlockStateModelGenerator blockStateModelGenerator, Block block, String path, String suffix, Model model, Function<Identifier, TextureMap> texturesFactory) {
        return model.upload(block, suffix, texturesFactory.apply(new Identifier(GardenBotanical.MOD_ID, path)), blockStateModelGenerator.modelCollector);
    }
}
