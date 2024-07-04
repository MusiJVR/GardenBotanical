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
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.SNOW_HYDRANGEA, GardenBotanicalBlocks.POTTED_SNOW_HYDRANGEA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.CHICORY, GardenBotanicalBlocks.POTTED_CHICORY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.IVY, GardenBotanicalBlocks.POTTED_IVY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.AQUILEGIA, GardenBotanicalBlocks.POTTED_AQUILEGIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP, GardenBotanicalBlocks.POTTED_BLUE_MOUNTAIN_TULIP, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.DRY_VIOLA, GardenBotanicalBlocks.POTTED_DRY_VIOLA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BIG_DANDELION, GardenBotanicalBlocks.POTTED_BIG_DANDELION, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.AHSOKA, GardenBotanicalBlocks.POTTED_AHSOKA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BURGUNDY_ROSE, GardenBotanicalBlocks.POTTED_BURGUNDY_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.CALENDULA, GardenBotanicalBlocks.POTTED_CALENDULA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.STRONGYLODON, GardenBotanicalBlocks.POTTED_STRONGYLODON, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.SCULK_FLOWER, GardenBotanicalBlocks.POTTED_SCULK_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.DIANTHUS, GardenBotanicalBlocks.POTTED_DIANTHUS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.DARK_RED_PHLOX, GardenBotanicalBlocks.POTTED_DARK_RED_PHLOX, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.PINK_MATTHIOLA, GardenBotanicalBlocks.POTTED_PINK_MATTHIOLA, BlockStateModelGenerator.TintType.NOT_TINTED);

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
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.SNOW_HYDRANGEA_CROP, GardenBotanicalBlocks.SNOW_HYDRANGEA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.CHICORY_CROP, GardenBotanicalBlocks.CHICORY, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.IVY_CROP, GardenBotanicalBlocks.IVY, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.AQUILEGIA_CROP, GardenBotanicalBlocks.AQUILEGIA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP_CROP, GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.DRY_VIOLA_CROP, GardenBotanicalBlocks.DRY_VIOLA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.BIG_DANDELION_CROP, GardenBotanicalBlocks.BIG_DANDELION, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.AHSOKA_CROP, GardenBotanicalBlocks.AHSOKA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.BURGUNDY_ROSE_CROP, GardenBotanicalBlocks.BURGUNDY_ROSE, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.CALENDULA_CROP, GardenBotanicalBlocks.CALENDULA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.STRONGYLODON_CROP, GardenBotanicalBlocks.STRONGYLODON, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.SCULK_FLOWER_CROP, GardenBotanicalBlocks.SCULK_FLOWER, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.DIANTHUS_CROP, GardenBotanicalBlocks.DIANTHUS, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.DARK_RED_PHLOX_CROP, GardenBotanicalBlocks.DARK_RED_PHLOX, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
        registerGrowingFlower(blockStateModelGenerator, GardenBotanicalBlocks.PINK_MATTHIOLA_CROP, GardenBotanicalBlocks.PINK_MATTHIOLA, GrowingFlower.AGE, 0, 1, 2, 3, 4, 5);
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
        itemModelGenerator.register(GardenBotanicalItems.SNOW_HYDRANGEA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.CHICORY_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.IVY_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.AQUILEGIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BLUE_MOUNTAIN_TULIP_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.DRY_VIOLA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BIG_DANDELION_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.AHSOKA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BURGUNDY_ROSE_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.CALENDULA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.STRONGYLODON_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.SCULK_FLOWER_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.DIANTHUS_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.DARK_RED_PHLOX_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.PINK_MATTHIOLA_PETAL, Models.GENERATED);

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
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_SNOW_HYDRANGEA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_CHICORY, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_IVY, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_AQUILEGIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BLUE_MOUNTAIN_TULIP, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_DRY_VIOLA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BIG_DANDELION, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_AHSOKA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BURGUNDY_ROSE, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_CALENDULA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_STRONGYLODON, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_SCULK_FLOWER, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_DIANTHUS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_DARK_RED_PHLOX, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_PINK_MATTHIOLA, Models.GENERATED);
    }

    private void registerGrowingFlower(BlockStateModelGenerator blockStateModelGenerator, Block crop, Block flower, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier flowerId = Registries.BLOCK.getId(flower);
                Identifier identifier = (Identifier) int2ObjectMap.computeIfAbsent(i, (j) -> {
                    if (i == ageTextureIndices.length - 1) {
                        return createSubModel(blockStateModelGenerator, crop, "block/" + flowerId.getPath(), "_stage" + i, Models.CROSS, TextureMap::cross);
                    } else {
                        return createSubModel(blockStateModelGenerator, crop, "block/" + flowerId.getPath() + "_stage" + i, "_stage" + i, Models.CROSS, TextureMap::cross);
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
