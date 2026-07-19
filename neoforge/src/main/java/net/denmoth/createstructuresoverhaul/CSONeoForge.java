package net.denmoth.createstructuresoverhaul;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.minecraft.core.registries.Registries;

@Mod(CSOMain.MOD_ID)
public class CSONeoForge {
    public CSONeoForge(IEventBus modEventBus) {
        modEventBus.addListener(this::register);
    }

    private void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.STRUCTURE_PROCESSOR)) {
            CSOMain.init(event);
        }
    }
}
