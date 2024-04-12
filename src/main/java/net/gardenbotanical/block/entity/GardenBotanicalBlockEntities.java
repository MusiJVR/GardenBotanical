package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlockEntities {
    public static final BlockEntityType<PreparationTableBlockEntity> PREPARATION_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GardenBotanical.MOD_ID, "preparation_table_block_entity"),
                    FabricBlockEntityTypeBuilder.create(PreparationTableBlockEntity::new,
                            GardenBotanicalBlock.PREPARATION_TABLE).build());

    public static void registerBlockEntities() {
        GardenBotanical.LOGGER.info("Registering Block Entities for " + GardenBotanical.MOD_ID);
    }
}