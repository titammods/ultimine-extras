package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import dev.pedro.ultimineshapes.UltimineShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import java.util.List;

public enum EscapeTunnel3x3 implements Shape {
    INSTANCE;

    private static final Identifier ID = Identifier.fromNamespaceAndPath(UltimineShapes.MOD_ID, "escape_tunnel_3x3");

    @Override
    public Identifier getName() {
        return ID;
    }

    @Override
    public List<BlockPos> getBlocks(ShapeContext context) {
        return Staircase.collect(context, 1);
    }
}
