package net.denmoth.createstructuresoverhaul;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraft.core.registries.Registries;

@Mod(CSOMain.MOD_ID)
public class CSOForge {
    public CSOForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::register);
    }

    private void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.STRUCTURE_PROCESSOR)) {
            CSOMain.init(event);
        }
    }
}
