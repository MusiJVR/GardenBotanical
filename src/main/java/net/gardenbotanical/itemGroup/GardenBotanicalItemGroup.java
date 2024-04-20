package net.gardenbotanical.itemGroup;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class GardenBotanicalItemGroup {
    public static final ItemGroup GARDENBOTANICAL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(GardenBotanical.MOD_ID, "gardenbotanical_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gardenbotanical.gardenbotanical_group"))
                    .icon(() -> new ItemStack(GardenBotanicalBlocks.BOUVARDIA)).entries((displayContext, entries) -> {
                        entries.add(GardenBotanicalBlocks.PREPARATION_TABLE);
                        entries.add(GardenBotanicalBlocks.POUNDING_TABLE);

                        entries.add(GardenBotanicalItems.GARDEN_PRUNER);

                        entries.add(GardenBotanicalBlocks.BOUVARDIA);
                        entries.add(GardenBotanicalBlocks.BRUNIA);
                        entries.add(GardenBotanicalBlocks.GERBERA);
                        entries.add(GardenBotanicalBlocks.HERBAL_PEONY);
                        entries.add(GardenBotanicalBlocks.VERONICA);

                        entries.add(GardenBotanicalItems.BOUVARDIA_PETAL);
                        entries.add(GardenBotanicalItems.BRUNIA_PETAL);
                        entries.add(GardenBotanicalItems.GERBERA_PETAL);
                        entries.add(GardenBotanicalItems.HERBAL_PEONY_PETAL);
                        entries.add(GardenBotanicalItems.VERONICA_PETAL);

                        entries.add(GardenBotanicalItems.POWDERED_BOUVARDIA);
                        entries.add(GardenBotanicalItems.POWDERED_BRUNIA);
                        entries.add(GardenBotanicalItems.POWDERED_GERBERA);
                        entries.add(GardenBotanicalItems.POWDERED_HERBAL_PEONY);
                        entries.add(GardenBotanicalItems.POWDERED_VERONICA);

                        entries.add(GardenBotanicalItems.BOUVARDIA_SEEDS);
                        entries.add(GardenBotanicalItems.BRUNIA_SEEDS);
                        entries.add(GardenBotanicalItems.GERBERA_SEEDS);
                        entries.add(GardenBotanicalItems.HERBAL_PEONY_SEEDS);
                        entries.add(GardenBotanicalItems.VERONICA_SEEDS);
                    }).build());

    public static void register() {
        GardenBotanical.LOGGER.info("Registering item groups for: " + GardenBotanical.MOD_ID);
    }
}