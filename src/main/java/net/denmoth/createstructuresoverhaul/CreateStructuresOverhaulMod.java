package net.denmoth.createstructuresoverhaul;

import com.mojang.logging.LogUtils;
import net.denmoth.createstructuresoverhaul.commands.ModCommands;
import net.denmoth.createstructuresoverhaul.config.CSOConfig;
import net.denmoth.createstructuresoverhaul.datagen.DataGenerators;
import net.denmoth.createstructuresoverhaul.worldgen.ModPlacementTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CreateStructuresOverhaulMod.MODID)
public class CreateStructuresOverhaulMod {
    public static final String MODID = "cso";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateStructuresOverhaulMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSOConfig.COMMON_SPEC);
        ModPlacementTypes.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }
}