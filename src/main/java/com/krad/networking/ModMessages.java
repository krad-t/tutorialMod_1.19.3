package com.krad.networking;

import com.krad.TutorialMod;
import com.krad.networking.packet.ItemStackSyncS2CPacket;
import com.krad.networking.packet.ParticleStackSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ITEM_SYNC = new Identifier(TutorialMod.MOD_ID, "item_sync");
    public static final Identifier PARTICLE_SYNC = new Identifier(TutorialMod.MOD_ID, "particle_sync");

    public static void registerC2SPackets() {
//        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(PARTICLE_SYNC, ParticleStackSyncS2CPacket::receive);
    }
}
