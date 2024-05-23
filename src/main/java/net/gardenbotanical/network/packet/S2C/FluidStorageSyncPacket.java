package net.gardenbotanical.network.packet.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.gardenbotanical.util.Utils;
import net.gardenbotanical.util.interfaces.FluidStorageInterface;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;


public class FluidStorageSyncPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (client.world != null) {
            FluidVariant variant = FluidVariant.fromPacket(buf);
            long fluidLevel = buf.readLong();
            BlockPos position = buf.readBlockPos();

            BlockEntity blockEntity = client.world.getBlockEntity(position);
            if (blockEntity != null) {
                if (!Utils.containInterface(blockEntity.getClass(), FluidStorageInterface.class))
                    blockEntity = null;
            }

            if (blockEntity != null) {
                ((FluidStorageInterface) blockEntity).setFluidLevel(variant, fluidLevel);
            }
        }
    }

    public static PacketByteBuf write(SingleVariantStorage<FluidVariant> fluidStorage, BlockPos blockPos) {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(blockPos);
        return data;
    }
}
