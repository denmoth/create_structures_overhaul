package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Optional;
import java.util.Map;

public class ModStructures {

    public static final ResourceKey<Structure> OAK_WINDMILL = createKey("oak_windmill");
    public static final ResourceKey<Structure> BIRCH_WINDMILL = createKey("birch_windmill");
    public static final ResourceKey<Structure> SPRUCE_WINDMILL = createKey("spruce_windmill");
    public static final ResourceKey<Structure> ACACIA_WINDMILL = createKey("acacia_windmill");
    public static final ResourceKey<Structure> CHERRY_WINDMILL = createKey("cherry_windmill");

    public static final ResourceKey<Structure> TOWER = createKey("tower");
    public static final ResourceKey<Structure> GRAVEYARD = createKey("graveyard");
    public static final ResourceKey<Structure> DARK_SPOOKY_HOUSE = createKey("dark_spooky_house");
    public static final ResourceKey<Structure> SPOOKY_HOUSE_1 = createKey("spooky_house1");
    public static final ResourceKey<Structure> SPOOKY_HOUSE_2 = createKey("spooky_house2");

    public static final ResourceKey<Structure> NETHER_FORPOST = createKey("nether_forpost");
    public static final ResourceKey<Structure> WARPED_GREENHOUSE = createKey("warped_greenhouse");

    public static final ResourceKey<Structure> CAMP = createKey("camp");
    public static final ResourceKey<Structure> MINER_HOUSE = createKey("miner_hut");
    public static final ResourceKey<Structure> LIGHTNING_ROD_TOWER = createKey("lightningrodtower");
    public static final ResourceKey<Structure> COPPER_GREENHOUSE = createKey("greenhouse");
    public static final ResourceKey<Structure> COPPER_STORAGE = createKey("storage");

    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);

        // === OVERWORLD (Surface) ===
        registerSurface(context, OAK_WINDMILL, templatePools, "oak_windmill",
                biomes.getOrThrow(BiomeTags.IS_FOREST), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, BIRCH_WINDMILL, templatePools, "birch_windmill",
                HolderSet.direct(biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)),
                TerrainAdjustment.BEARD_THIN);

        registerSurface(context, SPRUCE_WINDMILL, templatePools, "spruce_windmill",
                biomes.getOrThrow(BiomeTags.IS_TAIGA), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, ACACIA_WINDMILL, templatePools, "acacia_windmill",
                biomes.getOrThrow(BiomeTags.IS_SAVANNA), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, CHERRY_WINDMILL, templatePools, "cherry_windmill",
                HolderSet.direct(biomes.getOrThrow(Biomes.CHERRY_GROVE)), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, TOWER, templatePools, "tower",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, GRAVEYARD, templatePools, "graveyard",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BURY);

        registerSurface(context, DARK_SPOOKY_HOUSE, templatePools, "dark_spooky_house",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, SPOOKY_HOUSE_1, templatePools, "spooky_house1",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, SPOOKY_HOUSE_2, templatePools, "spooky_house2",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, CAMP, templatePools, "camp",
                biomes.getOrThrow(BiomeTags.IS_TAIGA), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, MINER_HOUSE, templatePools, "miner_hut",
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, LIGHTNING_ROD_TOWER, templatePools, "lightningrodtower",
                biomes.getOrThrow(BiomeTags.HAS_DESERT_PYRAMID), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, COPPER_GREENHOUSE, templatePools, "greenhouse",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        registerSurface(context, COPPER_STORAGE, templatePools, "storage",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        // Nether Forpost (Invasion) -> Overworld
        registerSurface(context, NETHER_FORPOST, templatePools, "nether_forpost",
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), TerrainAdjustment.BEARD_THIN);

        // === NETHER ===
        // Warped Greenhouse: Height 30-90, TerrainAdjustment.BURY helps avoid floating or roof stuck
        context.register(WARPED_GREENHOUSE, new JigsawStructure(
                structure(HolderSet.direct(biomes.getOrThrow(Biomes.WARPED_FOREST)), TerrainAdjustment.BURY),
                templatePools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "warped_greenhouse"))),
                Optional.empty(),
                1,
                UniformHeight.of(VerticalAnchor.absolute(32), VerticalAnchor.absolute(90)),
                false,
                Optional.empty(),
                80
        ));
    }

    private static void registerSurface(BootstapContext<Structure> context, ResourceKey<Structure> key, HolderGetter<StructureTemplatePool> pools, String poolName, HolderSet<Biome> biomes, TerrainAdjustment adj) {
        context.register(key, new JigsawStructure(
                structure(biomes, adj),
                pools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(CreateStructuresOverhaulMod.MODID, poolName))),
                Optional.empty(),
                1,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80
        ));
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> biomes, TerrainAdjustment adj) {
        return new Structure.StructureSettings(biomes, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, adj);
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }
}