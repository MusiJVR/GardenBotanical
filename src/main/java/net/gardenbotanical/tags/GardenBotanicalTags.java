package net.gardenbotanical.tags;

import net.gardenbotanical.GardenBotanical;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class GardenBotanicalTags {
    public static final TagKey<Item> FLOWER_POWDERS = registerItemTag("flower_powders");

    public static TagKey<Item> registerItemTag(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(GardenBotanical.MOD_ID, id));
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering tags for: " + GardenBotanical.MOD_ID);
    }
}
