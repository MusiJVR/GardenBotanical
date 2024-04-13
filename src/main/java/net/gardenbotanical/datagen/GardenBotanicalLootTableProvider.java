package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;


public class GardenBotanicalLootTableProvider extends FabricBlockLootTableProvider {
    public static final LootCondition.Builder WITH_GARDEN_PRUNER = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(GardenBotanicalItems.GARDEN_PRUNER));

    public GardenBotanicalLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(GardenBotanicalBlocks.BOUVARDIA, createPool(GardenBotanicalBlocks.BOUVARDIA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlocks.VERONICA, createPool(GardenBotanicalBlocks.VERONICA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlocks.BRUNIA, createPool(GardenBotanicalBlocks.BRUNIA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlocks.GERBERA, createPool(GardenBotanicalBlocks.GERBERA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlocks.HERBAL_PEONY, createPool(GardenBotanicalBlocks.HERBAL_PEONY, WITH_GARDEN_PRUNER, 1.0f));

        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_BOUVARDIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_VERONICA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_BRUNIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_GERBERA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_HERBAL_PEONY);
    }

    private LootTable.Builder createPool(Block entryBlock, LootCondition.Builder condition, float rolls) {
        return LootTable.builder().pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(rolls))
                .conditionally(condition)
                .with(ItemEntry.builder(entryBlock)));
    }
}
