package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.gardenbotanical.item.GardenBotanicalItem;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;


public class GardenBotanicalLootTableProvider extends FabricBlockLootTableProvider {
    public static final LootCondition.Builder WITH_PRUNER = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(GardenBotanicalItem.GARDEN_PRUNER));

    public GardenBotanicalLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(GardenBotanicalBlock.BOUVARDIA, createPool(GardenBotanicalBlock.BOUVARDIA, WITH_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.VERONICA, createPool(GardenBotanicalBlock.VERONICA, WITH_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.BRUNIA, createPool(GardenBotanicalBlock.BRUNIA, WITH_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.GERBERA, createPool(GardenBotanicalBlock.GERBERA, WITH_PRUNER, 1.0f));
        addDrop(GardenBotanicalBlock.HERBAL_PEONY, createPool(GardenBotanicalBlock.HERBAL_PEONY, WITH_PRUNER, 1.0f));
    }

    private LootTable.Builder createPool(Block entryBlock, LootCondition.Builder condition, float rolls) {
        return LootTable.builder().pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(rolls))
                .conditionally(condition)
                .with(ItemEntry.builder(entryBlock)));
    }
}
