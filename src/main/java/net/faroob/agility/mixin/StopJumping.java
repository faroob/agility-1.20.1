package net.faroob.agility.mixin;

import net.faroob.agility.DataAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class StopJumping {

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void stopJumping(CallbackInfo callbackInfo) {
        DataAccessor accessor = (DataAccessor) MinecraftClient.getInstance();
        if (accessor.isSliding()) {
            callbackInfo.cancel();
        }
    }
}
