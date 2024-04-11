package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.gardenbotanical.item.GardenBotanicalItem;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;


public class GardenBotanicalModelProvider extends FabricModelProvider {
    public GardenBotanicalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlock.GERBERA, GardenBotanicalBlock.POTTED_GERBERA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlock.BRUNIA, GardenBotanicalBlock.POTTED_BRUNIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlock.HERBAL_PEONY, GardenBotanicalBlock.POTTED_HERBAL_PEONY, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlock.VERONICA, GardenBotanicalBlock.POTTED_VERONICA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(GardenBotanicalBlock.BOUVARDIA, GardenBotanicalBlock.POTTED_BOUVARDIA, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GardenBotanicalItem.BOUVARDIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.HERBAL_PEONY_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.BRUNIA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.GERBERA_PETAL, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.VERONICA_PETAL, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItem.POWDERED_BOUVARDIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.POWDERED_BRUNIA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.POWDERED_GERBERA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.POWDERED_VERONICA, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.POWDERED_HERBAL_PEONY, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItem.BOUVARDIA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.BRUNIA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.GERBERA_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.HERBAL_PEONY_SEEDS, Models.GENERATED);
        itemModelGenerator.register(GardenBotanicalItem.VERONICA_SEEDS, Models.GENERATED);

        itemModelGenerator.register(GardenBotanicalItem.GARDEN_PRUNER, Models.GENERATED);
    }
}
