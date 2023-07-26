package com.krad.block.custom;

import com.krad.block.blockEntity.MortarEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
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
        if (!world.isClient()) { // 只在服务器端执行
            if (world.getBlockEntity(pos) instanceof MortarEntity mortarEntity) {
                if(player.isSneaking()){//按下了潜行
                    if(hand==Hand.MAIN_HAND){
                        mortarEntity.craftItem();
                    }
                }else{
                    if(hand==Hand.MAIN_HAND){
                        ItemStack items = mortarEntity.onUse(player);
                        if (!player.getInventory().insertStack(items.copy()) && !player.getAbilities().creativeMode) {//非创造模式下,默认插入背包中,背包已满则掉落
                            player.dropItem(items, false);
                        }
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
