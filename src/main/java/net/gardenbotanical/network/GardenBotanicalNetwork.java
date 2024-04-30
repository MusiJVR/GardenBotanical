package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.S2C.ItemSyncPacket;
import net.gardenbotanical.network.packet.S2C.FluidSyncPacket;
import net.gardenbotanical.network.packet.ServerPacket;


public class GardenBotanicalNetwork {
    public static ServerPacket ITEM_SYNC_PACKET = new ServerPacket("item_sync");
    public static ServerPacket FLUID_SYNC_PACKET = new ServerPacket("fluid_sync");

    public static void registerS2CPacket() {
        GardenBotanical.LOGGER.info("Registering s2c packets for: " + GardenBotanical.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC_PACKET.ID, ItemSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC_PACKET.ID, FluidSyncPacket::receive);
    }

    public static void registerC2SPacket() {
        GardenBotanical.LOGGER.info("Registering c2s packets for: " + GardenBotanical.MOD_ID);
    }
}