package net.gardenbotanical.screen;

import net.gardenbotanical.block.entity.PoundingTableBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class PoundingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final PoundingTableBlockEntity blockEntity;

    public PoundingTableScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(3));
    }

    public PoundingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(GardenBotanicalScreenHandlers.POUNDING_TABLE_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 3);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((PoundingTableBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 55, 31) {
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.FLINT);
            }
        });
        this.addSlot(new Slot(inventory, 1, 106, 17));
        this.addSlot(new Slot(inventory, 2, 106, 53) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 15;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot == 2) {
                if (!this.insertItem(originalStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(originalStack, newStack);
            } else if (invSlot >= 3 && invSlot < 39) {
                if (!this.insertItem(originalStack, 0, 2, false)) {
                    if (invSlot < 30) {
                        if (!this.insertItem(originalStack, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(originalStack, 3, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(originalStack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
            if (invSlot == 2) {
                player.dropItem(originalStack, false);
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getFuel() {
        return this.propertyDelegate.get(2);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
