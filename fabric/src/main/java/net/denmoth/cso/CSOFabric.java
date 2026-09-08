package net.denmoth.cso;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.chat.Component;
import net.denmoth.cso.config.CSOConfig;

public class CSOFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CSOMain.init();
        
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "windmill_bearing"), CSOMain.WINDMILL_BEARING);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "ground_marker"), CSOMain.GROUND_MARKER);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "solid_ground_marker"), CSOMain.SOLID_GROUND_MARKER);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "decay"), CSOMain.DECAY_PROCESSOR);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "copper_greenhouse_processor"), CSOMain.COPPER_GREENHOUSE_PROCESSOR);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "terrain_blend_processor"), CSOMain.TERRAIN_BLEND_PROCESSOR);
        net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.STRUCTURE_PROCESSOR, new net.minecraft.resources.ResourceLocation(CSOMain.MOD_ID, "post_office_processor"), CSOMain.POST_OFFICE_PROCESSOR);
        
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (CSOConfig.hasError && handler.player.hasPermissions(2)) {
                handler.player.sendSystemMessage(Component.translatable("cso.config.error"));
            }
        });
        
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_WORLD_TICK.register(serverLevel -> {
        });
    }
}
