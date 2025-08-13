package net.faroob.agility;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class DevAuthSetup implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        System.setProperty("devauth.enabled", "true");
        System.setProperty("devauth.account", "main");
    }
}
