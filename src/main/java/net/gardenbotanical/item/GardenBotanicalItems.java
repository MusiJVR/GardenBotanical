package net.gardenbotanical.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.custom.*;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class GardenBotanicalItems {
    public static final Item DYE_MIXER = registerItem("dye_mixer", new DyeMixerItem(GardenBotanicalBlocks.DYE_MIXER, new FabricItemSettings()));
    public static final Item COLORIZER = registerItem("colorizer", new ColorizerItem(GardenBotanicalBlocks.COLORIZER, new FabricItemSettings()));

    public static final Item GARDEN_PRUNER = registerItem("garden_pruner", new GardenPrunerItem(new FabricItemSettings().maxDamage(64)));
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
    public static final Item SNOW_HYDRANGEA_PETAL = registerItem("snow_hydrangea_petal", new Item(new FabricItemSettings()));
    public static final Item CHICORY_PETAL = registerItem("chicory_petal", new Item(new FabricItemSettings()));
    public static final Item IVY_PETAL = registerItem("ivy_petal", new Item(new FabricItemSettings()));
    public static final Item AQUILEGIA_PETAL = registerItem("aquilegia_petal", new Item(new FabricItemSettings()));
    public static final Item BLUE_MOUNTAIN_TULIP_PETAL = registerItem("blue_mountain_tulip_petal", new Item(new FabricItemSettings()));
    public static final Item DRY_VIOLA_PETAL = registerItem("dry_viola_petal", new Item(new FabricItemSettings()));
    public static final Item BIG_DANDELION_PETAL = registerItem("big_dandelion_petal", new Item(new FabricItemSettings()));
    public static final Item AHSOKA_PETAL = registerItem("ahsoka_petal", new Item(new FabricItemSettings()));
    public static final Item BURGUNDY_ROSE_PETAL = registerItem("burgundy_rose_petal", new Item(new FabricItemSettings()));
    public static final Item CALENDULA_PETAL = registerItem("calendula_petal", new Item(new FabricItemSettings()));
    public static final Item STRONGYLODON_PETAL = registerItem("strongylodon_petal", new Item(new FabricItemSettings()));
    public static final Item SCULK_FLOWER_PETAL = registerItem("sculk_flower_petal", new Item(new FabricItemSettings()));
    public static final Item DIANTHUS_PETAL = registerItem("dianthus_petal", new Item(new FabricItemSettings()));
    public static final Item DARK_RED_PHLOX_PETAL = registerItem("dark_red_phlox_petal", new Item(new FabricItemSettings()));
    public static final Item PINK_MATTHIOLA_PETAL = registerItem("pink_matthiola_petal", new Item(new FabricItemSettings()));

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
    public static final Item POWDERED_SNOW_HYDRANGEA = registerItem("powdered_snow_hydrangea", new Item(new FabricItemSettings()));
    public static final Item POWDERED_CHICORY = registerItem("powdered_chicory", new Item(new FabricItemSettings()));
    public static final Item POWDERED_IVY = registerItem("powdered_ivy", new Item(new FabricItemSettings()));
    public static final Item POWDERED_AQUILEGIA = registerItem("powdered_aquilegia", new Item(new FabricItemSettings()));
    public static final Item POWDERED_BLUE_MOUNTAIN_TULIP = registerItem("powdered_blue_mountain_tulip", new Item(new FabricItemSettings()));
    public static final Item POWDERED_DRY_VIOLA = registerItem("powdered_dry_viola", new Item(new FabricItemSettings()));
    public static final Item POWDERED_BIG_DANDELION = registerItem("powdered_big_dandelion", new Item(new FabricItemSettings()));
    public static final Item POWDERED_AHSOKA = registerItem("powdered_ahsoka", new Item(new FabricItemSettings()));
    public static final Item POWDERED_BURGUNDY_ROSE = registerItem("powdered_burgundy_rose", new Item(new FabricItemSettings()));
    public static final Item POWDERED_CALENDULA = registerItem("powdered_calendula", new Item(new FabricItemSettings()));
    public static final Item POWDERED_STRONGYLODON = registerItem("powdered_strongylodon", new Item(new FabricItemSettings()));
    public static final Item POWDERED_SCULK_FLOWER = registerItem("powdered_sculk_flower", new Item(new FabricItemSettings()));
    public static final Item POWDERED_DIANTHUS = registerItem("powdered_dianthus", new Item(new FabricItemSettings()));
    public static final Item POWDERED_DARK_RED_PHLOX = registerItem("powdered_dark_red_phlox", new Item(new FabricItemSettings()));
    public static final Item POWDERED_PINK_MATTHIOLA = registerItem("powdered_pink_matthiola", new Item(new FabricItemSettings()));

    public static final Item BOUVARDIA_SEEDS = registerItem("bouvardia_seeds", new AliasedBlockItem(GardenBotanicalBlocks.BOUVARDIA_CROP, new FabricItemSettings()));
    public static final Item BRUNIA_SEEDS = registerItem("brunia_seeds", new AliasedBlockItem(GardenBotanicalBlocks.BRUNIA_CROP, new FabricItemSettings()));
    public static final Item GERBERA_SEEDS = registerItem("gerbera_seeds", new AliasedBlockItem(GardenBotanicalBlocks.GERBERA_CROP, new FabricItemSettings()));
    public static final Item HERBAL_PEONY_SEEDS = registerItem("herbal_peony_seeds", new AliasedBlockItem(GardenBotanicalBlocks.HERBAL_PEONY_CROP, new FabricItemSettings()));
    public static final Item VERONICA_SEEDS = registerItem("veronica_seeds", new AliasedBlockItem(GardenBotanicalBlocks.VERONICA_CROP, new FabricItemSettings()));
    public static final Item DULL_PINK_TULIP_SEEDS = registerItem("dull_pink_tulip_seeds", new AliasedBlockItem(GardenBotanicalBlocks.DULL_PINK_TULIP_CROP, new FabricItemSettings()));
    public static final Item POINSETTIA_SEEDS = registerItem("poinsettia_seeds", new AliasedBlockItem(GardenBotanicalBlocks.POINSETTIA_CROP, new FabricItemSettings()));
    public static final Item SETARIA_SEEDS = registerItem("setaria_seeds", new AliasedBlockItem(GardenBotanicalBlocks.SETARIA_CROP, new FabricItemSettings()));
    public static final Item ALOE_TRASKI_SEEDS = registerItem("aloe_traski_seeds", new AliasedBlockItem(GardenBotanicalBlocks.ALOE_TRASKI_CROP, new FabricItemSettings()));
    public static final Item ASTER_SEEDS = registerItem("aster_seeds", new AliasedBlockItem(GardenBotanicalBlocks.ASTER_CROP, new FabricItemSettings()));
    public static final Item SNOW_HYDRANGEA_SEEDS = registerItem("snow_hydrangea_seeds", new AliasedBlockItem(GardenBotanicalBlocks.SNOW_HYDRANGEA_CROP, new FabricItemSettings()));
    public static final Item CHICORY_SEEDS = registerItem("chicory_seeds", new AliasedBlockItem(GardenBotanicalBlocks.CHICORY_CROP, new FabricItemSettings()));
    public static final Item IVY_SEEDS = registerItem("ivy_seeds", new AliasedBlockItem(GardenBotanicalBlocks.IVY_CROP, new FabricItemSettings()));
    public static final Item AQUILEGIA_SEEDS = registerItem("aquilegia_seeds", new AliasedBlockItem(GardenBotanicalBlocks.AQUILEGIA_CROP, new FabricItemSettings()));
    public static final Item BLUE_MOUNTAIN_TULIP_SEEDS = registerItem("blue_mountain_tulip_seeds", new AliasedBlockItem(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP_CROP, new FabricItemSettings()));
    public static final Item DRY_VIOLA_SEEDS = registerItem("dry_viola_seeds", new AliasedBlockItem(GardenBotanicalBlocks.DRY_VIOLA_CROP, new FabricItemSettings()));
    public static final Item BIG_DANDELION_SEEDS = registerItem("big_dandelion_seeds", new AliasedBlockItem(GardenBotanicalBlocks.BIG_DANDELION_CROP, new FabricItemSettings()));
    public static final Item AHSOKA_SEEDS = registerItem("ahsoka_seeds", new AliasedBlockItem(GardenBotanicalBlocks.AHSOKA_CROP, new FabricItemSettings()));
    public static final Item BURGUNDY_ROSE_SEEDS = registerItem("burgundy_rose_seeds", new AliasedBlockItem(GardenBotanicalBlocks.BURGUNDY_ROSE_CROP, new FabricItemSettings()));
    public static final Item CALENDULA_SEEDS = registerItem("calendula_seeds", new AliasedBlockItem(GardenBotanicalBlocks.CALENDULA_CROP, new FabricItemSettings()));
    public static final Item STRONGYLODON_SEEDS = registerItem("strongylodon_seeds", new AliasedBlockItem(GardenBotanicalBlocks.STRONGYLODON_CROP, new FabricItemSettings()));
    public static final Item SCULK_FLOWER_SEEDS = registerItem("sculk_flower_seeds", new AliasedBlockItem(GardenBotanicalBlocks.SCULK_FLOWER_CROP, new FabricItemSettings()));
    public static final Item DIANTHUS_SEEDS = registerItem("dianthus_seeds", new AliasedBlockItem(GardenBotanicalBlocks.DIANTHUS_CROP, new FabricItemSettings()));
    public static final Item DARK_RED_PHLOX_SEEDS = registerItem("dark_red_phlox_seeds", new AliasedBlockItem(GardenBotanicalBlocks.DARK_RED_PHLOX_CROP, new FabricItemSettings()));
    public static final Item PINK_MATTHIOLA_SEEDS = registerItem("pink_matthiola_seeds", new AliasedBlockItem(GardenBotanicalBlocks.PINK_MATTHIOLA_CROP, new FabricItemSettings()));

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
