package net.gardenbotanical;

import net.fabricmc.api.ModInitializer;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.item.GardenBotanicalItemGroups;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.recipe.GardenBotanicalRecipes;
import net.gardenbotanical.screen.GardenBotanicalScreenHandlers;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.gardenbotanical.world.gen.GardenBotanicalWorldGen;
import net.minecraft.item.DyeableArmorItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;


public class GardenBotanical implements ModInitializer {
    public static final String MOD_ID = "gardenbotanical";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final int DEFAULT_WATER_COLOR = 0x3F76E4;
    public static final int DEFAULT_DYE_COLOR = 0xFFFFFF;
    public static final int DEFAULT_LEATHER_ARMOR_COLOR = DyeableArmorItem.DEFAULT_COLOR;

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize " + MOD_ID + "...");
        GeckoLib.initialize();
        GardenBotanicalItems.register();
        GardenBotanicalBlocks.register();
        GardenBotanicalItemGroups.register();
        GardenBotanicalTags.register();
        GardenBotanicalBlockEntities.register();
        GardenBotanicalScreenHandlers.register();
        GardenBotanicalRecipes.register();
        GardenBotanicalWorldGen.register();
        GardenBotanicalNetwork.registerC2SPacket();
    }
}