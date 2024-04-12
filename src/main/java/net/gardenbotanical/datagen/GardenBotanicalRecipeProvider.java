package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;


public class GardenBotanicalRecipeProvider extends FabricRecipeProvider {
    public GardenBotanicalRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, GardenBotanicalItems.GARDEN_PRUNER, 1)
                .pattern("# ")
                .pattern("X#")
                .input('#', Items.IRON_INGOT)
                .input('X', Items.LEATHER)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalItems.GARDEN_PRUNER)));
    }
}
