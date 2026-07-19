package net.denmoth.createstructuresoverhaul;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.denmoth.createstructuresoverhaul.worldgen.processor.WindmillBearingProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.GroundMarkerProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.SolidGroundMarkerProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.RegisterEvent;

public class CSOMain {
    public static final String MOD_ID = "cso";

    public static final StructureProcessorType<WindmillBearingProcessor> WINDMILL_BEARING = () -> WindmillBearingProcessor.CODEC;
    public static final StructureProcessorType<GroundMarkerProcessor> GROUND_MARKER = () -> GroundMarkerProcessor.CODEC;
    public static final StructureProcessorType<SolidGroundMarkerProcessor> SOLID_GROUND_MARKER = () -> SolidGroundMarkerProcessor.CODEC;

    public static void init(RegisterEvent event) {
        event.register(Registries.STRUCTURE_PROCESSOR, ResourceLocation.fromNamespaceAndPath(MOD_ID, "windmill_bearing"), () -> WINDMILL_BEARING);
        event.register(Registries.STRUCTURE_PROCESSOR, ResourceLocation.fromNamespaceAndPath(MOD_ID, "ground_marker"), () -> GROUND_MARKER);
        event.register(Registries.STRUCTURE_PROCESSOR, ResourceLocation.fromNamespaceAndPath(MOD_ID, "solid_ground_marker"), () -> SOLID_GROUND_MARKER);
    }
}
