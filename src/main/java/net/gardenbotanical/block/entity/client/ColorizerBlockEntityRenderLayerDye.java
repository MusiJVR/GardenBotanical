package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.ColorizerBlockEntity;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
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


public class ColorizerBlockEntityRenderLayerDye extends GeoRenderLayer<ColorizerBlockEntity> {
    public ColorizerBlockEntityRenderLayerDye(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, ColorizerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = animatable.getDyeRender();

        poseStack.push();

        poseStack.translate(-0.25, 0.21875f, 0.25f);

        poseStack.scale(1f, 1f, 1f);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotation((float) Math.toRadians(270)));
        poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(180)));

        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(animatable.getWorld(), animatable.getPos()), OverlayTexture.DEFAULT_UV, poseStack, bufferSource, animatable.getWorld(), 1);
        poseStack.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}