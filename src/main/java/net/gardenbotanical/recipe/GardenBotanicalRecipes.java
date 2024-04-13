package net.gardenbotanical.recipe;


import net.gardenbotanical.GardenBotanical;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GardenBotanicalRecipes {
    public static void registerRecipes() {
        GardenBotanical.LOGGER.info("Registering Recipes for: " + GardenBotanical.MOD_ID);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(GardenBotanical.MOD_ID, PreparationTableRecipe.Serializer.ID), PreparationTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(GardenBotanical.MOD_ID, PreparationTableRecipe.Type.ID), PreparationTableRecipe.Type.INSTANCE);
    }
}