package net.faroob.agility;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.faroob.agility.event.KeyInputHandler;
import net.faroob.agility.networking.ModMessages;
import net.faroob.agility.networking.packet.DashC2SPacket;
import net.minecraft.client.MinecraftClient;

import static net.faroob.agility.Agility.dashCooldownLength;
import static net.faroob.agility.Agility.staminaMax;

public class AgilityClient implements ClientModInitializer {
    private static int timer = 0;
    public static int slamVelocityTimeout;
    public static double stamina = staminaMax;

    @Override

    /*
    runs stuff on startup
     */
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        KeyInputHandler.register();
        slamTimerManager();
        dashCounter();
        staminaRegen();
    }

    /*
    counts how long your slam lasts for to that it can give you the correct ammount of speed in your slide (thats what it supposed to do)
     */
    public static void slamTimerManager() {

        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            DataAccessor accessor = (DataAccessor) MinecraftClient.getInstance();
            float slamCounter = accessor.getSlamCounter();
            if (accessor.isSlamming()) {
                ClientPlayNetworking.send(ModMessages.SLAM_ID, PacketByteBufs.create());
                slamVelocityTimeout = 20;
                slamCounter += .1F;
            }
            if ((slamVelocityTimeout == 0  || (minecraftClient.player.isOnGround() && !accessor.isSliding()) ) && slamCounter > 0) {
                slamCounter -=.1F;
            }
            if (slamCounter > 0 && minecraftClient.player.isOnGround()) {
                slamVelocityTimeout--;
            }
            accessor.setSlamCounter(slamCounter);
        });
    }

    /*
    counts the cooldown for dash
     */
    public static void dashCounter() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (DashC2SPacket.dashCooldown) {
                if (timer == 0) {
                    timer = dashCooldownLength;
                }
                timer--;
            }
            if (timer <= 0) {
                DashC2SPacket.dashCooldown = false;
            }
        });
    }

    /*
    regenerates stamina
     */
    public static void staminaRegen() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (!DashC2SPacket.dashCooldown && stamina < staminaMax) {
                stamina+= .5;
            }
        });
    }
}
