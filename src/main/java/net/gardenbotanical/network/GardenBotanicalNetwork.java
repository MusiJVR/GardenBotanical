package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.S2C.ItemSyncPacket;
import net.gardenbotanical.network.packet.S2C.WaterLevelSyncPacket;
import net.gardenbotanical.network.packet.ServerPacket;


public class GardenBotanicalNetwork {
    public static ServerPacket ITEM_SYNC_PACKET = new ServerPacket("item_sync");
    public static ServerPacket WATER_LEVEL_SYNC_PACKET = new ServerPacket("water_level_sync");

    public static void registerS2CPacket() {
        GardenBotanical.LOGGER.info("Registering s2c packets for: " + GardenBotanical.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC_PACKET.ID, ItemSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(WATER_LEVEL_SYNC_PACKET.ID, WaterLevelSyncPacket::receive);
    }

    public static void registerC2SPacket() {
        GardenBotanical.LOGGER.info("Registering c2s packets for: " + GardenBotanical.MOD_ID);
    }
}