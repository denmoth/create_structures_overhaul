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
        
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.addListener(this::onLevelTick);
        net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegister);
    }
    
    private void onRegister(net.minecraftforge.registries.RegisterEvent event) {
        if (event.getRegistryKey().equals(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR)) {
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "windmill_bearing"), () -> CSOMain.WINDMILL_BEARING);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "ground_marker"), () -> CSOMain.GROUND_MARKER);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "solid_ground_marker"), () -> CSOMain.SOLID_GROUND_MARKER);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "decay"), () -> CSOMain.DECAY_PROCESSOR);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "copper_greenhouse_processor"), () -> CSOMain.COPPER_GREENHOUSE_PROCESSOR);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "terrain_blend_processor"), () -> CSOMain.TERRAIN_BLEND_PROCESSOR);
            event.register(net.minecraft.core.registries.Registries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "post_office_processor"), () -> CSOMain.POST_OFFICE_PROCESSOR);
        }
    }
    
    private void onLevelTick(net.minecraftforge.event.TickEvent.LevelTickEvent event) {
        if (event.phase == net.minecraftforge.event.TickEvent.Phase.END && event.level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            net.denmoth.cso.event.CSOEventTracker.tick(serverLevel);
        }
    }
}
