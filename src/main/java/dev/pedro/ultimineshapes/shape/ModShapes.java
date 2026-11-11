package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.Shape;

import java.util.List;

public final class ModShapes {
    public static final List<Shape> ALL = List.of(
            Square5x5.INSTANCE,
            Cube3x3x3.INSTANCE,
            Cube5x5x5.INSTANCE,
            MiningTunnel3x3.INSTANCE,
            EscapeTunnel3x3.INSTANCE
    );

    private ModShapes() {
    }
}
