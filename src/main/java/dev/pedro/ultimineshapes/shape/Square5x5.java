package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import dev.pedro.ultimineshapes.UltimineShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public enum Square5x5 implements SizedShape {
    INSTANCE;

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(UltimineShapes.MOD_ID, "square_5x5");
    private static final int RADIUS = 2;
    private static final int DEPTH = 1;

    @Override
    public ResourceLocation getName() {
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
