package net.faroob.movementtest.mixin;

import net.faroob.movementtest.networking.packet.SlamSlideC2SPacket;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class StopJumping {

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void stopJumping(CallbackInfo callbackInfo) {
        if (SlamSlideC2SPacket.sliding) {
            callbackInfo.cancel();
        }
    }
}
