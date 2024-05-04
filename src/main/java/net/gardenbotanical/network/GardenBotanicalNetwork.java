package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.S2C.ColorizerSyncPacket;
import net.gardenbotanical.network.packet.S2C.DyeMixerSyncPacket;
import net.gardenbotanical.network.packet.S2C.PoundingTableSyncPacket;
import net.gardenbotanical.network.packet.S2C.SpawnWaterParticlePacket;
import net.gardenbotanical.network.packet.ServerPacket;


public class GardenBotanicalNetwork {
    public static ServerPacket PT_SYNC_PACKET = new ServerPacket("pounding_table_sync");
    public static ServerPacket DM_SYNC_PACKET = new ServerPacket("dye_mixer_sync");
    public static ServerPacket C_SYNC_PACKET = new ServerPacket("colorizer_sync");
    public static ServerPacket SPAWN_PARTICLE_PACKET = new ServerPacket("spawn_particle");

    public static void registerS2CPacket() {
        GardenBotanical.LOGGER.info("Registering s2c packets for: " + GardenBotanical.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(PT_SYNC_PACKET.ID, PoundingTableSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(DM_SYNC_PACKET.ID, DyeMixerSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(C_SYNC_PACKET.ID, ColorizerSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(SPAWN_PARTICLE_PACKET.ID, SpawnWaterParticlePacket::receive);
    }

    public static void registerC2SPacket() {
        GardenBotanical.LOGGER.info("Registering c2s packets for: " + GardenBotanical.MOD_ID);
    }
}