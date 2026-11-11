package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.pedro.ultimineshapes.shape.ModShapes;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

public final class Config {
    public static final ModConfigSpec SPEC;
    private static final Map<Shape, ModConfigSpec.DoubleValue> HUNGER_MULTIPLIERS = new HashMap<>();

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Hunger multiplier per block mined with each shape, on top of FTB Ultimine's exhaustion_per_block (1.0 = no extra cost)")
                .push("hunger_multiplier");
        for (Shape shape : ModShapes.ALL) {
            HUNGER_MULTIPLIERS.put(shape, builder.defineInRange(shape.getName().getPath(), 1.0, 1.0, 100.0));
        }
        builder.pop();
        SPEC = builder.build();
    }

    private Config() {
    }

    static double hungerMultiplier(Shape shape) {
        ModConfigSpec.DoubleValue value = HUNGER_MULTIPLIERS.get(shape);
        return value == null ? 1.0 : value.get();
    }
}
