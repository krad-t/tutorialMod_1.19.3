package com.krad.block.custom;

import com.krad.TutorialMod;
import com.krad.block.blockEntity.ExtractorEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class Extractor extends BlockWithEntity implements BlockEntityProvider {
    public Extractor(Settings settings) {
        super(settings);
    }

    public static final VoxelShape SHAPE =  Stream.of(
            Block.createCuboidShape(13, 12, 2, 14, 17, 14),
            Block.createCuboidShape(14, 17, 1, 15, 20, 15),
            Block.createCuboidShape(1, 17, 1, 2, 20, 15),
            Block.createCuboidShape(2, 12, 2, 3, 17, 14),
            Block.createCuboidShape(3, 12, 2, 13, 17, 3),
            Block.createCuboidShape(2, 17, 1, 14, 20, 2),
            Block.createCuboidShape(2, 17, 14, 14, 20, 15),
            Block.createCuboidShape(3, 12, 13, 13, 17, 14),
            Block.createCuboidShape(4, 7, 3, 12, 12, 4),
            Block.createCuboidShape(5, 7, 4, 11, 7, 5),
            Block.createCuboidShape(5, 7, 11, 11, 7, 12),
            Block.createCuboidShape(4, 7, 12, 12, 12, 13),
            Block.createCuboidShape(3, 7, 3, 4, 12, 13),
            Block.createCuboidShape(4, 7, 4, 5, 7, 12),
            Block.createCuboidShape(11, 7, 4, 12, 7, 12),
            Block.createCuboidShape(12, 7, 3, 13, 12, 13),
            Block.createCuboidShape(9, 0, 5, 11, 7, 11),
            Block.createCuboidShape(5, 0, 5, 7, 7, 11),
            Block.createCuboidShape(7, 0, 5, 9, 7, 7),
            Block.createCuboidShape(7, 0, 9, 9, 7, 11),
            Block.createCuboidShape(7, 0, 7, 9, 1, 9),
            Block.createCuboidShape(0, 0, 0, 2, 17, 2),
            Block.createCuboidShape(0, 0, 14, 2, 17, 16),
            Block.createCuboidShape(14, 0, 0, 16, 17, 2),
            Block.createCuboidShape(14, 0, 14, 16, 17, 16),
            Block.createCuboidShape(0, 17, 0, 2, 20, 1),
            Block.createCuboidShape(0, 17, 1, 1, 20, 2),
            Block.createCuboidShape(15, 17, 1, 16, 20, 2),
            Block.createCuboidShape(15, 17, 14, 16, 20, 15),
            Block.createCuboidShape(0, 17, 15, 2, 20, 16),
            Block.createCuboidShape(14, 17, 15, 16, 20, 16),
            Block.createCuboidShape(14, 17, 0, 16, 20, 1),
            Block.createCuboidShape(0, 17, 14, 1, 20, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        TutorialMod.LOGGER.info("info-create");
        return new ExtractorEntity(pos, state);
    }

}
