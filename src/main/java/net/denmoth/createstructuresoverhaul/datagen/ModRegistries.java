package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.worldgen.ModStructures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ModRegistries {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap)
            .add(Registries.PROCESSOR_LIST, ModProcessors::bootstrap); // <--- ДОБАВИЛИ ЭТУ СТРОКУ
}