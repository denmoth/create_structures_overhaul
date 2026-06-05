package net.denmoth.createstructuresoverhaul.forge;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.commands.ModCommands;
import net.denmoth.createstructuresoverhaul.config.CSOConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(CreateStructuresOverhaulMod.MODID)
public class CreateStructuresOverhaulForge {

    public CreateStructuresOverhaulForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CSOConfig.load(FMLPaths.CONFIGDIR.get().toFile());
        
        CreateStructuresOverhaulMod.init();

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }
}
