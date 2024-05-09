package net.gardenbotanical.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.gardenbotanical.network.packet.S2C.*;
import net.gardenbotanical.network.packet.ServerPacket;


public class GardenBotanicalNetwork {
    public static ServerPacket POUNDING_TABLE_SYNC_PACKET = new ServerPacket("pounding_table_sync");
    public static ServerPacket DYE_MIXER_SYNC_PACKET = new ServerPacket("dye_mixer_sync");
    public static ServerPacket COLORIZER_SYNC_PACKET = new ServerPacket("colorizer_sync");
    public static ServerPacket BLOCK_COLORIZER_SYNC_PACKET = new ServerPacket("block_colorizer_sync");
    public static ServerPacket DYEBLE_MATERIAL_SYNC_PACKET = new ServerPacket("dyeble_material_sync");
    public static ServerPacket SPAWN_PARTICLE_PACKET = new ServerPacket("spawn_particle");

    public static void registerS2CPacket() {
        GardenBotanical.LOGGER.info("Registering s2c packets for: " + GardenBotanical.MOD_ID);

        ClientPlayNetworking.registerGlobalReceiver(POUNDING_TABLE_SYNC_PACKET.ID, PoundingTableSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(DYE_MIXER_SYNC_PACKET.ID, DyeMixerSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(COLORIZER_SYNC_PACKET.ID, ColorizerSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(BLOCK_COLORIZER_SYNC_PACKET.ID, BlockColorizerSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(DYEBLE_MATERIAL_SYNC_PACKET.ID, DyebleMaterialSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(SPAWN_PARTICLE_PACKET.ID, SpawnWaterParticlePacket::receive);
    }

    public static void registerC2SPacket() {
        GardenBotanical.LOGGER.info("Registering c2s packets for: " + GardenBotanical.MOD_ID);
    }
}