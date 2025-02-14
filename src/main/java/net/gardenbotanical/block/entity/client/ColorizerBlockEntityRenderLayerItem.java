package net.gardenbotanical.block.entity.client;

import net.gardenbotanical.block.entity.ColorizerBlockEntity;
import net.gardenbotanical.tag.GardenBotanicalTags;
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
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class ColorizerBlockEntityRenderLayerItem extends GeoRenderLayer<ColorizerBlockEntity> {
    public ColorizerBlockEntityRenderLayerItem(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, ColorizerBlockEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = animatable.getItemRender();

        poseStack.push();

        if (itemStack.isIn(GardenBotanicalTags.COLORIZER_ITEM_TYPES)) {
            if (itemStack.isIn(GardenBotanicalTags.COLORIZER_ARMOR_TYPES)) {
                if (itemStack.isIn(GardenBotanicalTags.COLORIZER_HELMET) || itemStack.isIn(GardenBotanicalTags.COLORIZER_BOOTS)) {
                    poseStack.translate(0.125f, 0.9f, 0.125f);
                } else if (itemStack.isIn(GardenBotanicalTags.COLORIZER_CHESTPLATE) || itemStack.isIn(GardenBotanicalTags.COLORIZER_LEGGINGS)) {
                    poseStack.translate(0.125f, 0.86f, 0.125f);
                } else if (itemStack.isIn(GardenBotanicalTags.COLORIZER_HORSE_ARMOR)) {
                    poseStack.translate(0.13f, 0.86f, 0.13f);
                }
            } else {
                poseStack.translate(0.125f, 0.93f, 0.125f);
            }
        }

        poseStack.scale(0.7f, 0.7f, 0.7f);

        poseStack.multiply(RotationAxis.POSITIVE_Y.rotation((float) Math.toRadians(225)));

        if (itemStack.isOf(Items.LEATHER_HORSE_ARMOR)) {
            poseStack.multiply(RotationAxis.POSITIVE_X.rotation((float) Math.toRadians(180)));
        } else if (itemStack.isOf(Items.PAPER)) {
            poseStack.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(-35)));
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
