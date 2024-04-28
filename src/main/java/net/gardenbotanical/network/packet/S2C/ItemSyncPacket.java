package net.gardenbotanical.network.packet.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gardenbotanical.block.entity.PoundingTableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


public class ItemSyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world != null) {
            int size = buf.readInt();
            DefaultedList<ItemStack> list = DefaultedList.ofSize(size, ItemStack.EMPTY);
            for (int i = 0; i < size; i++) {
                list.set(i, buf.readItemStack());
            }
            BlockPos position = buf.readBlockPos();

            if (client.world.getBlockEntity(position) instanceof PoundingTableBlockEntity blockEntity) {
                blockEntity.setInventory(list);
            }
        }
    }
}
