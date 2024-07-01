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


public class GardenBotanicalBlockEntities {
    public static BlockEntityType<PreparationTableBlockEntity> PREPARATION_TABLE_BLOCK_ENTITY = registerBlockEntity(GardenBotanicalBlocks.PREPARATION_TABLE, PreparationTableBlockEntity::new);
    public static BlockEntityType<PoundingTableBlockEntity> POUNDING_TABLE_BLOCK_ENTITY = registerBlockEntity(GardenBotanicalBlocks.POUNDING_TABLE, PoundingTableBlockEntity::new);
    public static BlockEntityType<DyeMixerBlockEntity> DYE_MIXER_BLOCK_ENTITY = registerBlockEntity(GardenBotanicalBlocks.DYE_MIXER, DyeMixerBlockEntity::new);
    public static BlockEntityType<ColorizerBlockEntity> COLORIZER_BLOCK_ENTITY = registerBlockEntity(GardenBotanicalBlocks.COLORIZER, ColorizerBlockEntity::new);

    public static<T extends BlockEntity> BlockEntityType<T> registerBlockEntity(Block block, FabricBlockEntityTypeBuilder.Factory<T> factory) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Registries.BLOCK.getId(block).withSuffixedPath("_block_entity"), FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering block entities for: " + GardenBotanical.MOD_ID);
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, DYE_MIXER_BLOCK_ENTITY);
    }
}
