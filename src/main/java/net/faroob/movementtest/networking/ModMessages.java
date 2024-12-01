package net.faroob.movementtest.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.faroob.movementtest.MovementTest2;
import net.faroob.movementtest.networking.packet.DashC2SPacket;
import net.faroob.movementtest.networking.packet.SlamSlideC2SPacket;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DASH_ID = new Identifier(MovementTest2.MOD_ID, "dash");
    public static final Identifier SLIDE_ID = new Identifier(MovementTest2.MOD_ID, "slide");
    public static final Identifier SLAM_SLIDE_STARTED_ID = new Identifier(MovementTest2.MOD_ID, "slam_slide_started");
    public static final Identifier SLAM_ID = new Identifier(MovementTest2.MOD_ID, "slam");
    public static final Identifier UPDATE_GROUND_ID = new Identifier(MovementTest2.MOD_ID, "update_ground_state");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DASH_ID, DashC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SLIDE_ID, SlamSlideC2SPacket::slide);
        ServerPlayNetworking.registerGlobalReceiver(SLAM_SLIDE_STARTED_ID, SlamSlideC2SPacket::start);
        ServerPlayNetworking.registerGlobalReceiver(SLAM_ID, SlamSlideC2SPacket::slam);
        ServerPlayNetworking.registerGlobalReceiver(UPDATE_GROUND_ID, SlamSlideC2SPacket::updateGroundState);
    }

    public static void registerS2CPackets() {

    }
}
