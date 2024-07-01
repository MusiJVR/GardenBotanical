package net.gardenbotanical.world.gen;

import net.gardenbotanical.GardenBotanical;


public class GardenBotanicalWorldGen {
    public static void register(){
        GardenBotanical.LOGGER.info("Registering worldgen for: " + GardenBotanical.MOD_ID);
        GardenBotanicalFlowerGeneration.generateFlowers();
    }
}
