package net.faroob.movementtest;

import net.fabricmc.api.ModInitializer;

import net.faroob.movementtest.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovementTest2 implements ModInitializer {
	public static final String MOD_ID = "movementtest2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Get ready to MOVE.");
		ModMessages.registerC2SPackets();
	}
}