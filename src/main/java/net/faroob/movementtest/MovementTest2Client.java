package net.faroob.movementtest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.faroob.movementtest.event.KeyInputHandler;
import net.faroob.movementtest.networking.ModMessages;
import net.faroob.movementtest.networking.packet.DashC2SPacket;
import net.faroob.movementtest.networking.packet.SlamSlideC2SPacket;

import static net.faroob.movementtest.networking.packet.DashC2SPacket.*;

public class MovementTest2Client implements ClientModInitializer {
    private static int timer = 0;
    public static int slamVelocityTimeout;
    public static int staminaMax = 35;
    public static double stamina = staminaMax;
    private static int dashCooldownLength = 20;

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        KeyInputHandler.register();
        slamTimerManager();
        dashCounter();
        staminaRegen();
    }

    public static void slamTimerManager() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (SlamSlideC2SPacket.slamming) {
                ClientPlayNetworking.send(ModMessages.SLAM_ID, PacketByteBufs.create());
                slamVelocityTimeout = 20;
                SlamSlideC2SPacket.slamCounter++;
            }
            if (slamVelocityTimeout == 0) {
                SlamSlideC2SPacket.slamCounter = 0;
            }
            if (SlamSlideC2SPacket.slamCounter > 0 && SlamSlideC2SPacket.onGround) {
                slamVelocityTimeout--;
            }
        });
    }

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

    public static void staminaRegen() {
        ClientTickEvents.END_CLIENT_TICK.register((minecraftClient -> {
            if (DashC2SPacket.dashCooldown == false && stamina < staminaMax) {
                stamina+= .5;
            }
        }));
    }
}
