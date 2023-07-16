package com.krad.registry.Block;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public class MortarEntityRenderer implements BlockEntityRenderer<MortarEntity> {

    public MortarEntityRenderer(BlockEntityRendererFactory.Context ctx) {}



    @Override
    public void render(MortarEntity mortarEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        ItemStack stack = mortarEntity.getInventory().get(0); // 获取研钵内的物品

        if (!stack.isEmpty()) {
            matrixStack.push();

            matrixStack.translate(0.5, 0.5, 0.5); // 将物品移动到研钵中心位置
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, light, overlay, matrixStack, vertexConsumerProvider,0); // 绘制物品模型
            matrixStack.pop();
        }
    }
}

