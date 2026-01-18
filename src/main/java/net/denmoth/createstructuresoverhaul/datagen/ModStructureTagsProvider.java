package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.worldgen.ModStructures; // ВАЖНЫЙ ИМПОРТ
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagsProvider extends StructureTagsProvider {

    public ModStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, CreateStructuresOverhaulMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.SPOOKY_LOCATIONS)
                .add(ModStructures.DARK_SPOOKY_HOUSE)
                .add(ModStructures.GRAVEYARD)
                .add(ModStructures.SPOOKY_HOUSE_1)
                .add(ModStructures.SPOOKY_HOUSE_2)
                .add(ModStructures.TOWER);

        tag(ModTags.NETHER_COMPLEX)
                .add(ModStructures.NETHER_FORPOST)
                .add(ModStructures.WARPED_GREENHOUSE);
    }
}