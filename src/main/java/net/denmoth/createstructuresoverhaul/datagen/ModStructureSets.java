package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.worldgen.ConfigurableStructurePlacement;
import net.denmoth.createstructuresoverhaul.worldgen.ModStructures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.core.Vec3i;

import java.util.List;

public class ModStructureSets {

    public static final ResourceKey<StructureSet> OVERWORLD_STRUCTURES = registerKey("overworld_structures");
    public static final ResourceKey<StructureSet> NETHER_STRUCTURES = registerKey("nether_structures");

    public static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(OVERWORLD_STRUCTURES, new StructureSet(
                List.of(
                        // Windmills
                        StructureSet.entry(structures.getOrThrow(ModStructures.OAK_WINDMILL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.BIRCH_WINDMILL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPRUCE_WINDMILL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.ACACIA_WINDMILL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.CHERRY_WINDMILL), 1),
                        // Misc
                        StructureSet.entry(structures.getOrThrow(ModStructures.POST), 1), // Был в списке JSON
                        StructureSet.entry(structures.getOrThrow(ModStructures.CRIMSON_PORTAL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.NETHER_FORPOST), 1), // Overworld invasion
                        // Industrial
                        StructureSet.entry(structures.getOrThrow(ModStructures.MINER_HOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.TOWER), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.CAMP), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.COPPER_GREENHOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.COPPER_STORAGE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.LIGHTNING_ROD_TOWER), 1),
                        // Spooky
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPOOKY_HOUSE_1), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPOOKY_HOUSE_2), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.DARK_SPOOKY_HOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.GRAVEYARD), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPOOKY_FARM), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.TRIANGULAR,
                        1173713707, // Соль из overworld_structures.json
                        30, 6,
                        ConfigurableStructurePlacement.CfgType.OVERWORLD
                )
        ));

        context.register(NETHER_STRUCTURES, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.WARPED_GREENHOUSE), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.TRIANGULAR,
                        1713173207, // Соль из nether_structures.json
                        30, 6,
                        ConfigurableStructurePlacement.CfgType.NETHER
                )
        ));
    }

    private static ResourceKey<StructureSet> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }
}