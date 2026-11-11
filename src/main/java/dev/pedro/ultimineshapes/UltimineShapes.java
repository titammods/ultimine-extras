package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.api.blockbreaking.RegisterBlockBreakHandlerEvent;
import dev.ftb.mods.ftbultimine.api.shape.RegisterShapeEvent;
import dev.pedro.ultimineshapes.shape.ModShapes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(UltimineShapes.MOD_ID)
public class UltimineShapes {
    public static final String MOD_ID = "ultimineshapes";

    public UltimineShapes(IEventBus modBus, ModContainer container) {
        modBus.addListener(MaxBlocksPayload::register);
        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC);

        RegisterShapeEvent.REGISTER.register(registry -> ModShapes.ALL.forEach(registry::register));
        RegisterBlockBreakHandlerEvent.REGISTER.register(registry -> registry.registerHandler(HungerCost::onBreak));
    }
}
