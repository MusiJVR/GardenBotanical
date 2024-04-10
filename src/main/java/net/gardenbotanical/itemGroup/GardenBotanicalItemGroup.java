package net.gardenbotanical.itemGroup;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlock;
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
                        entries.add(GardenBotanicalBlock.BOUVARDIA);
                        entries.add(GardenBotanicalBlock.BRUNIA);
                        entries.add(GardenBotanicalBlock.GERBERA);
                        entries.add(GardenBotanicalBlock.HERBAL_PEONY);
                        entries.add(GardenBotanicalBlock.VERONICA);
                    }).build());

    public static void registerItemGroups() {
        GardenBotanical.LOGGER.info("Registering Item Groups for " + GardenBotanical.MOD_ID);
    }
}