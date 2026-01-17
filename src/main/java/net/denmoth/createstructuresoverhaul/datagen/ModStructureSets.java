package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.worldgen.ConfigurableStructurePlacement;
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

    public static final ResourceKey<StructureSet> WINDMILLS_FOREST = registerKey("windmills_forest");
    public static final ResourceKey<StructureSet> WINDMILLS_TAIGA = registerKey("windmills_taiga");
    public static final ResourceKey<StructureSet> WINDMILLS_SAVANNA = registerKey("windmills_savanna");
    public static final ResourceKey<StructureSet> WINDMILLS_CHERRY = registerKey("windmills_cherry");

    public static final ResourceKey<StructureSet> SPOOKY = registerKey("spooky");
    public static final ResourceKey<StructureSet> NETHER = registerKey("nether");
    public static final ResourceKey<StructureSet> INDUSTRIAL = registerKey("industrial");

    public static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(WINDMILLS_FOREST, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.OAK_WINDMILL), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.BIRCH_WINDMILL), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        14357619,
                        34, 12,
                        ConfigurableStructurePlacement.CfgType.WINDMILLS
                )
        ));

        context.register(WINDMILLS_TAIGA, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPRUCE_WINDMILL), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        14357620,
                        40, 15,
                        ConfigurableStructurePlacement.CfgType.WINDMILLS
                )
        ));

        context.register(WINDMILLS_SAVANNA, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.ACACIA_WINDMILL), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        14357621,
                        40, 15,
                        ConfigurableStructurePlacement.CfgType.WINDMILLS
                )
        ));

        context.register(WINDMILLS_CHERRY, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.CHERRY_WINDMILL), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        14357622,
                        24, 8,
                        ConfigurableStructurePlacement.CfgType.WINDMILLS
                )
        ));

        context.register(SPOOKY, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.TOWER), 2),
                        StructureSet.entry(structures.getOrThrow(ModStructures.GRAVEYARD), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.DARK_SPOOKY_HOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPOOKY_HOUSE_1), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.SPOOKY_HOUSE_2), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        16482390,
                        50, 20,
                        ConfigurableStructurePlacement.CfgType.SPOOKY
                )
        ));

        context.register(INDUSTRIAL, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.NETHER_FORPOST), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.CAMP), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.MINER_HOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.LIGHTNING_ROD_TOWER), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.COPPER_GREENHOUSE), 1),
                        StructureSet.entry(structures.getOrThrow(ModStructures.COPPER_STORAGE), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        99887766,
                        45, 18,
                        ConfigurableStructurePlacement.CfgType.SPOOKY
                )
        ));

        context.register(NETHER, new StructureSet(
                List.of(
                        StructureSet.entry(structures.getOrThrow(ModStructures.WARPED_GREENHOUSE), 1)
                ),
                new ConfigurableStructurePlacement(
                        Vec3i.ZERO,
                        RandomSpreadType.LINEAR,
                        11223344,
                        25, 10,
                        ConfigurableStructurePlacement.CfgType.NETHER
                )
        ));
    }

    private static ResourceKey<StructureSet> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }
}