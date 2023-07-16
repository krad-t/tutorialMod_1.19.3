package com.krad;

import com.krad.registry.Block.MortarEntityRenderer;
import com.krad.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;


public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        net.minecraft.client.render.block.entity.BlockEntityRendererFactories.register(ModBlocks.MortarEntityInstance, MortarEntityRenderer::new);
    }
}
