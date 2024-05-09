package net.gardenbotanical.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;


public class DyeMixerRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack outputDye;
    private final int dyeColor;
    private final Ingredient ingredient;

    public DyeMixerRecipe(Identifier id, ItemStack outputDye, int dyeColor, Ingredient ingredient) {
        this.id = id;
        this.outputDye = outputDye;
        this.dyeColor = dyeColor;
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
        ItemStack itemStack = outputDye.copy();
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("color", getColor());
        nbtCompound.put("display", nbt);
        return itemStack;
    }

    public int getColor() {
        return dyeColor;
    }

    @Override
    public Identifier getId() {
        return id;
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
            int dyeColor = Integer.parseInt(JsonHelper.getString(JsonHelper.getObject(json, "output"), "color").substring(1), 16);

            return new DyeMixerRecipe(id, outputDye, dyeColor, ingredient);
        }

        @Override
        public DyeMixerRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack outputDye = buf.readItemStack();
            int dyeColor = buf.readInt();
            return new DyeMixerRecipe(id, outputDye, dyeColor, ingredient);
        }

        @Override
        public void write(PacketByteBuf buf, DyeMixerRecipe recipe) {
            recipe.ingredient.write(buf);
            buf.writeItemStack(recipe.getOutput(null));
            buf.writeInt(recipe.getColor());
        }
    }
}
