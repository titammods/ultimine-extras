package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import dev.pedro.ultimineshapes.UltimineShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public enum MiningTunnel3x3 implements Shape {
    INSTANCE;

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(UltimineShapes.MOD_ID, "mining_tunnel_3x3");

    @Override
    public ResourceLocation getName() {
        return ID;
    }

    @Override
    public List<BlockPos> getBlocks(ShapeContext context) {
        return Staircase.collect(context, -1);
    }
}
