package net.gardenbotanical.block.entity.client;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.gardenbotanical.block.entity.DyeMixerBlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import static java.lang.Math.round;


public class DyeMixerBlockEntityRenderLayer extends GeoRenderLayer<DyeMixerBlockEntity> {
    public DyeMixerBlockEntityRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    private static void drawVertex(VertexConsumer consumer, Matrix4f modelMatrix, Matrix3f normalMatrix, Vector3f normal, float x, float y, float z, float u, float v, int color, int light, int overlay) {
        consumer.vertex(modelMatrix, x, y, z)
                .color(color)
                .texture(u, v)
                .light(light)
                .overlay(overlay)
                .normal(normalMatrix, normal.x, normal.y, normal.z)
                .next();
    }

    private void drawQuad(Direction direction, VertexConsumer consumer, Matrix4f modelMatrix, Matrix3f normalMatrix, Sprite sprite, int color, int light, int overlay) {
        Vector3f normal = direction.getUnitVector();
        float[][] positions = getQuadVerticesByDirection(direction);

        drawVertex(consumer, modelMatrix, normalMatrix, normal,
                positions[3][0], positions[3][1], positions[3][2],
                sprite.getMinU(), sprite.getMinV(),
                color, light, overlay);
        drawVertex(consumer, modelMatrix, normalMatrix, normal,
                positions[2][0], positions[2][1], positions[2][2],
                sprite.getMinU(), sprite.getMaxV(),
                color, light, overlay);
        drawVertex(consumer, modelMatrix, normalMatrix, normal,
                positions[1][0], positions[1][1], positions[1][2],
                sprite.getMaxU(), sprite.getMaxV(),
                color, light, overlay);
        drawVertex(consumer, modelMatrix, normalMatrix, normal,
                positions[0][0], positions[0][1], positions[0][2],
                sprite.getMaxU(), sprite.getMinV(),
                color, light, overlay);
    }

    private static float[][] getQuadVerticesByDirection(Direction direction) {
        return switch (direction) {
            case UP -> new float[][]{
                    {0, 1, 0},
                    {1, 1, 0},
                    {1, 1, 1},
                    {0, 1, 1}
            };
            case DOWN -> new float[][]{
                    {0, 0, 1},
                    {1, 0, 1},
                    {1, 0, 0},
                    {0, 0, 0}
            };
            case NORTH -> new float[][]{
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 0, 0},
                    {1, 0, 0}
            };
            case SOUTH -> new float[][]{
                    {0, 1, 1},
                    {1, 1, 1},
                    {1, 0, 1},
                    {0, 0, 1}
            };
            case WEST -> new float[][]{
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1},
                    {0, 0, 0}
            };
            case EAST -> new float[][]{
                    {1, 1, 1},
                    {1, 1, 0},
                    {1, 0, 0},
                    {1, 0, 1}
            };
        };
    }

    @Override
    public void render(MatrixStack poseStack, DyeMixerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int light, int overlay) {
        SingleVariantStorage<FluidVariant> storage = animatable.fluidStorage;
        int color = animatable.getFluidColor();
        if (storage.amount == 0 || storage.variant.isBlank()) return;

        FluidVariant fluid = storage.variant;
        float fill = (storage.amount) / (float) storage.getCapacity();

        Sprite sprite = FluidVariantRendering.getSprite(fluid);
        if (color == -1) {
            color = FluidVariantRendering.getColor(fluid);
        } else {
            System.out.println(color);
            color = color - 0xFFFFFF;
        }
        VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getTranslucent());

        poseStack.push();
        poseStack.translate(1, 1, 1);
        poseStack.scale(1, 1 * fill, 1);

        MatrixStack.Entry entry = poseStack.peek();
        Matrix4f modelMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        for (Direction direction : Direction.values()) {
            if (direction.equals(Direction.DOWN)) continue;
            drawQuad(direction, consumer, modelMatrix, normalMatrix, sprite, color, light, overlay);
        }

        poseStack.pop();
    }

    /*@Override
    public void render(MatrixStack poseStack, DyeMixerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = animatable.getFluidRender();

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
    }*/
}
