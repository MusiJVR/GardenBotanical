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
                    }).build());

    public static void register() {
        GardenBotanical.LOGGER.info("Registering item groups for: " + GardenBotanical.MOD_ID);
    }
}
