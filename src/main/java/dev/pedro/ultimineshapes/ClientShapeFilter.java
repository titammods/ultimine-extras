package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.shape.ShapeRegistry;

import java.util.ArrayList;
import java.util.List;

public final class ClientShapeFilter {
    private static int maxBlocks = Integer.MAX_VALUE;

    private ClientShapeFilter() {
    }

    static void setMaxBlocks(int value) {
        maxBlocks = value;
    }

    public static int visibleCount(ShapeRegistry registry, int current) {
        return visible(registry, current).size();
    }

    public static Shape visibleShape(ShapeRegistry registry, int current, int offset) {
        List<Integer> visible = visible(registry, current);
        int pos = visible.indexOf(current);
        if (pos < 0) {
            return registry.getShape(current + offset);
        }
        return registry.getShape(visible.get(Math.floorMod(pos + offset, visible.size())));
    }

    private static List<Integer> visible(ShapeRegistry registry, int current) {
        List<Integer> visible = new ArrayList<>();
        for (int i = 0; i < registry.shapeCount(); i++) {
            if (i == current || ShapeFilter.fits(registry.getShape(i), maxBlocks)) {
                visible.add(i);
            }
        }
        return visible;
    }
}
