package net.gardenbotanical.world.gen;

import net.gardenbotanical.GardenBotanical;

public class GardenBotanicalWorldGen {
    public static void registerWorldGen(){
        GardenBotanical.LOGGER.info("Registering WorldGen for: " + GardenBotanical.MOD_ID);
        GardenBotanicalFlowerGeneration.generateFlowers();
    }
}
