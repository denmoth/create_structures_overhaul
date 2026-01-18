package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {

    private static final String MODID = CreateStructuresOverhaulMod.MODID;

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {
        generateWindmill(writer, "oak_windmill", Items.OAK_LOG);
        generateWindmill(writer, "birch_windmill", Items.BIRCH_LOG);
        generateWindmill(writer, "spruce_windmill", Items.SPRUCE_LOG);
        generateWindmill(writer, "acacia_windmill", Items.ACACIA_LOG);
        generateWindmill(writer, "cherry_windmill", Items.CHERRY_LOG);

        writer.accept(new ResourceLocation(MODID, "dark_spooky_house"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(2))
                        .add(LootItem.lootTableItem(req("precision_mechanism")).setWeight(5).setQuality(2))
                        .add(LootItem.lootTableItem(req("brass_hand")).setWeight(5))
                        .add(LootItem.lootTableItem(req("rose_quartz")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("electron_tube")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                ));

        writer.accept(new ResourceLocation(MODID, "graveyard"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(Items.SOUL_LANTERN).setWeight(10))
                        .add(LootItem.lootTableItem(Items.CANDLE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(req("brass_sheet")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("zinc_nugget")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(req("polished_rose_quartz")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "spooky_house1"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(15).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(15, 30))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
                        .add(LootItem.lootTableItem(req("smart_chute")).setWeight(5))
                        .add(LootItem.lootTableItem(req("brass_ingot")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("brass_casing")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "spooky_house2"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.PUMPKIN_PIE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.ECHO_SHARD).setWeight(3))
                        .add(LootItem.lootTableItem(req("brass_sheet")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(req("brass_hand")).setWeight(5))
                        .add(LootItem.lootTableItem(req("polished_rose_quartz")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(req("display_board")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "tower"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.PAPER).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.MAP).setWeight(10))
                        .add(LootItem.lootTableItem(Items.SPYGLASS).setWeight(5))
                        .add(LootItem.lootTableItem(req("goggles")).setWeight(8))
                        .add(LootItem.lootTableItem(req("precision_mechanism")).setWeight(3).setQuality(5))
                        .add(LootItem.lootTableItem(req("wrench")).setWeight(10))
                ));

        writer.accept(new ResourceLocation(MODID, "nether_forpost"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.QUARTZ).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                        .add(LootItem.lootTableItem(req("andesite_alloy")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(req("shaft")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(req("mechanical_press")).setWeight(3).setQuality(2))
                        .add(LootItem.lootTableItem(req("cinder_flour")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                        .add(LootItem.lootTableItem(req("sturdy_sheet")).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(req("blaze_cake_base")).setWeight(5))
                ));

        writer.accept(new ResourceLocation(MODID, "warped_greenhouse"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.WARPED_FUNGUS).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(Items.WARPED_WART_BLOCK).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                        .add(LootItem.lootTableItem(req("fluid_tank")).setWeight(10))
                        .add(LootItem.lootTableItem(Items.SHROOMLIGHT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(req("tree_fertilizer")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("empty_blaze_burner")).setWeight(20).setQuality(5))
                        .add(LootItem.lootTableItem(req("blaze_burner")).setWeight(1).setQuality(10))
                ));

        writer.accept(new ResourceLocation(MODID, "camp"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.CHARCOAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(Items.COOKED_BEEF).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                        .add(LootItem.lootTableItem(Items.SPRUCE_LOG).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(req("builders_tea")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                ));

        writer.accept(new ResourceLocation(MODID, "copper_greenhouse"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 6))
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(req("copper_sheet")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("fluid_tank")).setWeight(8))
                        .add(LootItem.lootTableItem(req("fluid_pipe")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(Items.LILAC).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.ROSE_BUSH).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.PEONY).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.OAK_SAPLING).setWeight(5))
                        .add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(5))
                        .add(LootItem.lootTableItem(req("tree_fertilizer")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                ));

        writer.accept(new ResourceLocation(MODID, "copper_storage"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 6))
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                        .add(LootItem.lootTableItem(req("copper_casing")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("spout")).setWeight(5))
                        .add(LootItem.lootTableItem(req("copper_valve_handle")).setWeight(10))
                ));

        writer.accept(new ResourceLocation(MODID, "lightning_tower"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(Items.LIGHTNING_ROD).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(Items.COPPER_BLOCK).setWeight(10))
                        .add(LootItem.lootTableItem(req("zinc_ingot")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(LootItem.lootTableItem(req("electron_tube")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(req("encased_chain_drive")).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(req("brass_ingot")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                        .add(LootItem.lootTableItem(req("precision_mechanism")).setWeight(5).setQuality(5))
                        .add(LootItem.lootTableItem(req("copper_backtank")).setWeight(2).setQuality(8))
                ));

        writer.accept(new ResourceLocation(MODID, "miner_house"), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3, 5))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 12))))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(Items.TNT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(Items.RAIL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(8, 20))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(2).setQuality(5))
                        .add(LootItem.lootTableItem(req("crushed_raw_iron")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
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
                        .add(LootItem.lootTableItem(req("sail_frame")).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                        .add(LootItem.lootTableItem(req("cogwheel")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("large_cogwheel")).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .add(LootItem.lootTableItem(req("white_sail")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                        .add(LootItem.lootTableItem(req("propeller")).setWeight(5))
                        .add(LootItem.lootTableItem(req("andesite_casing")).setWeight(5))
                        .add(LootItem.lootTableItem(req("andesite_alloy")).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                ));
    }

    private Item req(String id) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", id));
        if (item == null || item == Items.AIR) {
            throw new IllegalStateException("CSO Datagen Error: Create item not found: " + id);
        }
        return item;
    }
}