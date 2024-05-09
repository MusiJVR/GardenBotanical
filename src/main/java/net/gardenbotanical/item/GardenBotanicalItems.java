package net.gardenbotanical.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalItems {
    public static final Item DYE_MIXER = registerItem("dye_mixer", new DyeMixerItem(GardenBotanicalBlocks.DYE_MIXER, new FabricItemSettings()));
    public static final Item COLORIZER = registerItem("colorizer", new ColorizerItem(GardenBotanicalBlocks.COLORIZER, new FabricItemSettings()));
    public static final Item BLOCK_COLORIZER = registerItem("block_colorizer", new BlockColorizerItem(GardenBotanicalBlocks.BLOCK_COLORIZER, new FabricItemSettings()));

    public static final Item GARDEN_PRUNER = registerItem("garden_pruner", new GardenPrunerItem(new FabricItemSettings().maxDamage(64)));

    public static final Item WATER_DYE_MIXER = registerItem("water_dye_mixer", new Item(new FabricItemSettings()));
    public static final Item COLORIZER_DYE = registerItem("colorizer_dye", new Item(new FabricItemSettings()));

    public static final Item DYE = registerItem("dye", new Item(new FabricItemSettings()));

    public static final Item BOUVARDIA_PETAL = registerItem("bouvardia_petal", new Item(new FabricItemSettings()));
    public static final Item BRUNIA_PETAL = registerItem("brunia_petal", new Item(new FabricItemSettings()));
    public static final Item GERBERA_PETAL = registerItem("gerbera_petal", new Item(new FabricItemSettings()));
    public static final Item HERBAL_PEONY_PETAL = registerItem("herbal_peony_petal", new Item(new FabricItemSettings()));
    public static final Item VERONICA_PETAL = registerItem("veronica_petal", new Item(new FabricItemSettings()));
    public static final Item DULL_PINK_TULIP_PETAL = registerItem("dull_pink_tulip_petal", new Item(new FabricItemSettings()));
    public static final Item POINSETTIA_PETAL = registerItem("poinsettia_petal", new Item(new FabricItemSettings()));
    public static final Item SETARIA_PETAL = registerItem("setaria_petal", new Item(new FabricItemSettings()));
    public static final Item ALOE_TRASKI_PETAL = registerItem("aloe_traski_petal", new Item(new FabricItemSettings()));
    public static final Item ASTER_PETAL = registerItem("aster_petal", new Item(new FabricItemSettings()));

    public static final Item POWDERED_BOUVARDIA = registerItem("powdered_bouvardia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_BRUNIA = registerItem("powdered_brunia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_GERBERA = registerItem("powdered_gerbera", new Item(new FabricItemSettings()));
    public static final Item POWDERED_HERBAL_PEONY = registerItem("powdered_herbal_peony", new Item(new FabricItemSettings()));
    public static final Item POWDERED_VERONICA = registerItem("powdered_veronica", new Item(new FabricItemSettings()));
    public static final Item POWDERED_DULL_PINK_TULIP = registerItem("powdered_dull_pink_tulip", new Item(new FabricItemSettings()));
    public static final Item POWDERED_POINSETTIA = registerItem("powdered_poinsettia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_SETARIA = registerItem("powdered_setaria", new Item(new FabricItemSettings()));
    public static final Item POWDERED_ALOE_TRASKI = registerItem("powdered_aloe_traski", new Item(new FabricItemSettings()));
    public static final Item POWDERED_ASTER = registerItem("powdered_aster", new Item(new FabricItemSettings()));

    public static final Item BOUVARDIA_SEEDS = registerItem("bouvardia_seeds", new Item(new FabricItemSettings()));
    public static final Item BRUNIA_SEEDS = registerItem("brunia_seeds", new Item(new FabricItemSettings()));
    public static final Item GERBERA_SEEDS = registerItem("gerbera_seeds", new Item(new FabricItemSettings()));
    public static final Item HERBAL_PEONY_SEEDS = registerItem("herbal_peony_seeds", new Item(new FabricItemSettings()));
    public static final Item VERONICA_SEEDS = registerItem("veronica_seeds", new Item(new FabricItemSettings()));
    public static final Item DULL_PINK_TULIP_SEEDS = registerItem("dull_pink_tulip_seeds", new Item(new FabricItemSettings()));
    public static final Item POINSETTIA_SEEDS = registerItem("poinsettia_seeds", new Item(new FabricItemSettings()));
    public static final Item SETARIA_SEEDS = registerItem("setaria_seeds", new Item(new FabricItemSettings()));
    public static final Item ALOE_TRASKI_SEEDS = registerItem("aloe_traski_seeds", new Item(new FabricItemSettings()));
    public static final Item ASTER_SEEDS = registerItem("aster_seeds", new Item(new FabricItemSettings()));

    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(GardenBotanical.MOD_ID, id), item);
    }

    public static void registerItem(String id, Item item, RegistryKey<ItemGroup> group) {
        Item registeredItem = registerItem(id, item);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(registeredItem));
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering items for: " + GardenBotanical.MOD_ID);
    }
}