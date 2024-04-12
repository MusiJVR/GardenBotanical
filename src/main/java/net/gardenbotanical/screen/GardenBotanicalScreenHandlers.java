package net.gardenbotanical.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;


public class GardenBotanicalScreenHandlers {
    public static final ScreenHandlerType<PreparationTableScreenHandler> PREPARATION_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(GardenBotanical.MOD_ID, "preparation_table"),
                    new ExtendedScreenHandlerType<>(PreparationTableScreenHandler::new));

    public static void registerScreenHandlers() {
        GardenBotanical.LOGGER.info("Registering Screen Handlers for " + GardenBotanical.MOD_ID);
    }
}