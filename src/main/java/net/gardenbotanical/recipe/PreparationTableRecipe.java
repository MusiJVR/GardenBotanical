package net.gardenbotanical.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import java.util.List;


public class PreparationTableRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack outputFirst;
    private final ItemStack outputSecond;
    private final List<Ingredient> recipeItems;

    public PreparationTableRecipe(Identifier id, ItemStack itemStackFirst, ItemStack itemStackSecond, List<Ingredient> ingredients) {
        this.id = id;
        this.outputFirst = itemStackFirst;
        this.outputSecond = itemStackSecond;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(0));
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return outputFirst;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return null;
    }

    public ItemStack getOutputFirst(DynamicRegistryManager registryManager) {
        return outputFirst.copy();
    }

    public ItemStack getOutputSecond(DynamicRegistryManager registryManager) {
        return outputSecond.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<PreparationTableRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "preparation_table";
    }

    public static class Serializer implements RecipeSerializer<PreparationTableRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "preparation_table";

        @Override
        public PreparationTableRecipe read(Identifier id, JsonObject json) {
            JsonArray outputs = JsonHelper.getArray(json, "outputs");
            ItemStack outputFirst = ShapedRecipe.outputFromJson(outputs.get(0).getAsJsonObject());
            ItemStack outputSecond = ShapedRecipe.outputFromJson(outputs.get(1).getAsJsonObject());

            JsonArray ingredient = JsonHelper.getArray(json, "ingredient");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredient.get(i)));
            }

            return new PreparationTableRecipe(id, outputFirst, outputSecond, inputs);
        }

        @Override
        public PreparationTableRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack outputFirst = buf.readItemStack();
            ItemStack outputSecond = buf.readItemStack();
            return new PreparationTableRecipe(id, outputFirst, outputSecond, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, PreparationTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutputFirst(null));
            buf.writeItemStack(recipe.getOutputSecond(null));
        }
    }
}
