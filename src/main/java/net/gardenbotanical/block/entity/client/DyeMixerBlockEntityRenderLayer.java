package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class DyeMixerBlockEntityRenderLayer extends GeoRenderLayer<DyeMixerBlockEntity> {
    public DyeMixerBlockEntityRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, DyeMixerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = animatable.getWater();

        poseStack.push();

        switch (animatable.getCachedState().get(Properties.HORIZONTAL_FACING)) {
            case NORTH -> poseStack.translate(0, 0.25f, -0.0625f);
            case EAST -> poseStack.translate(0.0625f, 0.25f, -0.0625f);
            case SOUTH -> poseStack.translate(0.0625f, 0.25f, 0);
            case WEST -> poseStack.translate(0, 0.25f, 0);
        }

        poseStack.scale(1f, 1f, 1f);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotation((float) Math.toRadians(270)));

        switch (animatable.getCachedState().get(Properties.HORIZONTAL_FACING)) {
            case NORTH -> poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(180)));
            case EAST -> poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(90)));
            case SOUTH -> poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(0)));
            case WEST -> poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(270)));
        }

        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(animatable.getWorld(), animatable.getPos()), OverlayTexture.DEFAULT_UV, poseStack, bufferSource, animatable.getWorld(), 1);
        poseStack.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
