package net.gardenbotanical.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.item.tool.GardenPrunerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalItem {
    public static final Item GARDEN_PRUNER = registerItem("garden_pruner", new GardenPrunerItem(new FabricItemSettings().maxDamage(64)));

    public static final Item BOUVARDIA_PETAL = registerItem("bouvardia_petal", new Item(new FabricItemSettings()));
    public static final Item BRUNIA_PETAL = registerItem("brunia_petal", new Item(new FabricItemSettings()));
    public static final Item GERBERA_PETAL = registerItem("gerbera_petal", new Item(new FabricItemSettings()));
    public static final Item HERBAL_PEONY_PETAL = registerItem("herbal_peony_petal", new Item(new FabricItemSettings()));
    public static final Item VERONICA_PETAL = registerItem("veronica_petal", new Item(new FabricItemSettings()));

    public static final Item POWDERED_BOUVARDIA = registerItem("powdered_bouvardia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_BRUNIA = registerItem("powdered_brunia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_GERBERA = registerItem("powdered_gerbera", new Item(new FabricItemSettings()));
    public static final Item POWDERED_HERBAL_PEONY = registerItem("powdered_herbal_peony", new Item(new FabricItemSettings()));
    public static final Item POWDERED_VERONICA = registerItem("powdered_veronica", new Item(new FabricItemSettings()));

    public static final Item BOUVARDIA_SEEDS = registerItem("bouvardia_seeds", new Item(new FabricItemSettings()));
    public static final Item BRUNIA_SEEDS = registerItem("brunia_seeds", new Item(new FabricItemSettings()));
    public static final Item GERBERA_SEEDS = registerItem("gerbera_seeds", new Item(new FabricItemSettings()));
    public static final Item HERBAL_PEONY_SEEDS = registerItem("herbal_peony_seeds", new Item(new FabricItemSettings()));
    public static final Item VERONICA_SEEDS = registerItem("veronica_seeds", new Item(new FabricItemSettings()));

    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(GardenBotanical.MOD_ID, id), item);
    }

    public static void registerItem(String id, Item item, RegistryKey<ItemGroup> group) {
        Item registeredItem = registerItem(id, item);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(registeredItem));
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering Items for " + GardenBotanical.MOD_ID);
    }
}
