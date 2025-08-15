package net.faroob.agility.mixin;

import net.faroob.agility.DataAccessor;
import net.faroob.agility.networking.packet.SlamSlideC2SPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ForceCrawling {

    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void forceCrawling(CallbackInfo callbackInfo) {
        DataAccessor accessor = (DataAccessor) MinecraftClient.getInstance();
        PlayerEntity player = (PlayerEntity) (Object)this;
        if (accessor.isSliding()) {
            player.setSwimming(true);
            player.setPose(EntityPose.SWIMMING);
            player.setJumping(false);
        }else{
            player.setSwimming(false);
        }
    }
}

