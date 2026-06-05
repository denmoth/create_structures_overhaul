package net.denmoth.createstructuresoverhaul.fabric;

import com.mojang.serialization.Codec;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

public class PlatformHelperFabric implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends StructurePlacement> StructurePlacementType<T> registerPlacementType(String id, Codec<T> codec) {
        StructurePlacementType<T> type = () -> codec;
        return Registry.register(BuiltInRegistries.STRUCTURE_PLACEMENT, new ResourceLocation(CreateStructuresOverhaulMod.MODID, id), type);
    }
}
