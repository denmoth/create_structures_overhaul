package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTags {
    public static final TagKey<Structure> SPOOKY_LOCATIONS = TagKey.create(Registries.STRUCTURE,
            new ResourceLocation(CreateStructuresOverhaulMod.MODID, "on_spooky_explorer_maps"));

    public static final TagKey<Structure> NETHER_COMPLEX = TagKey.create(Registries.STRUCTURE,
            new ResourceLocation(CreateStructuresOverhaulMod.MODID, "nether_complex"));
}