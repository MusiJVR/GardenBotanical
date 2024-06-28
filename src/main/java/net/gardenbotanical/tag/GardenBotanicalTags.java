package net.gardenbotanical.tag;

import net.gardenbotanical.GardenBotanical;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalTags {
    public static final TagKey<Item> FLOWER_POWDERS = registerItemTag("flower_powders");
    public static final TagKey<Item> COLORIZER_ITEM_TYPES = registerItemTag("colorizer_item_types");
    public static final TagKey<Item> COLORIZER_ARMOR_TYPES = registerItemTag("colorizer_armor_types");
    public static final TagKey<Item> COLORIZER_HELMET = registerItemTag("colorizer_helmet");
    public static final TagKey<Item> COLORIZER_CHESTPLATE = registerItemTag("colorizer_chestplate");
    public static final TagKey<Item> COLORIZER_LEGGINGS = registerItemTag("colorizer_leggings");
    public static final TagKey<Item> COLORIZER_BOOTS = registerItemTag("colorizer_boots");
    public static final TagKey<Item> COLORIZER_HORSE_ARMOR = registerItemTag("colorizer_horse_armor");

    public static TagKey<Item> registerItemTag(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(GardenBotanical.MOD_ID, id));
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering tags for: " + GardenBotanical.MOD_ID);
    }
}
