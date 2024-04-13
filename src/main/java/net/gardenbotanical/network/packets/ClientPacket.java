package net.gardenbotanical.network.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;


public class ClientPacket {
    public final Identifier ID;
    public final ServerPlayNetworking.PlayChannelHandler HANDLER;

    public ClientPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(GardenBotanical.MOD_ID, id);
        HANDLER = handler;
    }

    public void send() {
        ClientPlayNetworking.send(this.ID, PacketByteBufs.create());
    }

    public void send(PacketByteBuf data) {
        ClientPlayNetworking.send(this.ID, data);
    }
}
