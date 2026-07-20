package net.denmoth.cso;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.denmoth.cso.worldgen.processor.WindmillBearingProcessor;
import net.denmoth.cso.worldgen.processor.GroundMarkerProcessor;
import net.denmoth.cso.worldgen.processor.SolidGroundMarkerProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class CSOMain {
    public static final String MOD_ID = "cso";

    public static StructureProcessorType<WindmillBearingProcessor> WINDMILL_BEARING;
    public static StructureProcessorType<GroundMarkerProcessor> GROUND_MARKER;
    public static StructureProcessorType<SolidGroundMarkerProcessor> SOLID_GROUND_MARKER;

    public static void init() {
        net.denmoth.cso.config.CSOConfig.load();
        
        WINDMILL_BEARING = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "windmill_bearing"), () -> WindmillBearingProcessor.CODEC);
        GROUND_MARKER = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "ground_marker"), () -> GroundMarkerProcessor.CODEC);
        SOLID_GROUND_MARKER = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "solid_ground_marker"), () -> SolidGroundMarkerProcessor.CODEC);
    }
}
