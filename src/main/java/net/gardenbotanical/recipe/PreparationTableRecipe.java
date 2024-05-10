package net.gardenbotanical.recipe;

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


public class PreparationTableRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack outputPetal;
    private final int outputPetalBonus;
    private final ItemStack outputSeeds;
    private final int outputSeedsBonus;
    private final Ingredient ingredient;

    public PreparationTableRecipe(Identifier id, ItemStack itemStackFirst, int bonusFirst, ItemStack itemStackSecond, int bonusSecond, Ingredient ingredient) {
        this.id = id;
        this.outputPetal = itemStackFirst;
        this.outputPetalBonus = bonusFirst;
        this.outputSeeds = itemStackSecond;
        this.outputSeedsBonus = bonusSecond;
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) return false;

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
        return null;
    }

    public ItemStack getOutputPetals() {
        return outputPetal.copy();
    }

    public ItemStack getOutputSeeds() {
        return outputSeeds.copy();
    }

    public int getOutputPetalBonus() {
        return outputPetalBonus;
    }

    public int getOutputSeedsBonus() {
        return outputSeedsBonus;
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

    public static class Type implements RecipeType<PreparationTableRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "preparation_table";
    }

    public static class Serializer implements RecipeSerializer<PreparationTableRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "preparation_table";

        @Override
        public PreparationTableRecipe read(Identifier id, JsonObject json) {
            ItemStack outputPetals = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "petal"), "itemStack"));
            ItemStack outputSeeds = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "seeds"), "itemStack"));
            int outputPetalBonus = JsonHelper.getInt(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "petal"), "bonus");
            int outputSeedsBonus = JsonHelper.getInt(JsonHelper.getObject(JsonHelper.getObject(json, "output"), "seeds"), "bonus");
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getElement(json, "ingredient"));

            return new PreparationTableRecipe(id, outputPetals, outputPetalBonus, outputSeeds, outputSeedsBonus, ingredient);
        }

        @Override
        public PreparationTableRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack outputPetals = buf.readItemStack();
            ItemStack outputSeeds = buf.readItemStack();
            int outputPetalBonus = buf.readInt();
            int outputSeedsBonus = buf.readInt();
            return new PreparationTableRecipe(id, outputPetals, outputPetalBonus, outputSeeds, outputSeedsBonus, ingredient);
        }

        @Override
        public void write(PacketByteBuf buf, PreparationTableRecipe recipe) {
            recipe.ingredient.write(buf);
            buf.writeItemStack(recipe.getOutputPetals());
            buf.writeItemStack(recipe.getOutputSeeds());
            buf.writeInt(recipe.outputPetalBonus);
            buf.writeInt(recipe.outputSeedsBonus);
        }
    }
}
