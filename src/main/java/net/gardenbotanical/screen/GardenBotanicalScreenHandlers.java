package net.gardenbotanical.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;


public class GardenBotanicalScreenHandlers {
    public static final ScreenHandlerType<PreparationTableScreenHandler> PREPARATION_TABLE_SCREEN_HANDLER = registerBlockScreenHandler(GardenBotanicalBlocks.PREPARATION_TABLE, PreparationTableScreenHandler::new);
    public static final ScreenHandlerType<PoundingTableScreenHandler> POUNDING_TABLE_SCREEN_HANDLER = registerBlockScreenHandler(GardenBotanicalBlocks.POUNDING_TABLE, PoundingTableScreenHandler::new);

    public static<T extends ScreenHandler> ScreenHandlerType<T> registerBlockScreenHandler(Block block, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Registries.BLOCK.getId(block).withSuffixedPath("_screen_handler"), new ExtendedScreenHandlerType<>(factory));
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering Screen Handlers for: " + GardenBotanical.MOD_ID);
    }
}