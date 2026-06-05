package net.denmoth.createstructuresoverhaul.forge;

import com.mojang.serialization.Codec;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.IPlatformHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PlatformHelperForge implements IPlatformHelper {

    private static final DeferredRegister<StructurePlacementType<?>> PLACEMENT_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, CreateStructuresOverhaulMod.MODID);
    
    private static boolean registered = false;

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.production;
    }

    @Override
    public <T extends StructurePlacement> StructurePlacementType<T> registerPlacementType(String id, Codec<T> codec) {
        if (!registered) {
            PLACEMENT_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
            registered = true;
        }
        RegistryObject<StructurePlacementType<T>> obj = PLACEMENT_TYPES.register(id, () -> () -> codec);
        
        // This is a bit hacky because DeferredRegister doesn't return the object immediately, 
        // but for StructurePlacementType it uses a supplier in vanilla, so we can return a wrapper or just use the obj.
        // Wait, StructurePlacementType is a functional interface in Vanilla.
        // We can just return the functional interface that delegates to the codec!
        return new StructurePlacementType<T>() {
            @Override
            public Codec<T> codec() {
                return codec;
            }
        };
    }
}
