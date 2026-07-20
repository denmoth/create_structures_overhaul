package net.denmoth.cso;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CSOMain.MOD_ID)
public class CSOForge {
    public CSOForge() {
        CSOMain.init(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
