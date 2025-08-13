package net.faroob.agility.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.faroob.agility.agilityClient.*;

public class DashC2SPacket {
    public static boolean dashCooldown;

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int dashCost = 10;
        if (stamina >= dashCost) {
            double dashModifier = 1.25;
            double pitch = player.getPitch();
            double yaw = player.getHeadYaw();
            player.velocityModified = true;
            player.addVelocity(
                    (-Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))) * dashModifier,
                    -Math.sin(Math.toRadians(pitch)) * dashModifier ,
                    (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))) * dashModifier
            );
            dashCooldown = true;
            stamina -= dashCost;
        }
    }
}
