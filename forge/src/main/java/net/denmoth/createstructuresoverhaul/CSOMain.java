package net.denmoth.createstructuresoverhaul;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.denmoth.createstructuresoverhaul.worldgen.processor.WindmillBearingProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.GroundMarkerProcessor;
import net.denmoth.createstructuresoverhaul.worldgen.processor.SolidGroundMarkerProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import java.util.function.Supplier;

public class CSOMain {
    public static final String MOD_ID = "cso";

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, MOD_ID);

    public static final RegistryObject<StructureProcessorType<WindmillBearingProcessor>> WINDMILL_BEARING = PROCESSORS.register("windmill_bearing", () -> () -> WindmillBearingProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<GroundMarkerProcessor>> GROUND_MARKER = PROCESSORS.register("ground_marker", () -> () -> GroundMarkerProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<SolidGroundMarkerProcessor>> SOLID_GROUND_MARKER = PROCESSORS.register("solid_ground_marker", () -> () -> SolidGroundMarkerProcessor.CODEC);

    public static void init(IEventBus modEventBus) {
        PROCESSORS.register(modEventBus);
    }
}
