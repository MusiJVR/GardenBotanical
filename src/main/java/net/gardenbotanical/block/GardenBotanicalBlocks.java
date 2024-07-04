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


public class GardenBotanicalBlocks {
    public static final Block PREPARATION_TABLE = registerBlock("preparation_table", new PreparationTableBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block POUNDING_TABLE = registerBlock("pounding_table", new PoundingTableBlock(FabricBlockSettings.copyOf(Blocks.GRINDSTONE).nonOpaque()));
    public static final Block DYE_MIXER = registerBlockNoItem("dye_mixer", new DyeMixerBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block COLORIZER = registerBlockNoItem("colorizer", new ColorizerBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE).nonOpaque()));

    public static final Block BOUVARDIA = registerBlock("bouvardia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block BRUNIA = registerBlock("brunia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block GERBERA = registerBlock("gerbera", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block HERBAL_PEONY = registerBlock("herbal_peony", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block VERONICA = registerBlock("veronica", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block DULL_PINK_TULIP = registerBlock("dull_pink_tulip", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block POINSETTIA = registerBlock("poinsettia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block SETARIA = registerBlock("setaria", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block ALOE_TRASKI = registerBlock("aloe_traski", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block ASTER = registerBlock("aster", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block SNOW_HYDRANGEA = registerBlock("snow_hydrangea", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block CHICORY = registerBlock("chicory", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block IVY = registerBlock("ivy", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block AQUILEGIA = registerBlock("aquilegia", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block BLUE_MOUNTAIN_TULIP = registerBlock("blue_mountain_tulip", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block DRY_VIOLA = registerBlock("dry_viola", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block BIG_DANDELION = registerBlock("big_dandelion", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block AHSOKA = registerBlock("ahsoka", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block BURGUNDY_ROSE = registerBlock("burgundy_rose", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block CALENDULA = registerBlock("calendula", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block STRONGYLODON = registerBlock("strongylodon", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block SCULK_FLOWER = registerBlock("sculk_flower", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block DIANTHUS = registerBlock("dianthus", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block DARK_RED_PHLOX = registerBlock("dark_red_phlox", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));
    public static final Block PINK_MATTHIOLA = registerBlock("pink_matthiola", new FlowerBlock(StatusEffects.LUCK, 0, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque()));

    public static final Block POTTED_BOUVARDIA = registerBlockNoItem("potted_bouvardia", new FlowerPotBlock(BOUVARDIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_BRUNIA = registerBlockNoItem("potted_brunia", new FlowerPotBlock(BRUNIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_GERBERA = registerBlockNoItem("potted_gerbera", new FlowerPotBlock(GERBERA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_HERBAL_PEONY = registerBlockNoItem("potted_herbal_peony", new FlowerPotBlock(HERBAL_PEONY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_VERONICA = registerBlockNoItem("potted_veronica", new FlowerPotBlock(VERONICA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_DULL_PINK_TULIP = registerBlockNoItem("potted_dull_pink_tulip", new FlowerPotBlock(DULL_PINK_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_POINSETTIA = registerBlockNoItem("potted_poinsettia", new FlowerPotBlock(POINSETTIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_SETARIA = registerBlockNoItem("potted_setaria", new FlowerPotBlock(SETARIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_ALOE_TRASKI = registerBlockNoItem("potted_aloe_traski", new FlowerPotBlock(ALOE_TRASKI, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_ASTER = registerBlockNoItem("potted_aster", new FlowerPotBlock(ASTER, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_SNOW_HYDRANGEA = registerBlockNoItem("potted_snow_hydrangea", new FlowerPotBlock(SNOW_HYDRANGEA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_CHICORY = registerBlockNoItem("potted_chicory", new FlowerPotBlock(CHICORY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_IVY = registerBlockNoItem("potted_ivy", new FlowerPotBlock(IVY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_AQUILEGIA = registerBlockNoItem("potted_aquilegia", new FlowerPotBlock(AQUILEGIA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_BLUE_MOUNTAIN_TULIP = registerBlockNoItem("potted_blue_mountain_tulip", new FlowerPotBlock(BLUE_MOUNTAIN_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_DRY_VIOLA = registerBlockNoItem("potted_dry_viola", new FlowerPotBlock(DRY_VIOLA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_BIG_DANDELION = registerBlockNoItem("potted_big_dandelion", new FlowerPotBlock(BIG_DANDELION, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_AHSOKA = registerBlockNoItem("potted_ahsoka", new FlowerPotBlock(AHSOKA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_BURGUNDY_ROSE = registerBlockNoItem("potted_burgundy_rose", new FlowerPotBlock(BURGUNDY_ROSE, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_CALENDULA = registerBlockNoItem("potted_calendula", new FlowerPotBlock(CALENDULA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_STRONGYLODON = registerBlockNoItem("potted_strongylodon", new FlowerPotBlock(STRONGYLODON, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_SCULK_FLOWER = registerBlockNoItem("potted_sculk_flower", new FlowerPotBlock(SCULK_FLOWER, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_DIANTHUS = registerBlockNoItem("potted_dianthus", new FlowerPotBlock(DIANTHUS, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_DARK_RED_PHLOX = registerBlockNoItem("potted_dark_red_phlox", new FlowerPotBlock(DARK_RED_PHLOX, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));
    public static final Block POTTED_PINK_MATTHIOLA = registerBlockNoItem("potted_pink_matthiola", new FlowerPotBlock(PINK_MATTHIOLA, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));

    public static final Block BOUVARDIA_CROP = registerBlockNoItem("bouvardia_crop", new GrowingFlower("bouvardia_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block BRUNIA_CROP = registerBlockNoItem("brunia_crop", new GrowingFlower("brunia_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block GERBERA_CROP = registerBlockNoItem("gerbera_crop", new GrowingFlower("gerbera_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block HERBAL_PEONY_CROP = registerBlockNoItem("herbal_peony_crop", new GrowingFlower("herbal_peony_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block VERONICA_CROP = registerBlockNoItem("veronica_crop", new GrowingFlower("veronica_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block DULL_PINK_TULIP_CROP = registerBlockNoItem("dull_pink_tulip_crop", new GrowingFlower("dull_pink_tulip_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block POINSETTIA_CROP = registerBlockNoItem("poinsettia_crop", new GrowingFlower("poinsettia_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block SETARIA_CROP = registerBlockNoItem("setaria_crop", new GrowingFlower("setaria_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block ALOE_TRASKI_CROP = registerBlockNoItem("aloe_traski_crop", new GrowingFlower("aloe_traski_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block ASTER_CROP = registerBlockNoItem("aster_crop", new GrowingFlower("aster_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block SNOW_HYDRANGEA_CROP = registerBlockNoItem("snow_hydrangea_crop", new GrowingFlower("snow_hydrangea_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block CHICORY_CROP = registerBlockNoItem("chicory_crop", new GrowingFlower("chicory_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block IVY_CROP = registerBlockNoItem("ivy_crop", new GrowingFlower("ivy_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block AQUILEGIA_CROP = registerBlockNoItem("aquilegia_crop", new GrowingFlower("aquilegia_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block BLUE_MOUNTAIN_TULIP_CROP = registerBlockNoItem("blue_mountain_tulip_crop", new GrowingFlower("blue_mountain_tulip_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block DRY_VIOLA_CROP = registerBlockNoItem("dry_viola_crop", new GrowingFlower("dry_viola_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block BIG_DANDELION_CROP = registerBlockNoItem("big_dandelion_crop", new GrowingFlower("big_dandelion_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block AHSOKA_CROP = registerBlockNoItem("ahsoka_crop", new GrowingFlower("ahsoka_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block BURGUNDY_ROSE_CROP = registerBlockNoItem("burgundy_rose_crop", new GrowingFlower("burgundy_rose_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block CALENDULA_CROP = registerBlockNoItem("calendula_crop", new GrowingFlower("calendula_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block STRONGYLODON_CROP = registerBlockNoItem("strongylodon_crop", new GrowingFlower("strongylodon_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block SCULK_FLOWER_CROP = registerBlockNoItem("sculk_flower_crop", new GrowingFlower("sculk_flower_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block DIANTHUS_CROP = registerBlockNoItem("dianthus_crop", new GrowingFlower("dianthus_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block DARK_RED_PHLOX_CROP = registerBlockNoItem("dark_red_phlox_crop", new GrowingFlower("dark_red_phlox_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));
    public static final Block PINK_MATTHIOLA_CROP = registerBlockNoItem("pink_matthiola_crop", new GrowingFlower("pink_matthiola_seeds", FabricBlockSettings.copyOf(Blocks.WHEAT), Blocks.FARMLAND));

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
        GardenBotanical.LOGGER.info("Registering blocks for: " + GardenBotanical.MOD_ID);
    }
}
