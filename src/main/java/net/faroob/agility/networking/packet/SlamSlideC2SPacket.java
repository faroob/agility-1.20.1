package net.faroob.agility.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.faroob.agility.DataAccessor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SlamSlideC2SPacket {


    public static void slide(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        DataAccessor accessor = (DataAccessor) (Object) player;
        player.velocityModified = true;
        if (player.isOnGround()) {
            accessor.setSliding(true);
            player.setVelocity(-Math.sin(Math.toRadians(accessor.getAgilityYaw())), 0, Math.cos(Math.toRadians(accessor.getAgilityYaw())));
        }
    }

    public static void start(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        DataAccessor accessor = (DataAccessor) (Object) player;
        if (!accessor.isSliding() && player.isOnGround()) {
            accessor.setAgilityYaw(player.getHeadYaw());
        }else if (!player.isOnGround()) {
            accessor.setSlamming(true);
            slam(server, player, handler, buf, responseSender);
        }
    }

    public static void slam(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        DataAccessor accessor = (DataAccessor) (Object) player;
        player.velocityModified = true;
        player.setVelocity(0,-3,0);
        if (player.isOnGround()) {
            accessor.setSlamming(false);
        }
    }

}
