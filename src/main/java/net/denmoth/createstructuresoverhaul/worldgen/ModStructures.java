package net.denmoth.createstructuresoverhaul.worldgen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.datagen.ModTemplatePools;
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
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;

public class ModStructures {

    // --- KEYS ---
    public static final ResourceKey<Structure> OAK_WINDMILL = createKey("oakwindmill");
    public static final ResourceKey<Structure> BIRCH_WINDMILL = createKey("birchwindmill");
    public static final ResourceKey<Structure> SPRUCE_WINDMILL = createKey("sprucewindmill");
    public static final ResourceKey<Structure> ACACIA_WINDMILL = createKey("acaciawindmill");
    public static final ResourceKey<Structure> CHERRY_WINDMILL = createKey("cherrywindmill");

    // Вот эта переменная, которую не может найти ModStructureSets
    public static final ResourceKey<Structure> POST = createKey("post");

    public static final ResourceKey<Structure> SPOOKY_HOUSE_1 = createKey("spooky_house1");
    public static final ResourceKey<Structure> SPOOKY_HOUSE_2 = createKey("spooky_house2");
    public static final ResourceKey<Structure> DARK_SPOOKY_HOUSE = createKey("dark_spooky_house");
    public static final ResourceKey<Structure> GRAVEYARD = createKey("graveyard");
    public static final ResourceKey<Structure> SPOOKY_FARM = createKey("spooky_farm");

    public static final ResourceKey<Structure> MINER_HOUSE = createKey("miner_hut");
    public static final ResourceKey<Structure> COPPER_STORAGE = createKey("storage");
    public static final ResourceKey<Structure> TOWER = createKey("tower");
    public static final ResourceKey<Structure> CAMP = createKey("camp");
    public static final ResourceKey<Structure> COPPER_GREENHOUSE = createKey("greenhouse");
    public static final ResourceKey<Structure> LIGHTNING_ROD_TOWER = createKey("lightningrodtower");

    public static final ResourceKey<Structure> NETHER_FORPOST = createKey("nether_forpost");
    public static final ResourceKey<Structure> WARPED_GREENHOUSE = createKey("warped_greenhouse");
    public static final ResourceKey<Structure> CRIMSON_PORTAL = createKey("crimson_portal");


    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }

    // --- BOOTSTRAP ---
    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        // Windmills
        register(context, OAK_WINDMILL, ModTemplatePools.OAK_WINDMILL, 6, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.FLOWER_FOREST), biomes.getOrThrow(Biomes.PLAINS)));

        register(context, BIRCH_WINDMILL, ModTemplatePools.BIRCH_WINDMILL, 6, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)));

        register(context, SPRUCE_WINDMILL, ModTemplatePools.SPRUCE_WINDMILL, 6, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_TAIGA));

        register(context, ACACIA_WINDMILL, ModTemplatePools.ACACIA_WINDMILL, 6, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_SAVANNA));

        register(context, CHERRY_WINDMILL, ModTemplatePools.CHERRY_WINDMILL, 6, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.CHERRY_GROVE)));

        // Misc
        register(context, POST, ModTemplatePools.POST, 4, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.MEADOW)));

        // Industrial
        register(context, CAMP, ModTemplatePools.CAMP, 4, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS)));

        register(context, TOWER, ModTemplatePools.TOWER, 5, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.SWAMP),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA), biomes.getOrThrow(Biomes.FLOWER_FOREST)));

        register(context, MINER_HOUSE, ModTemplatePools.MINER_HUT, 4, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN));

        register(context, COPPER_STORAGE, ModTemplatePools.STORAGE, 4, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD));

        register(context, COPPER_GREENHOUSE, ModTemplatePools.GREENHOUSE, 4, TerrainAdjustment.BURY,
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.FOREST)));

        register(context, LIGHTNING_ROD_TOWER, ModTemplatePools.LIGHTNING_TOWER, 5, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN));

        // Spooky
        HolderSet<Biome> spookyBiomes = HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.SWAMP));
        register(context, SPOOKY_HOUSE_1, ModTemplatePools.SPOOKY_HOUSE_1, 5, TerrainAdjustment.BEARD_THIN, spookyBiomes);
        register(context, SPOOKY_HOUSE_2, ModTemplatePools.SPOOKY_HOUSE_2, 5, TerrainAdjustment.BEARD_THIN, spookyBiomes);
        register(context, DARK_SPOOKY_HOUSE, ModTemplatePools.DARK_SPOOKY_HOUSE, 5, TerrainAdjustment.BEARD_THIN, spookyBiomes);
        register(context, GRAVEYARD, ModTemplatePools.GRAVEYARD, 4, TerrainAdjustment.BURY, spookyBiomes);
        register(context, SPOOKY_FARM, ModTemplatePools.SPOOKY_FARM, 5, TerrainAdjustment.BEARD_THIN, spookyBiomes);

        // Nether / Invasion
        register(context, NETHER_FORPOST, ModTemplatePools.NETHER_FORPOST, 5, TerrainAdjustment.BEARD_THIN,
                HolderSet.direct(biomes.getOrThrow(Biomes.SAVANNA), biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA), biomes.getOrThrow(Biomes.WINDSWEPT_HILLS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_GRAVELLY_HILLS)));

        register(context, CRIMSON_PORTAL, ModTemplatePools.CRIMSON_PORTAL, 4, TerrainAdjustment.BEARD_THIN,
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD));

        // Nether Proper
        register(context, WARPED_GREENHOUSE, ModTemplatePools.WARPED_GREENHOUSE, 5, TerrainAdjustment.BURY,
                biomes.getOrThrow(BiomeTags.IS_NETHER));
    }

    private static void register(BootstapContext<Structure> context, ResourceKey<Structure> key,
                                 ResourceKey<StructureTemplatePool> startPool, int maxDepth,
                                 TerrainAdjustment adjustment, HolderSet<Biome> biomes) {
        context.register(key, new JigsawStructure(
                new Structure.StructureSettings(
                        biomes,
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        adjustment
                ),
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(startPool),
                maxDepth,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));
    }
}