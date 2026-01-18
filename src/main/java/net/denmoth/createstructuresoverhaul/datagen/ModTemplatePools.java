package net.denmoth.createstructuresoverhaul.datagen;

import com.mojang.datafixers.util.Pair;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;
import java.util.function.Function;

public class ModTemplatePools {

    // --- KEYS ---
    public static final ResourceKey<StructureTemplatePool> OAK_WINDMILL = createKey("oakwindmill/start");
    public static final ResourceKey<StructureTemplatePool> BIRCH_WINDMILL = createKey("birchwindmill/start");
    public static final ResourceKey<StructureTemplatePool> SPRUCE_WINDMILL = createKey("sprucewindmill/start");
    public static final ResourceKey<StructureTemplatePool> ACACIA_WINDMILL = createKey("acaciawindmill/start");
    public static final ResourceKey<StructureTemplatePool> CHERRY_WINDMILL = createKey("cherrywindmill/start");
    public static final ResourceKey<StructureTemplatePool> POST = createKey("post/start");

    public static final ResourceKey<StructureTemplatePool> SPOOKY_HOUSE_1 = createKey("spooky_house1/start");
    public static final ResourceKey<StructureTemplatePool> SPOOKY_HOUSE_2 = createKey("spooky_house2/start");
    public static final ResourceKey<StructureTemplatePool> DARK_SPOOKY_HOUSE = createKey("dark_spooky_house/start");
    public static final ResourceKey<StructureTemplatePool> GRAVEYARD = createKey("graveyard/start");
    public static final ResourceKey<StructureTemplatePool> SPOOKY_FARM = createKey("spooky_farm/start");

    public static final ResourceKey<StructureTemplatePool> MINER_HUT = createKey("miner_hut/start");
    public static final ResourceKey<StructureTemplatePool> STORAGE = createKey("storage/start");
    public static final ResourceKey<StructureTemplatePool> TOWER = createKey("tower/start");
    public static final ResourceKey<StructureTemplatePool> CAMP = createKey("camp/start");
    public static final ResourceKey<StructureTemplatePool> GREENHOUSE = createKey("greenhouse/start");
    public static final ResourceKey<StructureTemplatePool> LIGHTNING_TOWER = createKey("lightningrodtower/start");

    public static final ResourceKey<StructureTemplatePool> NETHER_FORPOST = createKey("nether_forpost/start");
    public static final ResourceKey<StructureTemplatePool> WARPED_GREENHOUSE = createKey("warped_greenhouse/start");
    public static final ResourceKey<StructureTemplatePool> CRIMSON_PORTAL = createKey("crimson_portal/start");

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> empty = pools.getOrThrow(Pools.EMPTY);

        HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

        // ИСПРАВЛЕНО: Используем наш собственный ModProcessors.EMPTY
        Holder<StructureProcessorList> emptyProc = processors.getOrThrow(ModProcessors.EMPTY);

        Holder<StructureProcessorList> invasion50 = processors.getOrThrow(ModProcessors.NETHER_INVASION_50);
        Holder<StructureProcessorList> invasion100 = processors.getOrThrow(ModProcessors.NETHER_INVASION_100);

        // --- ОБЫЧНЫЕ СТРУКТУРЫ ---
        register(context, OAK_WINDMILL, "oak_windmill", empty, emptyProc);
        register(context, BIRCH_WINDMILL, "birch_windmill", empty, emptyProc);
        register(context, SPRUCE_WINDMILL, "spruce_windmill", empty, emptyProc);
        register(context, ACACIA_WINDMILL, "acacia_windmill", empty, emptyProc);
        register(context, CHERRY_WINDMILL, "cherry_windmill", empty, emptyProc);
        register(context, POST, "post", empty, emptyProc);

        register(context, SPOOKY_HOUSE_1, "spooky_house1", empty, emptyProc);
        register(context, SPOOKY_HOUSE_2, "spooky_house2", empty, emptyProc);
        register(context, DARK_SPOOKY_HOUSE, "dark_spooky_house", empty, emptyProc);
        register(context, GRAVEYARD, "graveyard", empty, emptyProc);
        register(context, SPOOKY_FARM, "spooky_farm", empty, emptyProc);

        register(context, MINER_HUT, "miner_hut", empty, emptyProc);
        register(context, STORAGE, "storage", empty, emptyProc);
        register(context, TOWER, "tower", empty, emptyProc);
        register(context, CAMP, "camp", empty, emptyProc);
        register(context, GREENHOUSE, "greenhouse", empty, emptyProc);
        register(context, LIGHTNING_TOWER, "lightningrodtower", empty, emptyProc);

        // --- INVASION STRUCTURES ---
        register(context, NETHER_FORPOST, "nether_forpost", empty, invasion50);
        register(context, CRIMSON_PORTAL, "crimson_portal", empty, invasion50);
        register(context, WARPED_GREENHOUSE, "warped_greenhouse", empty, invasion100);
    }

    private static void register(BootstapContext<StructureTemplatePool> context,
                                 ResourceKey<StructureTemplatePool> key,
                                 String nbtName,
                                 Holder<StructureTemplatePool> fallback,
                                 Holder<StructureProcessorList> processorList) {

        Function<StructureTemplatePool.Projection, ? extends StructurePoolElement> elementFactory =
                StructurePoolElement.single(CreateStructuresOverhaulMod.MODID + ":" + nbtName, processorList);

        context.register(key, new StructureTemplatePool(
                fallback,
                List.of(Pair.of(elementFactory, 1)),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}