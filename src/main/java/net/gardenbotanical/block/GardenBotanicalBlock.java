package net.gardenbotanical.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.GardenBotanicalItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlock {
    public static final Block BOUVARDIA = registerBlock("bouvardia", new Block(FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block BRUNIA = registerBlock("brunia", new Block(FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block GERBERA = registerBlock("gerbera", new Block(FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block PEONY = registerBlock("peony", new Block(FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block VERONICA = registerBlock("veronica", new Block(FabricBlockSettings.copyOf(Blocks.POPPY)));

    private static Block registerBlock(String id, Block block) {
        GardenBotanicalItem.registerItem(id, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, id), block);
    }

    private static Block registerBlock(String id, Block block, RegistryKey<ItemGroup> group) {
        GardenBotanicalItem.registerItem(id, new BlockItem(block, new Item.Settings()), group);
        return Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, id), block);
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering blocks for: " + GardenBotanical.MOD_ID);
    }
}
