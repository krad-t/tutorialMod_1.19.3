package com.krad.block.blockEntity;

import com.krad.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ExtractorEntity extends BlockEntity {
    public ExtractorEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.EXTRACTOR_ENTITY, pos, state);
    }
}