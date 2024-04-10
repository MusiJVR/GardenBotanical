package net.gardenbotanical.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.GardenBotanicalItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalBlock {
    public static final Block BOUVARDIA = registerBlock("bouvardia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block POTTED_BOUVARDIA = Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, "potted_bouvardia"), new FlowerPotBlock(BOUVARDIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
    public static final Block BRUNIA = registerBlock("brunia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block POTTED_BRUNIA = Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, "potted_brunia"), new FlowerPotBlock(BRUNIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
    public static final Block GERBERA = registerBlock("gerbera", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block POTTED_GERBERA = Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, "potted_gerbera"), new FlowerPotBlock(GERBERA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
    public static final Block PEONY = registerBlock("peony", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block POTTED_PEONY = Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, "potted_peony"), new FlowerPotBlock(PEONY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));
    public static final Block VERONICA = registerBlock("veronica", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY)));
    public static final Block POTTED_VERONICA = Registry.register(Registries.BLOCK, new Identifier(GardenBotanical.MOD_ID, "potted_veronica"), new FlowerPotBlock(VERONICA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));

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
