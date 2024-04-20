package net.gardenbotanical;

import net.fabricmc.api.ModInitializer;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.block.entity.GardenBotanicalBlockEntities;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.itemGroup.GardenBotanicalItemGroup;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.recipe.GardenBotanicalRecipes;
import net.gardenbotanical.screen.GardenBotanicalScreenHandlers;
import net.gardenbotanical.world.gen.GardenBotanicalWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GardenBotanical implements ModInitializer {
    public static final String MOD_ID = "gardenbotanical";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize " + MOD_ID + "...");
        GardenBotanicalItems.register();
        GardenBotanicalBlocks.register();
        GardenBotanicalItemGroup.registerItemGroups();
        GardenBotanicalBlockEntities.registerBlockEntities();
        GardenBotanicalScreenHandlers.registerScreenHandlers();
        GardenBotanicalRecipes.registerRecipes();
        GardenBotanicalWorldGen.registerWorldGen();
        GardenBotanicalNetwork.registerS2CPackets();
    }
}