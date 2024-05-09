package net.gardenbotanical.block.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.gardenbotanical.block.DyebleMaterialBlock;
import net.gardenbotanical.network.GardenBotanicalNetwork;
import net.gardenbotanical.network.packet.S2C.DyebleMaterialSyncPacket;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class DyebleMaterialBlockEntity extends BlockEntity {
    public int material;
    public int color;

    public DyebleMaterialBlockEntity(BlockPos pos, BlockState state) {
        super(GardenBotanicalBlockEntities.DYEBLE_MATERIAL_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("material", material);
        nbt.putInt("color", color);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        material = nbt.getInt("material");
        color = nbt.getInt("color");
    }

    public void updateClientData() {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            GardenBotanicalNetwork.DYEBLE_MATERIAL_SYNC_PACKET.send(player, DyebleMaterialSyncPacket.write(material, color, getPos()));
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        updateClientData();
        world.setBlockState(pos, state.with(DyebleMaterialBlock.MATERIAL, 0));
    }
}