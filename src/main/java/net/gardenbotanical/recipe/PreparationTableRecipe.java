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
    private final ItemStack output;
    private final ItemStack output2;
    private final List<Ingredient> recipeItems;

    public PreparationTableRecipe(Identifier id, ItemStack itemStack1, ItemStack itemStack2, List<Ingredient> ingredients) {
        this.id = id;
        this.output = itemStack1;
        this.output2 = itemStack2;
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
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    public ItemStack getOutput2(DynamicRegistryManager registryManager) {
        return output2.copy();
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
            ItemStack output1 = ShapedRecipe.outputFromJson(outputs.get(0).getAsJsonObject());
            ItemStack output2 = ShapedRecipe.outputFromJson(outputs.get(1).getAsJsonObject());

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new PreparationTableRecipe(id, output1, output2, inputs);
        }

        @Override
        public PreparationTableRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output1 = buf.readItemStack();
            ItemStack output2 = buf.readItemStack();
            return new PreparationTableRecipe(id, output1, output2, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, PreparationTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput(null));
            buf.writeItemStack(recipe.getOutput2(null));
        }
    }
}
