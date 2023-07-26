package com.krad.block.client;

import com.krad.block.blockEntity.MortarEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class MortarEntityRenderer implements BlockEntityRenderer<MortarEntity> {

    public MortarEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(MortarEntity mortarEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = mortarEntity.getStack(); // 获取研钵内的物品
//        System.out.println(stack);
        if (!stack.isEmpty()) {
            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
            matrixStack.scale(0.5f,0.5f,0.5f);
            if(stack.getCount()!=1){
                matrixStack.translate(0.9f, -1f, 0.3125); // 将物品移动到研钵中心位置
                itemRenderer.renderItem(stack, ModelTransformation.Mode.GUI, getLightLevel(mortarEntity.getWorld(), mortarEntity.getPos()),
                        OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, 1);
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-35));
                matrixStack.translate(0.2f, 0.1f, -0.0625f); // 将物品移动到研钵中心位置
                itemRenderer.renderItem(stack, ModelTransformation.Mode.GUI, getLightLevel(mortarEntity.getWorld(), mortarEntity.getPos()),
                        OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, 1);
            }else{
                matrixStack.translate(1f, -1f, 0.25); // 将物品移动到研钵中心位置
                itemRenderer.renderItem(stack, ModelTransformation.Mode.GUI, getLightLevel(mortarEntity.getWorld(), mortarEntity.getPos()),
                        OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, 1);
            }
            matrixStack.pop();
        }
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}

