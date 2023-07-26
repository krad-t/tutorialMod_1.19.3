package com.krad.block.blockEntity;

import com.krad.networking.ModMessages;
import com.krad.recipe.MortarRecipe;
import com.krad.registry.ModBlocks;
import com.krad.registry.ModItems;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class MortarEntity extends BlockEntity {
    private final int maxSquashCount = 5;
    private int currSquashCount = 0;

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(1, ItemStack.EMPTY); // 研钵的物品槽，默认为空

    public ItemStack getStack() {
        return inventory.get(0);
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        this.inventory.set(0, inventory.get(0));
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

    @Override
    public void markDirty() {
        if(!world.isClient) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for(int i = 0; i < inventory.size(); i++) {
                data.writeItemStack(inventory.get(i));
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC, data);
            }
        }

        super.markDirty();
    }

    public ItemStack onUse(PlayerEntity player) {
        currSquashCount=0;
        if (inventory.get(0).isEmpty()) {
            inventory.set(0, player.getMainHandStack().split(10));
        }else{
            ItemStack items = inventory.get(0).copy();
            inventory.set(0, ItemStack.EMPTY);

            markDirty(); // 标记方块实体已更改
            return items;
        }
        markDirty(); // 标记方块实体已更改
        return ItemStack.EMPTY;
    }

    public void craftItem() {
        currSquashCount++;
        if(!inventory.get(0).isEmpty()){
            if(currSquashCount>=maxSquashCount){
                //根据配方生成物品
                SimpleInventory simpleinventory = new SimpleInventory(this.inventory.size());
                simpleinventory.setStack(0, inventory.get(0));
                Optional<MortarRecipe> recipe = this.getWorld().getRecipeManager()
                        .getFirstMatch(MortarRecipe.Type.INSTANCE,simpleinventory,this.getWorld());
                if(recipe.isPresent()){//配方符合
                    inventory.set(0,new ItemStack(ModItems.CITRINE,inventory.get(0).getCount()));
                    sendParticle("COMPOSTER");
                }else{
                    sendParticle("CRIT");
                }

                currSquashCount=0;
            }else{
                sendParticle("CRIT");
            }
            this.markDirty();
        }
    }

    private void sendParticle(String type) {
        PacketByteBuf data = PacketByteBufs.create();
        // 向data中添加粒子类型的体积数据
        data.writeFloat(0.3f);
        data.writeFloat(0.3f);
        data.writeFloat(0.3f);
        // 向data中添加粒子类型的位置数据
        data.writeBlockPos(pos);
        // 向data中添加粒子类型数据
        data.writeString(type);

        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            ServerPlayNetworking.send(player, ModMessages.PARTICLE_SYNC, data);
        }
    }
}
