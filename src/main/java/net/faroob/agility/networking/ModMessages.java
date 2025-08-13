package net.faroob.agility.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.faroob.agility.agility;
import net.faroob.agility.networking.packet.DashC2SPacket;
import net.faroob.agility.networking.packet.SlamSlideC2SPacket;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DASH_ID = new Identifier(agility.MOD_ID, "dash");
    public static final Identifier SLIDE_ID = new Identifier(agility.MOD_ID, "slide");
    public static final Identifier SLAM_SLIDE_STARTED_ID = new Identifier(agility.MOD_ID, "slam_slide_started");
    public static final Identifier SLAM_ID = new Identifier(agility.MOD_ID, "slam");


    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DASH_ID, DashC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SLIDE_ID, SlamSlideC2SPacket::slide);
        ServerPlayNetworking.registerGlobalReceiver(SLAM_SLIDE_STARTED_ID, SlamSlideC2SPacket::start);
        ServerPlayNetworking.registerGlobalReceiver(SLAM_ID, SlamSlideC2SPacket::slam);
    }

    public static void registerS2CPackets() {

    }
}
