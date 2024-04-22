package ninja.carter.mods.freshsky.modules.no_swim.mixins;

import net.minecraft.client.player.LocalPlayer;
import ninja.carter.mods.freshsky.FreshSkyMod;
import ninja.carter.mods.freshsky.modules.no_swim.NoSwimModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
	@Shadow public abstract boolean isUnderWater();

	@Inject(at = @At("HEAD"), method = "canStartSprinting", cancellable = true)
	public void canStartSprinting(CallbackInfoReturnable<Boolean> cir) {
		if (FreshSkyMod.isSkyblock && NoSwimModule.ENABLED && this.isUnderWater())
			cir.setReturnValue(false);
	}
}
