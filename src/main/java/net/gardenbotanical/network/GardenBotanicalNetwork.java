package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.block.entity.PoundingTableBlockEntity;
import net.gardenbotanical.network.packet.ClientPacket;
import net.gardenbotanical.network.packet.ServerPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


public class GardenBotanicalNetwork {
    public static final ServerPacket ITEM_SYNC_PACKET = registerPacket("item_sync", (MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) -> {
        int size = buf.readInt();
        DefaultedList<ItemStack> list = DefaultedList.ofSize(size, ItemStack.EMPTY);
        for(int i = 0; i < size; i++) {
            list.set(i, buf.readItemStack());
        }
        BlockPos position = buf.readBlockPos();

        if(client.world.getBlockEntity(position) instanceof PoundingTableBlockEntity blockEntity) {
            blockEntity.setInventory(list);
        }
    });

    private static ClientPacket registerPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ClientPacket packet = new ClientPacket(id, handler);
        ServerPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }

    private static ServerPacket registerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler)  {
        ServerPacket packet = new ServerPacket(id, handler);
        ClientPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }

    public static void register() {
        GardenBotanical.LOGGER.info("Registering network for: " + GardenBotanical.MOD_ID);
    }
}