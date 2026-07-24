package net.denmoth.cso;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

@Mod(CSOMain.MOD_ID)
public class CSONeoForge {
    public CSONeoForge(IEventBus modEventBus, net.neoforged.fml.ModContainer container) {
        CSOMain.init();
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.addListener(net.neoforged.neoforge.event.tick.LevelTickEvent.Post.class, this::onLevelTick);
        
        if (net.neoforged.fml.ModList.get().isLoaded("cloth_config")) {
            container.registerExtensionPoint(net.neoforged.neoforge.client.gui.IConfigScreenFactory.class, (mc, screen) -> {
                return net.denmoth.cso.compat.ClothConfigIntegration.createConfigScreen(screen);
            });
        }
        modEventBus.addListener(this::onRegister);
    }
    
    private void onRegister(net.neoforged.neoforge.registries.RegisterEvent event) {
        event.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper -> {
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "windmill_bearing"), CSOMain.WINDMILL_BEARING);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "ground_marker"), CSOMain.GROUND_MARKER);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "solid_ground_marker"), CSOMain.SOLID_GROUND_MARKER);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "decay"), CSOMain.DECAY_PROCESSOR);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "copper_greenhouse_processor"), CSOMain.COPPER_GREENHOUSE_PROCESSOR);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "terrain_blend_processor"), CSOMain.TERRAIN_BLEND_PROCESSOR);
            helper.register(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(CSOMain.MOD_ID, "post_office_processor"), CSOMain.POST_OFFICE_PROCESSOR);
        });
    }
    
    private void onLevelTick(net.neoforged.neoforge.event.tick.LevelTickEvent.Post event) {
        if (event.getLevel() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            net.denmoth.cso.event.CSOEventTracker.tick(serverLevel);
        }
    }
}
