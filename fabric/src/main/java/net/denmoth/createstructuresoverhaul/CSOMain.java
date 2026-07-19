package net.denmoth.createstructuresoverhaul;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.denmoth.createstructuresoverhaul.worldgen.processor.WindmillBearingProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.GroundMarkerProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.SolidGroundMarkerProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class CSOMain {
    public static final String MOD_ID = "cso";

    public static StructureProcessorType<WindmillBearingProcessor> WINDMILL_BEARING;
    public static StructureProcessorType<GroundMarkerProcessor> GROUND_MARKER;
    public static StructureProcessorType<SolidGroundMarkerProcessor> SOLID_GROUND_MARKER;

    public static void init() {
        WINDMILL_BEARING = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "windmill_bearing"), () -> WindmillBearingProcessor.CODEC);
        GROUND_MARKER = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "ground_marker"), () -> GroundMarkerProcessor.CODEC);
        SOLID_GROUND_MARKER = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(MOD_ID, "solid_ground_marker"), () -> SolidGroundMarkerProcessor.CODEC);
    }
}
