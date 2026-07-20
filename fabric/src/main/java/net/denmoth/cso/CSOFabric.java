package net.denmoth.cso;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.chat.Component;
import net.denmoth.cso.config.CSOConfig;

public class CSOFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CSOMain.init();
        
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (CSOConfig.hasError && handler.player.hasPermissions(2)) {
                handler.player.sendSystemMessage(Component.translatable("cso.config.error"));
            }
        });
    }
}
