package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.worldgen.ModStructures;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends ForgeAdvancementProvider {

    public ModAdvancementProvider(net.minecraft.data.PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    public static class ModAdvancementGenerator implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {

            // ROOT
            Advancement root = Advancement.Builder.advancement()
                    .display(
                            getModItem("andesite_alloy"),
                            Component.translatable("advancements.cso.root.title"),
                            Component.translatable("advancements.cso.root.desc"),
                            new ResourceLocation("minecraft", "textures/block/andesite.png"),
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("tick", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.location().build()))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "root"), existingFileHelper);

            // ================= WINDMILLS =================
            // ANY
            Advancement windmillsAny = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            getModItem("white_sail"),
                            Component.translatable("advancements.cso.windmills.title"),
                            Component.translatable("advancements.cso.windmills.desc"),
                            null, FrameType.TASK, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("find_oak_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.OAK_WINDMILL)))
                    .addCriterion("find_birch_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.BIRCH_WINDMILL)))
                    .addCriterion("find_spruce_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPRUCE_WINDMILL)))
                    .addCriterion("find_acacia_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.ACACIA_WINDMILL)))
                    .addCriterion("find_cherry_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CHERRY_WINDMILL)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_any_windmill"), existingFileHelper);

            // ALL
            Advancement.Builder.advancement()
                    .parent(windmillsAny)
                    .display(
                            getModItem("windmill_bearing"),
                            Component.translatable("advancements.cso.windmills_all.title"),
                            Component.translatable("advancements.cso.windmills_all.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .addCriterion("find_oak_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.OAK_WINDMILL)))
                    .addCriterion("find_birch_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.BIRCH_WINDMILL)))
                    .addCriterion("find_spruce_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPRUCE_WINDMILL)))
                    .addCriterion("find_acacia_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.ACACIA_WINDMILL)))
                    .addCriterion("find_cherry_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CHERRY_WINDMILL)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_all_windmills"), existingFileHelper);

            // ================= SPOOKY =================
            // ANY
            Advancement spookyAny = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.SOUL_LANTERN,
                            Component.translatable("advancements.cso.spooky.title"),
                            Component.translatable("advancements.cso.spooky.desc"),
                            null, FrameType.TASK, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("find_dark_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.DARK_SPOOKY_HOUSE)))
                    .addCriterion("find_house1", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_1)))
                    .addCriterion("find_house2", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_2)))
                    .addCriterion("find_graveyard", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.GRAVEYARD)))
                    .addCriterion("find_tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_any_spooky"), existingFileHelper);

            // ALL (NEW: GHOSTBUSTER)
            Advancement.Builder.advancement()
                    .parent(spookyAny)
                    .display(
                            Items.WITHER_SKELETON_SKULL,
                            Component.translatable("advancements.cso.spooky_all.title"),
                            Component.translatable("advancements.cso.spooky_all.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .addCriterion("find_dark_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.DARK_SPOOKY_HOUSE)))
                    .addCriterion("find_house1", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_1)))
                    .addCriterion("find_house2", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_2)))
                    .addCriterion("find_graveyard", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.GRAVEYARD)))
                    .addCriterion("find_tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_all_spooky"), existingFileHelper);

            // ================= INDUSTRIAL =================
            // ANY
            Advancement industrialAny = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            getModItem("copper_casing"),
                            Component.translatable("advancements.cso.industrial.title"),
                            Component.translatable("advancements.cso.industrial.desc"),
                            null, FrameType.TASK, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("camp", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CAMP)))
                    .addCriterion("miner", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.MINER_HOUSE)))
                    .addCriterion("copper_g", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_GREENHOUSE)))
                    .addCriterion("copper_s", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_STORAGE)))
                    .addCriterion("light", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.LIGHTNING_ROD_TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_any_industrial"), existingFileHelper);

            // ALL (NEW: HEAVY INDUSTRY)
            Advancement.Builder.advancement()
                    .parent(industrialAny)
                    .display(
                            getModItem("railway_casing"),
                            Component.translatable("advancements.cso.industrial_all.title"),
                            Component.translatable("advancements.cso.industrial_all.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .addCriterion("camp", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CAMP)))
                    .addCriterion("miner", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.MINER_HOUSE)))
                    .addCriterion("copper_g", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_GREENHOUSE)))
                    .addCriterion("copper_s", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_STORAGE)))
                    .addCriterion("light", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.LIGHTNING_ROD_TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_all_industrial"), existingFileHelper);

            // ================= OTHERS =================
            // TRADING
            Advancement trading = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.FILLED_MAP,
                            Component.translatable("advancements.cso.trading.title"),
                            Component.translatable("advancements.cso.trading.desc"),
                            null, FrameType.TASK, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("buy_map", new TradeTrigger.TriggerInstance(
                            ContextAwarePredicate.ANY,
                            ContextAwarePredicate.ANY,
                            ItemPredicate.Builder.item().of(Items.FILLED_MAP).build()
                    ))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "trading"), existingFileHelper);

            // NETHER: Any
            Advancement nether = Advancement.Builder.advancement()
                    .parent(industrialAny) // Ответвление от индустрии
                    .display(
                            getModItem("blaze_burner"),
                            Component.translatable("advancements.cso.nether.title"),
                            Component.translatable("advancements.cso.nether.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("find_nether_outpost", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.NETHER_FORPOST)))
                    .addCriterion("find_greenhouse", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.WARPED_GREENHOUSE)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_nether"), existingFileHelper);

            // INVASION
            Advancement.Builder.advancement()
                    .parent(industrialAny)
                    .display(
                            Items.NETHER_BRICK,
                            Component.translatable("advancements.cso.invasion.title"),
                            Component.translatable("advancements.cso.invasion.desc"),
                            null, FrameType.CHALLENGE, true, true, true
                    )
                    .addCriterion("find_outpost", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.NETHER_FORPOST)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "invasion"), existingFileHelper);

            // GRAND TOUR: THE END
            Advancement.Builder.advancement()
                    .parent(nether)
                    .display(
                            Items.SPYGLASS,
                            Component.translatable("advancements.cso.all.title"),
                            Component.translatable("advancements.cso.all.desc"),
                            null, FrameType.CHALLENGE, true, true, false
                    )
                    // Windmills
                    .addCriterion("oak_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.OAK_WINDMILL)))
                    .addCriterion("birch_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.BIRCH_WINDMILL)))
                    .addCriterion("spruce_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPRUCE_WINDMILL)))
                    .addCriterion("acacia_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.ACACIA_WINDMILL)))
                    .addCriterion("cherry_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CHERRY_WINDMILL)))
                    // Spooky
                    .addCriterion("tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.TOWER)))
                    .addCriterion("grave", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.GRAVEYARD)))
                    .addCriterion("dark_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.DARK_SPOOKY_HOUSE)))
                    .addCriterion("house1", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_1)))
                    .addCriterion("house2", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_2)))
                    // Nether
                    .addCriterion("nether_out", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.NETHER_FORPOST)))
                    .addCriterion("warped_green", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.WARPED_GREENHOUSE)))
                    // Industrial
                    .addCriterion("camp", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CAMP)))
                    .addCriterion("miner", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.MINER_HOUSE)))
                    .addCriterion("copper_g", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_GREENHOUSE)))
                    .addCriterion("copper_s", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.COPPER_STORAGE)))
                    .addCriterion("light", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.LIGHTNING_ROD_TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "grand_tour"), existingFileHelper);
        }

        private ItemLike getModItem(String id) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", id));
            return item != null ? item : Items.BARRIER;
        }
    }
}