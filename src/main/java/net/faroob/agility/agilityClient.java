package net.faroob.agility;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.faroob.agility.event.KeyInputHandler;
import net.faroob.agility.networking.ModMessages;
import net.faroob.agility.networking.packet.DashC2SPacket;
import net.faroob.agility.networking.packet.SlamSlideC2SPacket;

public class agilityClient implements ClientModInitializer {
    private static int timer = 0;
    public static int slamVelocityTimeout;
    public static int staminaMax = 35;
    public static double stamina = staminaMax;
    private static final int dashCooldownLength = 20;

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
    counts how long your slam lasts for to that it can give you the correct ammount of speed in your slide
     */
    public static void slamTimerManager() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (SlamSlideC2SPacket.slamming) {
                ClientPlayNetworking.send(ModMessages.SLAM_ID, PacketByteBufs.create());
                slamVelocityTimeout = 20;
                SlamSlideC2SPacket.slamCounter += .1;
                //System.out.println(SlamSlideC2SPacket.slamCounter);
            }
            if ((slamVelocityTimeout == 0  || (SlamSlideC2SPacket.onGround && !SlamSlideC2SPacket.sliding) ) && SlamSlideC2SPacket.slamCounter > 0) {
                SlamSlideC2SPacket.slamCounter -=.1;
            }
            if (SlamSlideC2SPacket.slamCounter > 0 && SlamSlideC2SPacket.onGround) {
                slamVelocityTimeout--;
            }
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
