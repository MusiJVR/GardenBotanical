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


public class DyeMixerRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack outputDye;
    private final Ingredient ingredient;

    public DyeMixerRecipe(Identifier id, ItemStack itemStack, Ingredient ingredient) {
        this.id = id;
        this.outputDye = itemStack;
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return ingredient.test(inventory.getStack(0));
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
        return outputDye.copy();
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

    public static class Type implements RecipeType<DyeMixerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "dye_mixer";
    }

    public static class Serializer implements RecipeSerializer<DyeMixerRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "dye_mixer";

        @Override
        public DyeMixerRecipe read(Identifier id, JsonObject json) {
            ItemStack outputDye = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "dye"));
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getElement(json, "ingredient"));

            return new DyeMixerRecipe(id, outputDye, ingredient);
        }

        @Override
        public DyeMixerRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack outputDye = buf.readItemStack();
            return new DyeMixerRecipe(id, outputDye, ingredient);
        }

        @Override
        public void write(PacketByteBuf buf, DyeMixerRecipe recipe) {
            recipe.ingredient.write(buf);
            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}
