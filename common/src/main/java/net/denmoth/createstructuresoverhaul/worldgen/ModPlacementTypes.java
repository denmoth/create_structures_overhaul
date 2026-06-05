package net.denmoth.createstructuresoverhaul.worldgen;

import net.denmoth.createstructuresoverhaul.Services;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

public class ModPlacementTypes {

    public static StructurePlacementType<ConfigurableStructurePlacement> CONFIGURABLE;

    public static void init() {
        CONFIGURABLE = Services.PLATFORM.registerPlacementType("configurable", ConfigurableStructurePlacement.CODEC);
    }
}