package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import dev.pedro.ultimineshapes.UltimineShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import java.util.List;

public enum Cube5x5x5 implements SizedShape {
    INSTANCE;

    private static final Identifier ID = Identifier.fromNamespaceAndPath(UltimineShapes.MOD_ID, "cube_5x5x5");
    private static final int RADIUS = 2;
    private static final int DEPTH = 5;

    @Override
    public Identifier getName() {
        return ID;
    }

    @Override
    public int size() {
        return Layers.size(RADIUS, DEPTH);
    }

    @Override
    public List<BlockPos> getBlocks(ShapeContext context) {
        return Layers.collect(context, RADIUS, DEPTH);
    }
}
