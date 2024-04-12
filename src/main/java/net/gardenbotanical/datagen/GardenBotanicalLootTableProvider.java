package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gardenbotanical.block.GardenBotanicalBlock;
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
        addDrop(GardenBotanicalBlock.BOUVARDIA, createPool(GardenBotanicalBlock.BOUVARDIA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.VERONICA, createPool(GardenBotanicalBlock.VERONICA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.BRUNIA, createPool(GardenBotanicalBlock.BRUNIA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.GERBERA, createPool(GardenBotanicalBlock.GERBERA, WITH_GARDEN_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.HERBAL_PEONY, createPool(GardenBotanicalBlock.HERBAL_PEONY, WITH_GARDEN_PRUNER, 1.0f));

        addPottedPlantDrops(GardenBotanicalBlock.POTTED_BOUVARDIA);
        addPottedPlantDrops(GardenBotanicalBlock.POTTED_VERONICA);
        addPottedPlantDrops(GardenBotanicalBlock.POTTED_BRUNIA);
        addPottedPlantDrops(GardenBotanicalBlock.POTTED_GERBERA);
        addPottedPlantDrops(GardenBotanicalBlock.POTTED_HERBAL_PEONY);
    }

    private LootTable.Builder createPool(Block entryBlock, LootCondition.Builder condition, float rolls) {
        return LootTable.builder().pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(rolls))
                .conditionally(condition)
                .with(ItemEntry.builder(entryBlock)));
    }
}
