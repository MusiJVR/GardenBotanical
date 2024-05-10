package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.GrowingFlower;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;


public class GardenBotanicalLootTableProvider extends FabricBlockLootTableProvider {
    public static final LootCondition.Builder WITH_GARDEN_PRUNER = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(GardenBotanicalItems.GARDEN_PRUNER));

    public GardenBotanicalLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(GardenBotanicalBlocks.BOUVARDIA, createPool(GardenBotanicalBlocks.BOUVARDIA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.BRUNIA, createPool(GardenBotanicalBlocks.BRUNIA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.GERBERA, createPool(GardenBotanicalBlocks.GERBERA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.HERBAL_PEONY, createPool(GardenBotanicalBlocks.HERBAL_PEONY, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.VERONICA, createPool(GardenBotanicalBlocks.VERONICA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.DULL_PINK_TULIP, createPool(GardenBotanicalBlocks.DULL_PINK_TULIP, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.POINSETTIA, createPool(GardenBotanicalBlocks.POINSETTIA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.SETARIA, createPool(GardenBotanicalBlocks.SETARIA, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.ALOE_TRASKI, createPool(GardenBotanicalBlocks.ALOE_TRASKI, 1.0f, WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.ASTER, createPool(GardenBotanicalBlocks.ASTER, 1.0f, WITH_GARDEN_PRUNER));

        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_BOUVARDIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_BRUNIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_GERBERA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_HERBAL_PEONY);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_VERONICA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_DULL_PINK_TULIP);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_POINSETTIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_SETARIA);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_ALOE_TRASKI);
        addPottedPlantDrops(GardenBotanicalBlocks.POTTED_ASTER);

        addDrop(GardenBotanicalBlocks.BOUVARDIA_CROP, createPool(GardenBotanicalBlocks.BOUVARDIA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.BOUVARDIA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.BRUNIA_CROP, createPool(GardenBotanicalBlocks.BRUNIA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.BRUNIA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.GERBERA_CROP, createPool(GardenBotanicalBlocks.GERBERA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.GERBERA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.HERBAL_PEONY_CROP, createPool(GardenBotanicalBlocks.HERBAL_PEONY, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.HERBAL_PEONY_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.VERONICA_CROP, createPool(GardenBotanicalBlocks.VERONICA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.VERONICA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.DULL_PINK_TULIP_CROP, createPool(GardenBotanicalBlocks.DULL_PINK_TULIP, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.DULL_PINK_TULIP_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.POINSETTIA_CROP, createPool(GardenBotanicalBlocks.POINSETTIA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.POINSETTIA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.SETARIA_CROP, createPool(GardenBotanicalBlocks.SETARIA, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.SETARIA_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.ALOE_TRASKI_CROP, createPool(GardenBotanicalBlocks.ALOE_TRASKI, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.ALOE_TRASKI_CROP), WITH_GARDEN_PRUNER));
        addDrop(GardenBotanicalBlocks.ASTER_CROP, createPool(GardenBotanicalBlocks.ASTER, 1.0f, createFlowerAgeCondition(GardenBotanicalBlocks.ASTER_CROP), WITH_GARDEN_PRUNER));
    }

    private LootTable.Builder createPool(Block entryBlock, float rolls, LootCondition.Builder... conditions) {
        LootPool.Builder pool = LootPool.builder().rolls(ConstantLootNumberProvider.create(rolls));

        for (LootCondition.Builder condition : conditions) {
            pool.conditionally(condition);
        }
        pool.with(ItemEntry.builder(entryBlock));

        return LootTable.builder().pool(pool);
    }

    private BlockStatePropertyLootCondition.Builder createFlowerAgeCondition(Block block) {
        return BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(GrowingFlower.AGE, GrowingFlower.MAX_AGE));
    }
}
