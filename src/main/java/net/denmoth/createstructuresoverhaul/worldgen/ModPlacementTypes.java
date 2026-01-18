package net.denmoth.createstructuresoverhaul.worldgen;

import com.mojang.serialization.Codec;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacementTypes {

    public static final DeferredRegister<StructurePlacementType<?>> REGISTER =
            DeferredRegister.create(new ResourceLocation("minecraft", "worldgen/structure_placement"), CreateStructuresOverhaulMod.MODID);

    // Вместо лямбды используем наш статический класс. Это гарантирует правильную сборку.
    public static final RegistryObject<StructurePlacementType<ConfigurableStructurePlacement>> CONFIGURABLE =
            REGISTER.register("configurable", Type::new);

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    // Явный класс, чтобы избежать проблем с обфускацией лямбд
    public static class Type implements StructurePlacementType<ConfigurableStructurePlacement> {
        @Override
        public Codec<ConfigurableStructurePlacement> codec() {
            return ConfigurableStructurePlacement.CODEC;
        }
    }
}