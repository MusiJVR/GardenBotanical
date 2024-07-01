package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.S2C.*;
import net.gardenbotanical.network.packet.ServerPacket;


public class GardenBotanicalNetwork {
    public static ServerPacket INVENTORY_SYNC_PACKET = new ServerPacket("inventory_sync");
    public static ServerPacket FLUID_STORAGE_SYNC_PACKET = new ServerPacket("fluid_storage_sync");
    public static ServerPacket SPAWN_PARTICLE_PACKET = new ServerPacket("spawn_particle");

    public static void registerS2CPacket() {
        GardenBotanical.LOGGER.info("Registering s2c packets for: " + GardenBotanical.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(INVENTORY_SYNC_PACKET.ID, InventorySyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(FLUID_STORAGE_SYNC_PACKET.ID, FluidStorageSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(SPAWN_PARTICLE_PACKET.ID, SpawnWaterParticlePacket::receive);
    }

    public static void registerC2SPacket() {
        GardenBotanical.LOGGER.info("Registering c2s packets for: " + GardenBotanical.MOD_ID);
    }
}
