package com.krad.registry.Block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Mortar extends BlockWithEntity {
    public Mortar(Settings settings) {
        super(settings);
    }
    protected static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 1.0, 13.0), Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 2.0, 14.0), Block.createCuboidShape(1.0, 2.0, 1.0, 15.0, 7.0, 15.0));

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
        return new MortarEntity(pos,state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) { // 只在服务器端执行
            ItemStack heldItem = player.getStackInHand(hand);
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof MortarEntity mortarEntity) {
                ItemStack mortarItemStack = mortarEntity.getInventory().get(0);

                if (mortarItemStack.isEmpty()) {
                    // 如果研钵内没有物品，则将手持物品放入研钵
                    mortarEntity.addItem(heldItem);
                    player.setStackInHand(hand, ItemStack.EMPTY);
                } else {
                    // 如果研钵内有物品，则将物品取回到玩家手上
                    if (player.getInventory().insertStack(mortarItemStack.copy())) {
                        mortarEntity.removeItem(); // 从研钵中移除物品
                    } else {
                        // 玩家背包已满，将物品掉落
                        player.dropItem(mortarItemStack, false);
                        mortarEntity.removeItem(); // 从研钵中移除物品
                    }
                }
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
