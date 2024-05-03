package net.gardenbotanical.network.packet.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

import java.util.Random;


public class SpawnWaterParticlePacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world != null) {
            BlockPos pos = buf.readBlockPos();
            Random random = new Random();
            for (int count = 0; count < 2; count++) {
                client.world.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.6 + random.nextDouble(0.1), pos.getY() + 0.45 + random.nextDouble(0.1), pos.getZ() + 0.5 + random.nextDouble(0.1), 0, 0, 0);
                client.world.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.4 + random.nextDouble(0.1), pos.getY() + 0.45 + random.nextDouble(0.1), pos.getZ() + 0.5 + random.nextDouble(0.1), 0, 0, 0);
                client.world.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5 + random.nextDouble(0.1), pos.getY() + 0.45 + random.nextDouble(0.1), pos.getZ() + 0.6 + random.nextDouble(0.1), 0, 0, 0);
                client.world.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5 + random.nextDouble(0.1), pos.getY() + 0.45 + random.nextDouble(0.1), pos.getZ() + 0.4 + random.nextDouble(0.1), 0, 0, 0);
            }
        }
    }
}
