package dev.pedro.ultimineshapes;

import dev.ftb.mods.ftbultimine.api.neoforge.FTBUltimineEvent;
import dev.pedro.ultimineshapes.shape.ModShapes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(UltimineShapes.MOD_ID)
public class UltimineShapes {
    public static final String MOD_ID = "ultimineshapes";

    public UltimineShapes(IEventBus modBus, ModContainer container) {
        modBus.addListener(MaxBlocksPayload::register);
        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC);

        NeoForge.EVENT_BUS.addListener(FTBUltimineEvent.RegisterShape.class,
                event -> ModShapes.ALL.forEach(event.getEventData()::register));
        NeoForge.EVENT_BUS.addListener(FTBUltimineEvent.RegisterBlockBreakHandler.class,
                event -> event.getEventData().consumer().accept(HungerCost::onBreak));
    }
}
