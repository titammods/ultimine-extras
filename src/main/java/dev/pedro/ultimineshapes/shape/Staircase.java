package dev.pedro.ultimineshapes.shape;

import dev.ftb.mods.ftbultimine.api.shape.ShapeContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

final class Staircase {
    private Staircase() {
    }

    static List<BlockPos> collect(ShapeContext context, int yDirection) {
        Direction face = context.face().getAxis().isVertical() ? context.player().getDirection().getOpposite() : context.face();
        Direction side = face.getClockWise();
        List<BlockPos> list = new ArrayList<>();
        BlockPos center = context.origPos();
        list.add(center);

        for (int step = 0; list.size() < context.maxBlocks(); step++) {
            int sizeBefore = list.size();

            for (int up = -1; up <= 1; up++) {
                for (int a = -1; a <= 1; a++) {
                    if (list.size() >= context.maxBlocks()) {
                        return list;
                    }
                    if (step == 0 && up == 0 && a == 0) {
                        continue;
                    }

                    BlockPos pos = center.relative(side, a).above(up);

                    if (context.check(pos)) {
                        list.add(pos);
                    }
                }
            }

            if (list.size() == sizeBefore) {
                break;
            }

            center = center.offset(-face.getStepX(), yDirection, -face.getStepZ());
        }

        return list;
    }
}
