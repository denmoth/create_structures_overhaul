package net.denmoth.createstructuresoverhaul;

import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();
    
    /**
     * Registers a custom structure placement type.
     */
    <T extends net.minecraft.world.level.levelgen.structure.placement.StructurePlacement> StructurePlacementType<T> registerPlacementType(String id, com.mojang.serialization.Codec<T> codec);
}
