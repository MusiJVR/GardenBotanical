package net.gardenbotanical.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class GardenBotanicalItemGroups {
    public static final ItemGroup GARDENBOTANICAL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(GardenBotanical.MOD_ID, "gardenbotanical_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gardenbotanical.gardenbotanical_group"))
                    .icon(() -> new ItemStack(GardenBotanicalBlocks.BOUVARDIA)).entries((displayContext, entries) -> {
                        entries.add(GardenBotanicalBlocks.PREPARATION_TABLE);
                        entries.add(GardenBotanicalBlocks.POUNDING_TABLE);
                        entries.add(GardenBotanicalItems.DYE_MIXER);
                        entries.add(GardenBotanicalItems.COLORIZER);

                        entries.add(GardenBotanicalItems.GARDEN_PRUNER);

                        entries.add(GardenBotanicalBlocks.BOUVARDIA);
                        entries.add(GardenBotanicalBlocks.BRUNIA);
                        entries.add(GardenBotanicalBlocks.GERBERA);
                        entries.add(GardenBotanicalBlocks.HERBAL_PEONY);
                        entries.add(GardenBotanicalBlocks.VERONICA);
                        entries.add(GardenBotanicalBlocks.DULL_PINK_TULIP);
                        entries.add(GardenBotanicalBlocks.POINSETTIA);
                        entries.add(GardenBotanicalBlocks.SETARIA);
                        entries.add(GardenBotanicalBlocks.ALOE_TRASKI);
                        entries.add(GardenBotanicalBlocks.ASTER);
                        entries.add(GardenBotanicalBlocks.SNOW_HYDRANGEA);
                        entries.add(GardenBotanicalBlocks.CHICORY);
                        entries.add(GardenBotanicalBlocks.IVY);
                        entries.add(GardenBotanicalBlocks.AQUILEGIA);
                        entries.add(GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP);
                        entries.add(GardenBotanicalBlocks.DRY_VIOLA);
                        entries.add(GardenBotanicalBlocks.BIG_DANDELION);
                        entries.add(GardenBotanicalBlocks.AHSOKA);
                        entries.add(GardenBotanicalBlocks.BURGUNDY_ROSE);
                        entries.add(GardenBotanicalBlocks.CALENDULA);
                        entries.add(GardenBotanicalBlocks.STRONGYLODON);
                        entries.add(GardenBotanicalBlocks.SCULK_FLOWER);
                        entries.add(GardenBotanicalBlocks.DIANTHUS);
                        entries.add(GardenBotanicalBlocks.DARK_RED_PHLOX);
                        entries.add(GardenBotanicalBlocks.PINK_MATTHIOLA);

                        entries.add(GardenBotanicalItems.BOUVARDIA_PETAL);
                        entries.add(GardenBotanicalItems.BRUNIA_PETAL);
                        entries.add(GardenBotanicalItems.GERBERA_PETAL);
                        entries.add(GardenBotanicalItems.HERBAL_PEONY_PETAL);
                        entries.add(GardenBotanicalItems.VERONICA_PETAL);
                        entries.add(GardenBotanicalItems.DULL_PINK_TULIP_PETAL);
                        entries.add(GardenBotanicalItems.POINSETTIA_PETAL);
                        entries.add(GardenBotanicalItems.SETARIA_PETAL);
                        entries.add(GardenBotanicalItems.ALOE_TRASKI_PETAL);
                        entries.add(GardenBotanicalItems.ASTER_PETAL);
                        entries.add(GardenBotanicalItems.SNOW_HYDRANGEA_PETAL);
                        entries.add(GardenBotanicalItems.CHICORY_PETAL);
                        entries.add(GardenBotanicalItems.IVY_PETAL);
                        entries.add(GardenBotanicalItems.AQUILEGIA_PETAL);
                        entries.add(GardenBotanicalItems.BLUE_MOUNTAIN_TULIP_PETAL);
                        entries.add(GardenBotanicalItems.DRY_VIOLA_PETAL);
                        entries.add(GardenBotanicalItems.BIG_DANDELION_PETAL);
                        entries.add(GardenBotanicalItems.AHSOKA_PETAL);
                        entries.add(GardenBotanicalItems.BURGUNDY_ROSE_PETAL);
                        entries.add(GardenBotanicalItems.CALENDULA_PETAL);
                        entries.add(GardenBotanicalItems.STRONGYLODON_PETAL);
                        entries.add(GardenBotanicalItems.SCULK_FLOWER_PETAL);
                        entries.add(GardenBotanicalItems.DIANTHUS_PETAL);
                        entries.add(GardenBotanicalItems.DARK_RED_PHLOX_PETAL);
                        entries.add(GardenBotanicalItems.PINK_MATTHIOLA_PETAL);

                        entries.add(GardenBotanicalItems.POWDERED_BOUVARDIA);
                        entries.add(GardenBotanicalItems.POWDERED_BRUNIA);
                        entries.add(GardenBotanicalItems.POWDERED_GERBERA);
                        entries.add(GardenBotanicalItems.POWDERED_HERBAL_PEONY);
                        entries.add(GardenBotanicalItems.POWDERED_VERONICA);
                        entries.add(GardenBotanicalItems.POWDERED_DULL_PINK_TULIP);
                        entries.add(GardenBotanicalItems.POWDERED_POINSETTIA);
                        entries.add(GardenBotanicalItems.POWDERED_SETARIA);
                        entries.add(GardenBotanicalItems.POWDERED_ALOE_TRASKI);
                        entries.add(GardenBotanicalItems.POWDERED_ASTER);
                        entries.add(GardenBotanicalItems.POWDERED_SNOW_HYDRANGEA);
                        entries.add(GardenBotanicalItems.POWDERED_CHICORY);
                        entries.add(GardenBotanicalItems.POWDERED_IVY);
                        entries.add(GardenBotanicalItems.POWDERED_AQUILEGIA);
                        entries.add(GardenBotanicalItems.POWDERED_BLUE_MOUNTAIN_TULIP);
                        entries.add(GardenBotanicalItems.POWDERED_DRY_VIOLA);
                        entries.add(GardenBotanicalItems.POWDERED_BIG_DANDELION);
                        entries.add(GardenBotanicalItems.POWDERED_AHSOKA);
                        entries.add(GardenBotanicalItems.POWDERED_BURGUNDY_ROSE);
                        entries.add(GardenBotanicalItems.POWDERED_CALENDULA);
                        entries.add(GardenBotanicalItems.POWDERED_STRONGYLODON);
                        entries.add(GardenBotanicalItems.POWDERED_SCULK_FLOWER);
                        entries.add(GardenBotanicalItems.POWDERED_DIANTHUS);
                        entries.add(GardenBotanicalItems.POWDERED_DARK_RED_PHLOX);
                        entries.add(GardenBotanicalItems.POWDERED_PINK_MATTHIOLA);

                        entries.add(GardenBotanicalItems.BOUVARDIA_SEEDS);
                        entries.add(GardenBotanicalItems.BRUNIA_SEEDS);
                        entries.add(GardenBotanicalItems.GERBERA_SEEDS);
                        entries.add(GardenBotanicalItems.HERBAL_PEONY_SEEDS);
                        entries.add(GardenBotanicalItems.VERONICA_SEEDS);
                        entries.add(GardenBotanicalItems.DULL_PINK_TULIP_SEEDS);
                        entries.add(GardenBotanicalItems.POINSETTIA_SEEDS);
                        entries.add(GardenBotanicalItems.SETARIA_SEEDS);
                        entries.add(GardenBotanicalItems.ALOE_TRASKI_SEEDS);
                        entries.add(GardenBotanicalItems.ASTER_SEEDS);
                        entries.add(GardenBotanicalItems.SNOW_HYDRANGEA_SEEDS);
                        entries.add(GardenBotanicalItems.CHICORY_SEEDS);
                        entries.add(GardenBotanicalItems.IVY_SEEDS);
                        entries.add(GardenBotanicalItems.AQUILEGIA_SEEDS);
                        entries.add(GardenBotanicalItems.BLUE_MOUNTAIN_TULIP_SEEDS);
                        entries.add(GardenBotanicalItems.DRY_VIOLA_SEEDS);
                        entries.add(GardenBotanicalItems.BIG_DANDELION_SEEDS);
                        entries.add(GardenBotanicalItems.AHSOKA_SEEDS);
                        entries.add(GardenBotanicalItems.BURGUNDY_ROSE_SEEDS);
                        entries.add(GardenBotanicalItems.CALENDULA_SEEDS);
                        entries.add(GardenBotanicalItems.STRONGYLODON_SEEDS);
                        entries.add(GardenBotanicalItems.SCULK_FLOWER_SEEDS);
                        entries.add(GardenBotanicalItems.DIANTHUS_SEEDS);
                        entries.add(GardenBotanicalItems.DARK_RED_PHLOX_SEEDS);
                        entries.add(GardenBotanicalItems.PINK_MATTHIOLA_SEEDS);
                    }).build());

    public static void register() {
        GardenBotanical.LOGGER.info("Registering item groups for: " + GardenBotanical.MOD_ID);
    }
}
