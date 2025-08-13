package net.faroob.agility;

import net.fabricmc.api.ModInitializer;
import net.faroob.agility.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class agility implements ModInitializer {
	public static final String MOD_ID = "agility";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static int staminaMax = 35;
	public static int dashCooldownLength = 20;

	@Override
	public void onInitialize() {
		LOGGER.info("Get ready to MOVE.");
		ModMessages.registerC2SPackets();
	}
}