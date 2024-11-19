package net.faroob.movementtest.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.faroob.movementtest.MovementTest2;
import net.faroob.movementtest.networking.ModMessages;
import net.faroob.movementtest.networking.packet.SlamSlideC2SPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "key.category.movementtest2.movement_test";
    public static final String KEY_DASH = "key.movementtest2.dash";
    public static final String KEY_SLAM_SLIDE = "key.movementtest2.slam_slide";

    public static KeyBinding dashKey;
    public static KeyBinding slamSlideKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if(dashKey.wasPressed()){
                ClientPlayNetworking.send(ModMessages.DASH_ID, PacketByteBufs.create());
            }
            if(slamSlideKey.wasPressed()){
                ClientPlayNetworking.send(ModMessages.SLAM_SLIDE_STARTED_ID, PacketByteBufs.create());
            }
            if(slamSlideKey.isPressed()){
                ClientPlayNetworking.send(ModMessages.SLAM_SLIDE_ID, PacketByteBufs.create());
            }
            if(!slamSlideKey.isPressed()){
                SlamSlideC2SPacket.sliding = false;
            }
        });
    }

    public static void register() {
        dashKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DASH,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                KEY_CATEGORY
        ));
        slamSlideKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SLAM_SLIDE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                KEY_CATEGORY
        ));
        registerKeyInputs();
    }
}
