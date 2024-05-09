package net.gardenbotanical.network.packet.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.gardenbotanical.block.entity.DyebleMaterialBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;


public class DyebleMaterialSyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world != null) {
            int material = buf.readInt();
            int color = buf.readInt();
            BlockPos position = buf.readBlockPos();

            if (client.world.getBlockEntity(position) instanceof DyebleMaterialBlockEntity blockEntity) {
                blockEntity.material = material;
                blockEntity.color = color;
            }
        }
    }

    public static PacketByteBuf write(int material, int color, BlockPos blockPos) {
        PacketByteBuf data = PacketByteBufs.create();
        data.writeInt(material);
        data.writeInt(color);
        data.writeBlockPos(blockPos);
        return data;
    }
}