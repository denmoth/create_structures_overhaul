package net.denmoth.cso;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.denmoth.cso.config.CSOConfig;

public class CSOMain {
    public static final String MOD_ID = "cso";



    public static void init() {
        CSOConfig.load();
    }
}
