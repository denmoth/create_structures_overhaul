package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
// Правильный импорт для Forge/Official mappings 1.20.1
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;

import java.util.List;

public class ModProcessors {

    public static final ResourceKey<StructureProcessorList> NETHER_INVASION_50 = createKey("nether_invasion_50");
    public static final ResourceKey<StructureProcessorList> NETHER_INVASION_100 = createKey("nether_invasion_100");
    public static final ResourceKey<StructureProcessorList> EMPTY = createKey("empty");

    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation(CreateStructuresOverhaulMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<StructureProcessorList> context) {
        context.register(EMPTY, new StructureProcessorList(List.of()));

        // --- 50% REPLACEMENT (For Portals & Outposts) ---
        context.register(NETHER_INVASION_50, new StructureProcessorList(List.of(
                // Используем RuleProcessor вместо RuleStructureProcessor
                new RuleProcessor(List.of(
                        // 50% Grass Block -> Netherrack
                        new ProcessorRule(
                                new RandomBlockMatchTest(Blocks.GRASS_BLOCK, 0.5F), // Input + Chance
                                AlwaysTrueTest.INSTANCE,                            // Location
                                Blocks.NETHERRACK.defaultBlockState()               // Output
                        ),
                        // 50% Dirt -> Netherrack
                        new ProcessorRule(
                                new RandomBlockMatchTest(Blocks.DIRT, 0.5F),
                                AlwaysTrueTest.INSTANCE,
                                Blocks.NETHERRACK.defaultBlockState()
                        )
                ))
        )));

        // --- 100% REPLACEMENT (For Greenhouse) ---
        context.register(NETHER_INVASION_100, new StructureProcessorList(List.of(
                new RuleProcessor(List.of(
                        // 100% Grass Block -> Netherrack
                        new ProcessorRule(
                                new BlockMatchTest(Blocks.GRASS_BLOCK), // Strict Match
                                AlwaysTrueTest.INSTANCE,
                                Blocks.NETHERRACK.defaultBlockState()
                        ),
                        // 100% Dirt -> Netherrack
                        new ProcessorRule(
                                new BlockMatchTest(Blocks.DIRT),
                                AlwaysTrueTest.INSTANCE,
                                Blocks.NETHERRACK.defaultBlockState()
                        )
                ))
        )));
    }
}