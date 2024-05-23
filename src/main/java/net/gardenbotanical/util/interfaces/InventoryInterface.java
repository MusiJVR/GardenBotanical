package net.gardenbotanical.util.interfaces;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;

public interface InventoryInterface extends ImplementedInventory {
    default void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            setStack(i, inventory.get(i));
        }
    }

    default boolean slotIsEmpty(int slot) {
        return getStack(slot).isEmpty();
    }

    default boolean canInsertItem(ItemStack itemStack, int slot) {
        return (getStack(slot).getItem() == itemStack.getItem() || getStack(slot).isEmpty())
                && (getStack(slot).getCount() + itemStack.getCount() <= getStack(slot).getMaxCount());
    }

    default void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, getItems());
    }

    default void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, getItems());
    }
}
