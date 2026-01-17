package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {

    private static final TagKey<Structure> BURIED_TREASURE_TAG = TagKey.create(Registries.STRUCTURE, new ResourceLocation("minecraft", "buried_treasure"));
    private static final String MODID = CreateStructuresOverhaulMod.MODID;

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {
        // Windmills
        generateWindmill(writer, "oak_windmill", Items.OAK_LOG);
        generateWindmill(writer, "birch_windmill", Items.BIRCH_LOG);
        generateWindmill(writer, "spruce_windmill", Items.SPRUCE_LOG);
        generateWindmill(writer, "acacia_windmill", Items.ACACIA_LOG);
        generateWindmill(writer, "cherry_windmill", Items.CHERRY_LOG);

        // Spooky
        writer.accept(new ResourceLocation(MODID, "dark_spooky_house"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(2))
                        .add(LootItem.lootTableItem(create("precision_mechanism")).setWeight(5).setQuality(2))
                        .add(LootItem.lootTableItem(create("brass_hand")).setWeight(5))
                        .add(LootItem.lootTableItem(create("rose_quartz")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("electron_tube")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                ));

        writer.accept(new ResourceLocation(MODID, "graveyard"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(Items.SOUL_LANTERN).setWeight(10))
                        .add(LootItem.lootTableItem(Items.CANDLE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(create("brass_sheet")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("zinc_nugget")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(create("polished_rose_quartz")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "spooky_house1"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(15).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(15, 30))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
                        .add(LootItem.lootTableItem(create("smart_chute")).setWeight(5))
                        .add(LootItem.lootTableItem(create("brass_ingot")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("brass_casing")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "spooky_house2"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.PUMPKIN_PIE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.ECHO_SHARD).setWeight(3))
                        .add(LootItem.lootTableItem(create("brass_sheet")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(create("brass_hand")).setWeight(5))
                        .add(LootItem.lootTableItem(create("polished_rose_quartz")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(create("display_board")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "tower"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.PAPER).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.MAP).setWeight(10))
                        .add(LootItem.lootTableItem(Items.SPYGLASS).setWeight(5))
                        .add(LootItem.lootTableItem(create("goggles")).setWeight(8))
                        .add(LootItem.lootTableItem(create("precision_mechanism")).setWeight(3).setQuality(5))
                        .add(LootItem.lootTableItem(create("wrench")).setWeight(10))
                ));

        // Nether Forpost (Invasion)
        writer.accept(new ResourceLocation(MODID, "nether_forpost"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.QUARTZ).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                        .add(LootItem.lootTableItem(create("andesite_alloy")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(create("shaft")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(create("mechanical_press")).setWeight(3).setQuality(2))
                        .add(LootItem.lootTableItem(create("cinder_flour")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                        .add(LootItem.lootTableItem(create("sturdy_sheet")).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(create("blaze_cake_base")).setWeight(5))
                ));

        // Nether Greenhouse - BUFF: Blaze Burners
        writer.accept(new ResourceLocation(MODID, "warped_greenhouse"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.WARPED_FUNGUS).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(Items.WARPED_WART_BLOCK).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                        .add(LootItem.lootTableItem(create("fluid_tank")).setWeight(10))
                        .add(LootItem.lootTableItem(Items.SHROOMLIGHT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(create("tree_fertilizer")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        // The "Cage" (Burner)
                        .add(LootItem.lootTableItem(create("empty_blaze_burner")).setWeight(20).setQuality(5))
                        .add(LootItem.lootTableItem(create("blaze_burner")).setWeight(1).setQuality(10)) // Rare filled one
                ));

        // Camp
        writer.accept(new ResourceLocation(MODID, "camp"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.CHARCOAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(Items.COOKED_BEEF).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                        .add(LootItem.lootTableItem(Items.SPRUCE_LOG).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(create("builders_tea")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                ));

        // Copper Greenhouse
        writer.accept(new ResourceLocation(MODID, "copper_greenhouse"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 6))
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(create("copper_sheet")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("fluid_tank")).setWeight(8))
                        .add(LootItem.lootTableItem(create("fluid_pipe")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.LILAC).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.ROSE_BUSH).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.PEONY).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.OAK_SAPLING).setWeight(5))
                        .add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(5))
                        .add(LootItem.lootTableItem(create("tree_fertilizer")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                ));

        writer.accept(new ResourceLocation(MODID, "copper_storage"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 6))
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(create("copper_casing")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("spout")).setWeight(5))
                        .add(LootItem.lootTableItem(create("copper_valve_handle")).setWeight(10))
                ));

        // Lightning - HUGE BUFF
        writer.accept(new ResourceLocation(MODID, "lightning_tower"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.LIGHTNING_ROD).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.COPPER_BLOCK).setWeight(10))
                        .add(LootItem.lootTableItem(create("zinc_ingot")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(create("electron_tube")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(create("encased_chain_drive")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(create("brass_ingot")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(create("precision_mechanism")).setWeight(5).setQuality(5))
                        .add(LootItem.lootTableItem(create("copper_backtank")).setWeight(2).setQuality(8))
                ));

        // Miner
        writer.accept(new ResourceLocation(MODID, "miner_house"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 12))))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(Items.TNT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(Items.RAIL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(8, 20))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(create("crushed_raw_iron")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                ));
    }

    private void generateWindmill(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Item logItem) {
        writer.accept(new ResourceLocation(MODID, name), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3, 5))
                        .setBonusRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(logItem).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.WHEAT).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                        .add(LootItem.lootTableItem(Items.BREAD).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("sail_frame")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(create("cogwheel")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("large_cogwheel")).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(create("white_sail")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(create("propeller")).setWeight(5))
                        .add(LootItem.lootTableItem(create("andesite_casing")).setWeight(5))
                        .add(LootItem.lootTableItem(create("andesite_alloy")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                ));
    }

    private Item create(String id) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", id));
        if (item == null || item == Items.AIR) {
            return Items.AIR;
        }
        return item;
    }
}