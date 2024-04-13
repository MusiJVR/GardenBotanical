package net.gardenbotanical.network.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


public class ServerPacket {
    public final Identifier ID;
    public final ClientPlayNetworking.PlayChannelHandler HANDLER;

    public ServerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(GardenBotanical.MOD_ID, id);
        HANDLER = handler;
    }

    public void send(ServerPlayerEntity receiver) {
        ServerPlayNetworking.send(receiver, this.ID, PacketByteBufs.create());
    }

    public void send(ServerPlayerEntity receiver, PacketByteBuf data) {
        ServerPlayNetworking.send(receiver, this.ID, data);
    }
}
