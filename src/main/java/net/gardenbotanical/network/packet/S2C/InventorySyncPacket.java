package net.gardenbotanical.network.packet.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gardenbotanical.util.Utils;
import net.gardenbotanical.util.interfaces.InventoryInterface;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


public class InventorySyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world != null) {
            int size = buf.readInt();
            DefaultedList<ItemStack> list = DefaultedList.ofSize(size, ItemStack.EMPTY);
            for (int i = 0; i < size; i++) {
                list.set(i, buf.readItemStack());
            }
            BlockPos position = buf.readBlockPos();

            BlockEntity blockEntity = client.world.getBlockEntity(position);
            if (blockEntity != null) {
                if (!Utils.containInterface(blockEntity.getClass(), InventoryInterface.class))
                    blockEntity = null;
            }

            if (blockEntity != null) {
                ((InventoryInterface) blockEntity).setInventory(list);
            }
        }
    }

    public static PacketByteBuf write(DefaultedList<ItemStack> inventory, BlockPos blockPos) {
        PacketByteBuf data = PacketByteBufs.create();
        data.writeInt(inventory.size());
        for (ItemStack itemStack : inventory) {
            data.writeItemStack(itemStack);
        }
        data.writeBlockPos(blockPos);
        return data;
    }
}
