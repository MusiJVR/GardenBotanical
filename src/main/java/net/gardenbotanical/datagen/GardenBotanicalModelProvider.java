package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;


public class GardenBotanicalModelProvider extends FabricModelProvider {
    public GardenBotanicalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.GERBERA, GardenBotanicalBlocks.POTTED_GERBERA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BRUNIA, GardenBotanicalBlocks.POTTED_BRUNIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.HERBAL_PEONY, GardenBotanicalBlocks.POTTED_HERBAL_PEONY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.VERONICA, GardenBotanicalBlocks.POTTED_VERONICA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlocks.BOUVARDIA, GardenBotanicalBlocks.POTTED_BOUVARDIA, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GardenBotanicalItems.BOUVARDIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.HERBAL_PEONY_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BRUNIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.GERBERA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.VERONICA_PETAL, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BOUVARDIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_BRUNIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_GERBERA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_VERONICA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.POWDERED_HERBAL_PEONY, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.BOUVARDIA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.BRUNIA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.GERBERA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.HERBAL_PEONY_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItems.VERONICA_SEEDS, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.GARDEN_PRUNER, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItems.WATER_DYE_MIXER, Models.GENERATED);
    }
}
