package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.api.blockbreaking.BlockBreakHandler;
import dev.ftb.mods.ftbultimine.api.shape.Shape;
import dev.ftb.mods.ftbultimine.config.FTBUltimineServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

final class HungerCost {
    private static final double EXHAUSTION_SCALE = 0.005;

    private HungerCost() {
    }

    static BlockBreakHandler.Result onBreak(Player player, BlockPos pos, BlockState state, Shape shape, BlockHitResult hit) {
        if (player instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
            double extra = Config.hungerMultiplier(shape) - 1.0;
            if (extra > 0.0) {
                serverPlayer.causeFoodExhaustion((float) (FTBUltimineServerConfig.getExhaustionPerBlock(serverPlayer) * EXHAUSTION_SCALE * extra));
            }
        }
        return BlockBreakHandler.Result.PASS;
    }
}
