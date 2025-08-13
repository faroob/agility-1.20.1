package net.faroob.agility.mixin;

import net.faroob.agility.DataAccessor;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerPlayerEntity.class)
public abstract class AgilityPlayerData implements DataAccessor {

    @Shadow @Final private ServerStatHandler statHandler;
    @Unique
    public float agilityYaw;
    @Unique
    public float agilityPitch;
    @Unique
    public float agilityStamina;
    @Unique
    public boolean sliding;
    @Unique
    public boolean slamming;
    @Unique
    public boolean dashCooldown;
    @Unique
    public float slamCounter;


    @Unique
    public float getAgilityYaw() { return agilityYaw; }
    @Unique
    public void setAgilityYaw(float yaw) { agilityYaw = yaw; }

    @Unique
    public float getAgilityPitch() { return agilityPitch; }
    @Unique
    public void setAgilityPitch(float pitch) { agilityPitch = pitch; }

    @Unique
    public boolean isSliding() { return sliding; }
    @Unique
    public void setSliding(boolean setSliding) { sliding = setSliding; }

    @Unique
    public boolean isSlamming() { return slamming; }
    @Unique
    public void setSlamming(boolean setSlamming) { slamming = setSlamming; }

    @Unique
    public float getStamina() { return agilityStamina; }
    @Unique
    public void setSliding(float stamina) { agilityStamina = stamina; }

    @Unique
    public boolean getAgilityDashCooldown() { return dashCooldown; }
    @Unique
    public void setDashCooldown(boolean cooldown) { dashCooldown = cooldown; }

    @Unique
    public  float getSlamCounter() { return slamCounter; }
    @Unique
    public void setSlamCounter(float setSlamCounter) { slamCounter = setSlamCounter; }
}
