package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
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

            // WINDMILLS: Any -> All
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

            // Here is the "Find ALL" achievement
            Advancement windmillsAll = Advancement.Builder.advancement()
                    .parent(windmillsAny)
                    .display(
                            getModItem("windmill_bearing"),
                            Component.translatable("advancements.cso.windmills_all.title"),
                            Component.translatable("advancements.cso.windmills_all.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    // Implicit AND strategy
                    .addCriterion("find_oak_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.OAK_WINDMILL)))
                    .addCriterion("find_birch_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.BIRCH_WINDMILL)))
                    .addCriterion("find_spruce_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPRUCE_WINDMILL)))
                    .addCriterion("find_acacia_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.ACACIA_WINDMILL)))
                    .addCriterion("find_cherry_windmill", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CHERRY_WINDMILL)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_all_windmills"), existingFileHelper);

            // SPOOKY: Any
            Advancement spookyAny = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.SOUL_LANTERN,
                            Component.translatable("advancements.cso.spooky.title"),
                            Component.translatable("advancements.cso.spooky.desc"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("find_dark_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.DARK_SPOOKY_HOUSE)))
                    .addCriterion("find_house1", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_1)))
                    .addCriterion("find_house2", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_2)))
                    .addCriterion("find_graveyard", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.GRAVEYARD)))
                    .addCriterion("find_tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.TOWER)))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "find_any_spooky"), existingFileHelper);

            // INDUSTRIAL: Any
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

            // TRADING (FIXED COMPILE ERROR)
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
                            ItemPredicate.Builder.item().of(Items.FILLED_MAP).build() // .build() was missing
                    ))
                    .save(saver, new ResourceLocation(CreateStructuresOverhaulMod.MODID, "trading"), existingFileHelper);

            // NETHER: Any Nether structure
            Advancement nether = Advancement.Builder.advancement()
                    .parent(industrialAny)
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

            // SECRET: INVASION
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

            // GRAND TOUR: ALL
            Advancement.Builder.advancement()
                    .parent(nether)
                    .display(
                            Items.SPYGLASS,
                            Component.translatable("advancements.cso.all.title"),
                            Component.translatable("advancements.cso.all.desc"),
                            null, FrameType.CHALLENGE, true, true, false
                    )
                    .addCriterion("oak_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.OAK_WINDMILL)))
                    .addCriterion("birch_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.BIRCH_WINDMILL)))
                    .addCriterion("spruce_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPRUCE_WINDMILL)))
                    .addCriterion("acacia_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.ACACIA_WINDMILL)))
                    .addCriterion("cherry_w", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.CHERRY_WINDMILL)))
                    .addCriterion("tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.TOWER)))
                    .addCriterion("grave", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.GRAVEYARD)))
                    .addCriterion("dark_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.DARK_SPOOKY_HOUSE)))
                    .addCriterion("house1", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_1)))
                    .addCriterion("house2", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.SPOOKY_HOUSE_2)))
                    .addCriterion("nether_out", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.NETHER_FORPOST)))
                    .addCriterion("warped_green", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.WARPED_GREENHOUSE)))
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