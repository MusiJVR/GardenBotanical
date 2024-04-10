package net.gardenbotanical;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GardenBotanical implements ModInitializer {
    public static final String MOD_ID = "gardenbotanical";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Initialize " + MOD_ID + "...");
    }
}
