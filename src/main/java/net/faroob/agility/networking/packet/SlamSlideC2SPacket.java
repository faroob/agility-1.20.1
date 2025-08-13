package net.faroob.agility.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SlamSlideC2SPacket {
    public static float yaw;
    public static boolean sliding;
    public static boolean onGround;
    public static boolean slamming;
    public static double slamCounter;

    public static void slide(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        player.velocityModified = true;
        onGround = player.isOnGround();
        if (onGround) {
            sliding = true;
            player.setVelocity(-Math.sin(Math.toRadians(yaw)), -1, Math.cos(Math.toRadians(yaw)));
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
        }
    }

}
