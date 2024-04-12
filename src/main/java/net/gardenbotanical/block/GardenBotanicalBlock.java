package net.gardenbotanical.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlock {
    public static final Block PREPARATION_TABLE = registerBlock("preparation_table", new PreparationTableBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block BOUVARDIA = registerBlock("bouvardia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POTTED_BOUVARDIA = registerBlockNoItem("potted_bouvardia", new FlowerPotBlock(BOUVARDIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block BRUNIA = registerBlock("brunia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POTTED_BRUNIA = registerBlockNoItem("potted_brunia", new FlowerPotBlock(BRUNIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block GERBERA = registerBlock("gerbera", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POTTED_GERBERA = registerBlockNoItem("potted_gerbera", new FlowerPotBlock(GERBERA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block HERBAL_PEONY = registerBlock("herbal_peony", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POTTED_HERBAL_PEONY = registerBlockNoItem("potted_herbal_peony", new FlowerPotBlock(HERBAL_PEONY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block VERONICA = registerBlock("veronica", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POTTED_VERONICA = registerBlockNoItem("potted_veronica", new FlowerPotBlock(VERONICA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));

    private static Block registerBlock(String id, Block block) {
        GardenBotanicalItems.registerItem(id, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, id), block);
    }

    private static Block registerBlockNoItem(String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, id), block);
    }

    private static Block registerBlockWithVanillaGroup(String id, Block block, RegistryKey<ItemGroup> group) {
        GardenBotanicalItems.registerItem(id, new BlockItem(block, new Item.Settings()), group);
        return Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, id), block);
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering Blocks for: " + GardenBotanical.MOD_ID);
    }
}