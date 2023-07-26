package com.krad.networking.packet;

import com.krad.block.blockEntity.MortarEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


import java.util.Objects;
import java.util.Random;


public class ParticleStackSyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        float vol_x = buf.readFloat();
        float vol_y = buf.readFloat();
        float vol_z = buf.readFloat();
        BlockPos pos = buf.readBlockPos();
        String otherData = buf.readString();
        if(Objects.equals(otherData, "EXPLOSION")){
            client.world.addParticle(ParticleTypes.EXPLOSION,pos.getX()+0.5,pos.getY()+0.3,pos.getZ()+0.5,vol_x,vol_y,vol_z);
        }else if (Objects.equals(otherData,"CLOUD")){
            client.world.addParticle(ParticleTypes.CLOUD,pos.getX()+0.5,pos.getY()+0.3,pos.getZ()+0.5,vol_x,vol_y,vol_z);
        }else if(Objects.equals(otherData,"COMPOSTER")){
            geneParticle(15,ParticleTypes.ELECTRIC_SPARK,client,pos,vol_x,vol_y,vol_z);
        } else if(Objects.equals(otherData,"CRIT")){
            geneParticle(20,ParticleTypes.CRIT,client,pos,vol_x,vol_y,vol_z);
        }
    }
    public static  void geneParticle(int count,DefaultParticleType type,MinecraftClient client,BlockPos pos,float vol_x, float vol_y, float vol_z){
        Random random = new Random();
        for(int i = 0;i<count;i++){
            // 生成随机整数
            int randomInt = random.nextInt()%4; // 返回一个随机整数
            client.world.addParticle(type, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                    vol_x*Math.sin(randomInt), vol_y, vol_z*Math.cos(randomInt));

        }
    }

}
