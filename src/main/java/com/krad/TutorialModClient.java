package com.krad;

import com.krad.block.client.MortarEntityRenderer;
import com.krad.networking.ModMessages;
import com.krad.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;


public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 渲染层级问题,需要把具有透明度的方块指定渲染层级为TRANSLUCENT才能在模型里正常显示透明度
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTRACTOR, RenderLayer.getTranslucent());

        BlockEntityRendererRegistry.register(ModBlocks.MortarEntityInstance,MortarEntityRenderer::new);


        ModMessages.registerS2CPackets();
    }
}
