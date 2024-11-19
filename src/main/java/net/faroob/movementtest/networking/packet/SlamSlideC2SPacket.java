package net.faroob.movementtest.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SlamSlideC2SPacket {
    public static float yaw;
    public static boolean sliding;
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        player.velocityModified = true;
        if (player.isOnGround()) {
            sliding = true;
            player.setVelocity(-Math.sin(Math.toRadians(yaw)), 0, Math.cos(Math.toRadians(yaw)));
        }
    }

    public static void start(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (!sliding && player.isOnGround()) {
            yaw = player.getHeadYaw();
        }
        /*while (!player.isOnGround()) {
            player.velocityModified = true;
            player.setVelocity(0, -1, 0);
        }*/
    }
}
