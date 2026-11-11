package dev.pedro.ultimineshapes.mixin;

import dev.ftb.mods.ftbultimine.FTBUltimine;
import dev.ftb.mods.ftbultimine.FTBUltiminePlayerData;
import dev.pedro.ultimineshapes.ShapeFilter;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FTBUltimine.class)
public abstract class FTBUltimineMixin {
    @Inject(method = "setKeyPressed", at = @At("HEAD"))
    private void ultimineshapes$skipUnfitShape(ServerPlayer player, boolean pressed, CallbackInfo ci) {
        ShapeFilter.onKeyPressed(((FTBUltimine) (Object) this).getOrCreatePlayerData(player), player);
    }

    @Redirect(method = "modeChanged", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbultimine/FTBUltiminePlayerData;cycleShape(Z)V"))
    private void ultimineshapes$cycleFittingShape(FTBUltiminePlayerData data, boolean next, ServerPlayer player, boolean nextArg) {
        ShapeFilter.cycle(data, player, next);
    }
}
