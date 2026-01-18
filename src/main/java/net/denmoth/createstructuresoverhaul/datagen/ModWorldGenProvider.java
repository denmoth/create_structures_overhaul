package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.worldgen.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.PROCESSOR_LIST, ModProcessors::bootstrap); // <--- ДОБАВИЛИ ЭТУ СТРОКУ

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CreateStructuresOverhaulMod.MODID));
    }
}