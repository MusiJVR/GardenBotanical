package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlockEntities {
    public static BlockEntityType<PreparationTableBlockEntity> PREPARATION_TABLE_BLOCK_ENTITY = registerBlockEntity("preparation_table_block_entity", GardenBotanicalBlocks.PREPARATION_TABLE, PreparationTableBlockEntity::new);
    public static BlockEntityType<PoundingTableBlockEntity> POUNDING_TABLE_BLOCK_ENTITY = registerBlockEntity("pounding_table_block_entity", GardenBotanicalBlocks.POUNDING_TABLE, PoundingTableBlockEntity::new);
    public static BlockEntityType<DyeMixerBlockEntity> DYE_MIXER_BLOCK_ENTITY = registerBlockEntity("dye_mixer_block_entity", GardenBotanicalBlocks.DYE_MIXER, DyeMixerBlockEntity::new);
    public static BlockEntityType<ColorizerBlockEntity> COLORIZER_BLOCK_ENTITY = registerBlockEntity("colorizer_block_entity", GardenBotanicalBlocks.COLORIZER, ColorizerBlockEntity::new);

    public static<T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, Block block, FabricBlockEntityTypeBuilder.Factory<T> factory) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(GardenBotanical.MOD_ID, id), FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering block entities for: " + GardenBotanical.MOD_ID);
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, DYE_MIXER_BLOCK_ENTITY);
    }
}