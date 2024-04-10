package net.gardenbotanical.client;

import net.fabricmc.api.ClientModInitializer;
import net.gardenbotanical.GardenBotanical;

public class GardenBotanicalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GardenBotanical.LOGGER.info("Initialize " + GardenBotanical.MOD_ID + " client...");
    }
}
