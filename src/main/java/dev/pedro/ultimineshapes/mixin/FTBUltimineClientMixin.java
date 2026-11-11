package dev.pedro.ultimineshapes.mixin;

import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.client.FTBUltimineClient;
import dev.ftb.mods.ftbultimine.shape.ShapeRegistry;
import dev.pedro.ultimineshapes.ClientShapeFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FTBUltimineClient.class)
public abstract class FTBUltimineClientMixin {
    @Shadow
    private int shapeIdx;

    @Redirect(method = {"addPressedInfo", "renderGameOverlay"}, at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbultimine/shape/ShapeRegistry;shapeCount()I"))
    private int ultimineshapes$visibleShapeCount(ShapeRegistry registry) {
        return ClientShapeFilter.visibleCount(registry, shapeIdx);
    }

    @Redirect(method = "addPressedInfo", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbultimine/shape/ShapeRegistry;getShape(I)Ldev/ftb/mods/ftbultimine/api/shape/Shape;"))
    private Shape ultimineshapes$visibleShape(ShapeRegistry registry, int idx) {
        return ClientShapeFilter.visibleShape(registry, shapeIdx, idx - shapeIdx);
    }
}
