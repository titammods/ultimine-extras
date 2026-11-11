package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.FTBUltiminePlayerData;
import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.config.FTBUltimineServerConfig;
import dev.ftb.mods.ftbultimine.shape.ShapeRegistry;
import dev.pedro.ultimineshapes.shape.SizedShape;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public final class ShapeFilter {
    private ShapeFilter() {
    }

    static boolean fits(Shape shape, int maxBlocks) {
        return !(shape instanceof SizedShape sized) || sized.size() <= maxBlocks;
    }

    public static void onKeyPressed(FTBUltiminePlayerData data, ServerPlayer player) {
        int maxBlocks = FTBUltimineServerConfig.getMaxBlocks(player);
        PacketDistributor.sendToPlayer(player, new MaxBlocksPayload(maxBlocks));
        if (!fits(data.getCurrentShape(), maxBlocks)) {
            cycle(data, player, true);
        }
    }

    public static void cycle(FTBUltiminePlayerData data, ServerPlayer player, boolean next) {
        int maxBlocks = FTBUltimineServerConfig.getMaxBlocks(player);
        for (int i = ShapeRegistry.INSTANCE.shapeCount(); i > 0; i--) {
            data.cycleShape(next);
            if (fits(data.getCurrentShape(), maxBlocks)) {
                return;
            }
        }
    }
}
