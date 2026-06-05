package net.denmoth.createstructuresoverhaul.fabric;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.commands.ModCommands;
import net.denmoth.createstructuresoverhaul.config.CSOConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

public class CreateStructuresOverhaulFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CSOConfig.load(FabricLoader.getInstance().getConfigDir().toFile());
        
        CreateStructuresOverhaulMod.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ModCommands.register(dispatcher);
        });
    }
}
