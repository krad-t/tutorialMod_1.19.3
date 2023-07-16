package com.krad.registry.Block;

import com.krad.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MortarEntity extends BlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY); // 研钵的物品槽，默认为空

    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public MortarEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.MortarEntityInstance, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory.clear();
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory, true);
    }

    public boolean addItem(ItemStack heldItem) {
        if (inventory.get(0).isEmpty()) {
            inventory.set(0, heldItem.copy());
            markDirty(); // 标记方块实体已更改
            return true;
        }
        return false;
    }

    public void removeItem() {
        inventory.set(0, ItemStack.EMPTY);
        markDirty(); // 标记方块实体已更改
    }
}
