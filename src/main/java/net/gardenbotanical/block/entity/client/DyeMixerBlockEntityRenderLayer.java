package net.gardenbotanical.block.entity.client;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.Direction;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class DyeMixerBlockEntityRenderLayer extends GeoRenderLayer<DyeMixerBlockEntity> {
    public DyeMixerBlockEntityRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    private void drawQuad(Direction direction, VertexConsumer consumer, Matrix4f modelMatrix, Matrix3f normalMatrix, Sprite sprite, int color, int light, int overlay) {
        Vector3f normal = direction.getUnitVector();
        float[][] vertexes = new float[][] {
                {0.514f, 1, 0.55f},
                {0.95f, 1, 0.55f},
                {0.95f, 1, 0.987f},
                {0.514f, 1, 0.987f}
        };

        consumer.vertex(modelMatrix, vertexes[3][0], vertexes[3][1], vertexes[3][2])
                .color(color)
                .texture(sprite.getMinU(), sprite.getMinV())
                .light(light).overlay(overlay)
                .normal(normalMatrix, normal.x, normal.y, normal.z)
                .next();
        consumer.vertex(modelMatrix, vertexes[2][0], vertexes[2][1], vertexes[2][2])
                .color(color)
                .texture(sprite.getMinU(), sprite.getMaxV())
                .light(light).overlay(overlay)
                .normal(normalMatrix, normal.x, normal.y, normal.z)
                .next();
        consumer.vertex(modelMatrix, vertexes[1][0], vertexes[1][1], vertexes[1][2])
                .color(color)
                .texture(sprite.getMaxU(), sprite.getMaxV())
                .light(light).overlay(overlay)
                .normal(normalMatrix, normal.x, normal.y, normal.z)
                .next();
        consumer.vertex(modelMatrix, vertexes[0][0], vertexes[0][1], vertexes[0][2])
                .color(color)
                .texture(sprite.getMaxU(), sprite.getMinV())
                .light(light).overlay(overlay)
                .normal(normalMatrix, normal.x, normal.y, normal.z)
                .next();
    }

    @Override
    public void render(MatrixStack poseStack, DyeMixerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int light, int overlay) {
        if (!animatable.renderFluid()) return;

        int color = animatable.getFluidColor();
        FluidVariant fluid = FluidVariant.of(Fluids.WATER);

        Sprite sprite = FluidVariantRendering.getSprite(fluid);
        if (color == -1) {
            color = FluidVariantRendering.getColor(fluid);
        } else {
            color = color - 0xFFFFFF;
            if (color == 0) {
                color = -1;
            }
        }
        VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getTranslucent());

        poseStack.push();
        poseStack.translate(-0.7, -0.73, -0.8);
        poseStack.scale(1, 1, 1);

        MatrixStack.Entry entry = poseStack.peek();
        Matrix4f modelMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        drawQuad(Direction.UP, consumer, modelMatrix, normalMatrix, sprite, color, light, overlay);

        poseStack.pop();
    }
}