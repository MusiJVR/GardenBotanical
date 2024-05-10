package net.gardenbotanical.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;


public class PoundingTableRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack outputPowder;
    private final Ingredient ingredient;

    public PoundingTableRecipe(Identifier id, ItemStack itemStack, Ingredient ingredient) {
        this.id = id;
        this.outputPowder = itemStack;
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) return false;

        return ingredient.test(inventory.getStack(1));
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return outputPowder.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return DefaultedList.of();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<PoundingTableRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "pounding_table";
    }

    public static class Serializer implements RecipeSerializer<PoundingTableRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "pounding_table";

        @Override
        public PoundingTableRecipe read(Identifier id, JsonObject json) {
            ItemStack outputPowder = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "powder"), "itemStack"));
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getElement(json, "ingredient"));

            return new PoundingTableRecipe(id, outputPowder, ingredient);
        }

        @Override
        public PoundingTableRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack outputPowder = buf.readItemStack();
            return new PoundingTableRecipe(id, outputPowder, ingredient);
        }

        @Override
        public void write(PacketByteBuf buf, PoundingTableRecipe recipe) {
            recipe.ingredient.write(buf);
            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}
