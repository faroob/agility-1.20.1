package net.faroob.movementtest;

import net.fabricmc.api.ClientModInitializer;
import net.faroob.movementtest.event.KeyInputHandler;
import net.faroob.movementtest.networking.ModMessages;

public class MovementTest2Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        KeyInputHandler.register();
    }
}
