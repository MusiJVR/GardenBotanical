package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.BlockColorizerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class BlockColorizerBlockEntityRenderLayerBlock extends GeoRenderLayer<BlockColorizerBlockEntity> {
    public BlockColorizerBlockEntityRenderLayerBlock(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, BlockColorizerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = animatable.getBlockRender();

        poseStack.push();

        poseStack.translate(0f, 1.03f, 0f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotation((float) Math.toRadians(330)));
        poseStack.multiply(RotationAxis.POSITIVE_Y.rotation((float) Math.toRadians(0)));
        poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(0)));

        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(animatable.getWorld(), animatable.getPos()), OverlayTexture.DEFAULT_UV, poseStack, bufferSource, animatable.getWorld(), 1);
        poseStack.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}