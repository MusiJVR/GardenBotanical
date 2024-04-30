package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlockEntities {
    public static final BlockEntityType<PreparationTableBlockEntity> PREPARATION_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GardenBotanical.MOD_ID, "preparation_table_block_entity"),
                    FabricBlockEntityTypeBuilder.create(PreparationTableBlockEntity::new,
                            GardenBotanicalBlocks.PREPARATION_TABLE).build());

    public static final BlockEntityType<PoundingTableBlockEntity> POUNDING_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GardenBotanical.MOD_ID, "pounding_table_block_entity"),
                    FabricBlockEntityTypeBuilder.create(PoundingTableBlockEntity::new,
                            GardenBotanicalBlocks.POUNDING_TABLE).build());

    public static BlockEntityType<DyeMixerBlockEntity> DYE_MIXER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GardenBotanical.MOD_ID, "dye_mixer_block_entity"),
                    FabricBlockEntityTypeBuilder.create(DyeMixerBlockEntity::new,
                            GardenBotanicalBlocks.DYE_MIXER).build());

    public static void register() {
        GardenBotanical.LOGGER.info("Registering block entities for: " + GardenBotanical.MOD_ID);

        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, DYE_MIXER_BLOCK_ENTITY);
    }
}