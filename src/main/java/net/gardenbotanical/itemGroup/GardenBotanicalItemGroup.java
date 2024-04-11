package net.gardenbotanical.itemGroup;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlock;
import net.gardenbotanical.item.GardenBotanicalItem;
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
                    .icon(() -> new ItemStack(GardenBotanicalBlock.BOUVARDIA)).entries((displayContext, entries) -> {
                        entries.add(GardenBotanicalItem.GARDEN_PRUNER);

                        entries.add(GardenBotanicalBlock.BOUVARDIA);
                        entries.add(GardenBotanicalBlock.BRUNIA);
                        entries.add(GardenBotanicalBlock.GERBERA);
                        entries.add(GardenBotanicalBlock.HERBAL_PEONY);
                        entries.add(GardenBotanicalBlock.VERONICA);

                        entries.add(GardenBotanicalItem.BOUVARDIA_PETAL);
                        entries.add(GardenBotanicalItem.BRUNIA_PETAL);
                        entries.add(GardenBotanicalItem.GERBERA_PETAL);
                        entries.add(GardenBotanicalItem.HERBAL_PEONY_PETAL);
                        entries.add(GardenBotanicalItem.VERONICA_PETAL);

                        entries.add(GardenBotanicalItem.POWDERED_BOUVARDIA);
                        entries.add(GardenBotanicalItem.POWDERED_BRUNIA);
                        entries.add(GardenBotanicalItem.POWDERED_GERBERA);
                        entries.add(GardenBotanicalItem.POWDERED_HERBAL_PEONY);
                        entries.add(GardenBotanicalItem.POWDERED_VERONICA);

                        entries.add(GardenBotanicalItem.BOUVARDIA_SEEDS);
                        entries.add(GardenBotanicalItem.BRUNIA_SEEDS);
                        entries.add(GardenBotanicalItem.GERBERA_SEEDS);
                        entries.add(GardenBotanicalItem.HERBAL_PEONY_SEEDS);
                        entries.add(GardenBotanicalItem.VERONICA_SEEDS);
                    }).build());

    public static void registerItemGroups() {
        GardenBotanical.LOGGER.info("Registering Item Groups for: " + GardenBotanical.MOD_ID);
    }
}