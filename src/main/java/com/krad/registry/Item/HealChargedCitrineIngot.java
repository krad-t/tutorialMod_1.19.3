package com.krad.registry.Item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HealChargedCitrineIngot extends Item {

    public HealChargedCitrineIngot(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // 检查玩家是否已满血
        if (user.getHealth() < user.getMaxHealth()) {
            // 给予生命恢复 II 效果
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1));
            // 减少物品数量
            stack.decrement(1);
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
