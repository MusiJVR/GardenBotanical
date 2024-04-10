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
