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
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public class ModTemplatePools {

    // Windmills
    public static final ResourceKey<StructureTemplatePool> OAK_WINDMILL = createKey("oak_windmill");
    public static final ResourceKey<StructureTemplatePool> BIRCH_WINDMILL = createKey("birch_windmill");
    public static final ResourceKey<StructureTemplatePool> SPRUCE_WINDMILL = createKey("spruce_windmill");
    public static final ResourceKey<StructureTemplatePool> ACACIA_WINDMILL = createKey("acacia_windmill");
    public static final ResourceKey<StructureTemplatePool> CHERRY_WINDMILL = createKey("cherry_windmill");

    // Spooky
    public static final ResourceKey<StructureTemplatePool> TOWER = createKey("tower");
    public static final ResourceKey<StructureTemplatePool> GRAVEYARD = createKey("graveyard");
    public static final ResourceKey<StructureTemplatePool> DARK_SPOOKY_HOUSE = createKey("dark_spooky_house");
    public static final ResourceKey<StructureTemplatePool> SPOOKY_HOUSE_1 = createKey("spooky_house1");
    public static final ResourceKey<StructureTemplatePool> SPOOKY_HOUSE_2 = createKey("spooky_house2");

    // Nether
    public static final ResourceKey<StructureTemplatePool> NETHER_FORPOST = createKey("nether_forpost");
    public static final ResourceKey<StructureTemplatePool> WARPED_GREENHOUSE = createKey("warped_greenhouse");

    // Other
    public static final ResourceKey<StructureTemplatePool> CAMP = createKey("camp");
    public static final ResourceKey<StructureTemplatePool> MINER_HUT = createKey("miner_hut");
    public static final ResourceKey<StructureTemplatePool> LIGHTNING_ROD_TOWER = createKey("lightningrodtower");
    public static final ResourceKey<StructureTemplatePool> GREENHOUSE = createKey("greenhouse");
    public static final ResourceKey<StructureTemplatePool> STORAGE = createKey("storage");

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

        // FIX: Создаем ключ вручную, так как ProcessorLists.EMPTY имеет приватный доступ
        Holder<StructureProcessorList> empty = processors.getOrThrow(
                ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty"))
        );

        // Windmills
        register(context, OAK_WINDMILL, "oak_windmill", empty);
        register(context, BIRCH_WINDMILL, "birch_windmill", empty);
        register(context, SPRUCE_WINDMILL, "spruce_windmill", empty);
        register(context, ACACIA_WINDMILL, "acacia_windmill", empty);
        register(context, CHERRY_WINDMILL, "cherry_windmill", empty);

        // Spooky
        register(context, TOWER, "tower", empty);
        register(context, GRAVEYARD, "graveyard", empty);
        register(context, DARK_SPOOKY_HOUSE, "dark_spooky_house", empty);
        register(context, SPOOKY_HOUSE_1, "spooky_house1", empty);
        register(context, SPOOKY_HOUSE_2, "spooky_house2", empty);

        // Nether
        register(context, NETHER_FORPOST, "nether_forpost", empty);
        register(context, WARPED_GREENHOUSE, "warped_greenhouse", empty);

        // Other
        register(context, CAMP, "camp", empty);
        register(context, MINER_HUT, "miner_hut", empty);
        register(context, LIGHTNING_ROD_TOWER, "lightningrodtower", empty);
        register(context, GREENHOUSE, "greenhouse", empty);
        register(context, STORAGE, "storage", empty);
    }

    private static void register(BootstapContext<StructureTemplatePool> context, ResourceKey<StructureTemplatePool> key, String nbtName, Holder<StructureProcessorList> processors) {
        context.register(key, new StructureTemplatePool(
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY),
                List.of(Pair.of(SinglePoolElement.single(CreateStructuresOverhaulMod.MODID + ":" + nbtName, processors), 1)),
                StructureTemplatePool.Projection.RIGID
        ));
    }

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }
}