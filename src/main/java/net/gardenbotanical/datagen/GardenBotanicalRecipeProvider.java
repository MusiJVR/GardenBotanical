package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
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
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalItems.GARDEN_PRUNER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, GardenBotanicalBlocks.PREPARATION_TABLE, 1)
                .pattern("#X#")
                .pattern("L L")
                .input('#', ItemTags.WOODEN_SLABS)
                .input('X', GardenBotanicalItems.GARDEN_PRUNER)
                .input('L', Items.STICK)
                .criterion(hasItem(GardenBotanicalItems.GARDEN_PRUNER), conditionsFromItem(GardenBotanicalItems.GARDEN_PRUNER))
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalBlocks.PREPARATION_TABLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, GardenBotanicalBlocks.POUNDING_TABLE, 1)
                .pattern("XXX")
                .pattern(" X ")
                .pattern("LLL")
                .input('X', Blocks.SMOOTH_STONE)
                .input('L', Blocks.SMOOTH_STONE_SLAB)
                .criterion(hasItem(Blocks.SMOOTH_STONE), conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalBlocks.POUNDING_TABLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, GardenBotanicalBlocks.DYE_MIXER, 1)
                .pattern("#L#")
                .pattern("X X")
                .pattern(" X ")
                .input('#', Items.IRON_INGOT)
                .input('X', ItemTags.WOODEN_SLABS)
                .input('L', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalBlocks.DYE_MIXER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, GardenBotanicalBlocks.COLORIZER, 1)
                .pattern("L X")
                .pattern("LSL")
                .pattern("###")
                .input('#', ItemTags.WOODEN_SLABS)
                .input('L', Items.STICK)
                .input('X', Items.BRUSH)
                .input('S', Items.BOWL)
                .criterion(hasItem(Items.BRUSH), conditionsFromItem(Items.BRUSH))
                .offerTo(exporter, new Identifier(getRecipeName(GardenBotanicalBlocks.COLORIZER)));
    }
}