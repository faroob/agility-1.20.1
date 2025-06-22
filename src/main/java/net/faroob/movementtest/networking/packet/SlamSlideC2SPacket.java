package net.faroob.movementtest.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

public class SlamSlideC2SPacket {
    public static float yaw;
    public static boolean sliding;
    public static boolean onGround;
    public static boolean slamming;
    public static double slamCounter = 0;
    public static double slam2SlideSpeed;
    private static double slideMagnitude = .75;

    public static void slide(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        player.velocityModified = true;
        onGround = player.isOnGround();
        if (onGround) {
            sliding = true;
            slam2SlideSpeed = slamCounter * .5;
            player.setVelocity(-Math.sin(Math.toRadians(yaw)) * (slideMagnitude + slam2SlideSpeed), 0, Math.cos(Math.toRadians(yaw)) * (slideMagnitude + slam2SlideSpeed));
            System.out.println(slam2SlideSpeed);
        }
    }

    public static void start(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        onGround = player.isOnGround();
        if (!sliding && onGround) {
            yaw = player.getHeadYaw();
        }else if (!onGround) {
            slamming = true;
            slam(server, player, handler, buf, responseSender);
        }
    }

    public static void slam(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        player.velocityModified = true;
        onGround = player.isOnGround();
        player.setVelocity(0,-3,0);
        if (onGround) {
            slamming = false;
            yaw = player.getHeadYaw();
        }
    }

    public static void updateGroundState(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        onGround = player.isOnGround();
    }
}
