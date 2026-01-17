package net.denmoth.createstructuresoverhaul.datagen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ModRegistries {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // Структуры (сами объекты строений)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            // Сеты (правила спавна и биомы)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            // ПУЛЫ (NBT файлы и их вариации) - ДОБАВИЛ ЭТО
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);
}