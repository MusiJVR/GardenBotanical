package net.gardenbotanical.network.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;


public class ClientPacket {
    public final Identifier ID;

    public ClientPacket(String id) {
        ID = new Identifier(GardenBotanical.MOD_ID, id);
    }

    public void send() {
        ClientPlayNetworking.send(this.ID, PacketByteBufs.create());
    }

    public void send(PacketByteBuf data) {
        ClientPlayNetworking.send(this.ID, data);
    }
}
