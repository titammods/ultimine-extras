package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

final class Layers {
    private Layers() {
    }

    static int size(int radius, int depth) {
        int side = radius * 2 + 1;
        return side * side * depth;
    }

    static List<BlockPos> collect(ShapeContext context, int radius, int depth) {
        List<BlockPos> list = new ArrayList<>(size(radius, depth));
        BlockPos center = context.origPos();
        list.add(center);

        for (int layer = 0; layer < depth && list.size() < context.maxBlocks(); layer++) {
            int sizeBefore = list.size();

            for (int a = -radius; a <= radius; a++) {
                for (int b = -radius; b <= radius; b++) {
                    if (list.size() >= context.maxBlocks()) {
                        return list;
                    }
                    if (layer == 0 && a == 0 && b == 0) {
                        continue;
                    }

                    BlockPos pos = switch (context.face().getAxis()) {
                        case X -> center.offset(0, a, b);
                        case Y -> center.offset(a, 0, b);
                        case Z -> center.offset(a, b, 0);
                    };

                    if (context.check(pos)) {
                        list.add(pos);
                    }
                }
            }

            if (list.size() == sizeBefore) {
                break;
            }

            center = center.relative(context.face().getOpposite());
        }

        return list;
    }
}
