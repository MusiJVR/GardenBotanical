package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.ClientPacket;
import net.gardenbotanical.network.packet.ItemStackSyncS2CPacket;
import net.gardenbotanical.network.packet.ServerPacket;
import net.minecraft.util.Identifier;


public class GardenBotanicalNetwork {
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

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier(GardenBotanical.MOD_ID, "item_sync"), ItemStackSyncS2CPacket::receive);
    }
}