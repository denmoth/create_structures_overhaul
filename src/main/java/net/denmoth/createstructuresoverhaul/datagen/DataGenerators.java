package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataGenerators {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // 1. Loot Tables
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModChestLootTables::new, LootContextParamSets.CHEST)
        )));

        // 2. Registry Data (Structures, Sets, Pools)
        // Создаем провайдер отдельно, чтобы получить его Output Provider
        DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                new RegistrySetBuilder()
                        .add(Registries.STRUCTURE, ModStructures::bootstrap)
                        .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
                        .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap),
                Set.of(CreateStructuresOverhaulMod.MODID)
        );
        generator.addProvider(event.includeServer(), datapackProvider);

        // 3. Tags (FIXED DEPENDENCY)
        // Передаем RegistryLookup из datapackProvider, чтобы теги видели свежесозданные структуры
        generator.addProvider(event.includeServer(), new ModStructureTagsProvider(
                packOutput,
                datapackProvider.getRegistryProvider(), // <--- CRITICAL FIX
                existingFileHelper
        ));

        // 4. Advancements
        // Ачивкам тоже нужен lookup, чтобы проверять существование структур
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(
                packOutput,
                datapackProvider.getRegistryProvider(), // <--- CRITICAL FIX
                existingFileHelper
        ));

        // 5. Lang
        generator.addProvider(event.includeClient(), new ModLanguageProvider.EN(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.RU(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.UA(packOutput));
    }
}