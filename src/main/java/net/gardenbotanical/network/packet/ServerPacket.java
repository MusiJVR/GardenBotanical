package net.gardenbotanical.network.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


public class ServerPacket {
    public final Identifier ID;

    public ServerPacket(String id) {
        ID = new Identifier(GardenBotanical.MOD_ID, id);
    }

    public void send(ServerPlayerEntity receiver) {
        ServerPlayNetworking.send(receiver, this.ID, PacketByteBufs.create());
    }

    public void send(ServerPlayerEntity receiver, PacketByteBuf data) {
        ServerPlayNetworking.send(receiver, this.ID, data);
    }
}
