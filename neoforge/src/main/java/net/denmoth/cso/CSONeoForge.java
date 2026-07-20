package net.denmoth.cso;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

@Mod(CSOMain.MOD_ID)
public class CSONeoForge {
    public CSONeoForge(IEventBus modEventBus) {
        CSOMain.init(modEventBus);
    }
}
