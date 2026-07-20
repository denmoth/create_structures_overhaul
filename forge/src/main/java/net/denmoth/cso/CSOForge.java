package net.denmoth.cso;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.client.ConfigScreenHandler;

@Mod(CSOMain.MOD_ID)
public class CSOForge {
    public CSOForge() {
        CSOMain.init();
        
        if (ModList.get().isLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> {
                return net.denmoth.cso.compat.ClothConfigIntegration.createConfigScreen(parent);
            }));
        }
    }
}
