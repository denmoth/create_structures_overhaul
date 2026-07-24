package net.denmoth.cso.worldgen.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.denmoth.cso.CSOMain;

import org.jetbrains.annotations.Nullable;

public class CopperGreenhouseProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<CopperGreenhouseProcessor> CODEC = com.mojang.serialization.MapCodec.unit(CopperGreenhouseProcessor::new);
    public static final CopperGreenhouseProcessor INSTANCE = new CopperGreenhouseProcessor();

    private CopperGreenhouseProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        RandomSource globalRandom = settings.getRandom(BlockPos.ZERO);
        RandomSource localRandom = settings.getRandom(blockInfoOut.pos());

        boolean isDecayed = globalRandom.nextFloat() < 0.10f;
        float cobwebChance = 0.19f + globalRandom.nextFloat() * 0.11f;

        BlockState state = blockInfoOut.state();
        Block block = state.getBlock();
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        // 1. Handle Copper Aging
        if (isDecayed && blockName.contains("copper")) {
            Block aged = getAgedCopper(blockName, localRandom.nextBoolean());
            if (aged != null) {
                BlockState newState = aged.defaultBlockState();
                // Copy properties
                for (Property<?> prop : state.getProperties()) {
                    if (newState.hasProperty(prop)) {
                        newState = copyProperty(state, newState, prop);
                    }
                }
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), newState, blockInfoOut.nbt());
            }
        }

        // 2. Handle Crops
        if (block instanceof CropBlock || blockName.equals("wheat") || blockName.equals("carrots") || blockName.equals("potatoes") || blockName.equals("beetroots") || blockName.contains("cabbages") || blockName.contains("tomatoes") || blockName.contains("onions")) {
            
            // Decay into cobwebs
            if (isDecayed && localRandom.nextFloat() < cobwebChance) {
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), Blocks.COBWEB.defaultBlockState(), null);
            }

            // Grouping logic based on NBT tag written by python script
            int groupId = blockInfoOut.nbt() != null && blockInfoOut.nbt().contains("CsoCropGroup") 
                    ? blockInfoOut.nbt().getInt("CsoCropGroup") 
                    : (blockInfoOut.pos().getX() / 3 * 31 + blockInfoOut.pos().getZ() / 3); // Fallback if no NBT

            long structureSeed = settings.getRandom(BlockPos.ZERO).nextLong();
            long groupSeed = structureSeed ^ ((long) groupId * 31337L);
            RandomSource groupRandom = RandomSource.create(groupSeed);

            Block newCropBlock = null;
            if (groupRandom.nextBoolean()) {
                newCropBlock = getFdCrop(groupRandom);
            }
            if (newCropBlock == null) {
                newCropBlock = getVanillaCrop(groupRandom);
            }

            BlockState newCropState = newCropBlock.defaultBlockState();
            
            // Randomize age (above middle)
            for (Property<?> prop : newCropState.getProperties()) {
                if (prop.getName().equals("age") && prop instanceof IntegerProperty ageProp) {
                    int max = ageProp.getPossibleValues().stream().max(Integer::compareTo).orElse(7);
                    int min = max / 2 + 1;
                    if (min > max) min = max;
                    int age = min + localRandom.nextInt(max - min + 1);
                    newCropState = newCropState.setValue(ageProp, age);
                    break;
                }
            }

            return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), newCropState, null);
        }

        // 3. Handle Chest Loot Interception
        if (block == Blocks.CHEST && blockInfoOut.nbt() != null && blockInfoOut.nbt().contains("LootTable")) {
            if (level instanceof net.minecraft.world.level.ServerLevelAccessor serverLevelAccessor) {
                net.minecraft.server.MinecraftServer server = serverLevelAccessor.getServer();
                if (server != null) {
                    ResourceLocation lootTableId = new ResourceLocation(blockInfoOut.nbt().getString("LootTable"));
                    net.minecraft.world.level.storage.loot.LootTable lootTable = server.getLootData().getLootTable(lootTableId);
                    net.minecraft.world.level.storage.loot.LootParams params = new net.minecraft.world.level.storage.loot.LootParams.Builder(serverLevelAccessor.getLevel())
                            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.ORIGIN, net.minecraft.world.phys.Vec3.atCenterOf(pos))
                            .create(net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.CHEST);
                    
                    it.unimi.dsi.fastutil.objects.ObjectArrayList<net.minecraft.world.item.ItemStack> loot = lootTable.getRandomItems(params);
                    
                    long structureSeed = settings.getRandom(BlockPos.ZERO).nextLong();
                    // Add bonus crops for the 4 rows found in the greenhouse
                    for (int i = 1; i <= 4; i++) {
                        long gs = structureSeed ^ ((long) i * 31337L);
                        RandomSource gr = RandomSource.create(gs);
                        Block bonusCrop = null;
                        if (gr.nextBoolean()) {
                            bonusCrop = getFdCrop(gr);
                        }
                        if (bonusCrop == null) {
                            bonusCrop = getVanillaCrop(gr);
                        }
                        if (bonusCrop != null && bonusCrop.asItem() != net.minecraft.world.item.Items.AIR) {
                            int count = 2 + localRandom.nextInt(4);
                            loot.add(new net.minecraft.world.item.ItemStack(bonusCrop.asItem(), count));
                        }
                    }
                    
                    net.minecraft.nbt.CompoundTag newNbt = blockInfoOut.nbt().copy();
                    newNbt.remove("LootTable");
                    newNbt.remove("LootTableSeed");
                    net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> nonNullLoot = net.minecraft.core.NonNullList.create();
                    nonNullLoot.addAll(loot);
                    net.minecraft.world.ContainerHelper.saveAllItems(newNbt, nonNullLoot);
                    
                    return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), blockInfoOut.state(), newNbt);
                }
            }
        }

        return blockInfoOut;
    }

    private static Block getAgedCopper(String name, boolean oxidized) {
        if (name.contains("cut_copper_stairs")) return oxidized ? Blocks.OXIDIZED_CUT_COPPER_STAIRS : Blocks.WEATHERED_CUT_COPPER_STAIRS;
        if (name.contains("cut_copper_slab")) return oxidized ? Blocks.OXIDIZED_CUT_COPPER_SLAB : Blocks.WEATHERED_CUT_COPPER_SLAB;
        if (name.contains("cut_copper")) return oxidized ? Blocks.OXIDIZED_CUT_COPPER : Blocks.WEATHERED_CUT_COPPER;
        if (name.contains("copper_block") || name.equals("exposed_copper") || name.equals("weathered_copper") || name.equals("oxidized_copper") || name.contains("waxed_")) {
            return oxidized ? Blocks.OXIDIZED_COPPER : Blocks.WEATHERED_COPPER;
        }
        return null;
    }

    private static volatile java.util.List<Block> validFdCrops = null;

    private static Block getFdCrop(RandomSource random) {
        java.util.List<Block> localList = validFdCrops;
        if (localList == null) {
            localList = new java.util.ArrayList<>();
            // Base FD + Popular Addon Crops (10 total)
            String[] crops = {
                    "farmersdelight:cabbages", 
                    "farmersdelight:tomatoes", 
                    "farmersdelight:onions",
                    "corndelight:corn_crop",
                    "culturaldelights:cucumbers",
                    "culturaldelights:eggplants",
                    "culturaldelights:white_eggplants",
                    "pineappledelight:pineapple_crop",
                    "festivedelight:mint_crop",
                    "cuisinedelight:pepper_crop"
            };
            
            for (String selected : crops) {
                Block block = BuiltInRegistries.BLOCK.get(new ResourceLocation(selected));
                if (block != Blocks.AIR) {
                    localList.add(block);
                }
            }
            validFdCrops = localList;
        }
        
        if (localList.isEmpty()) return null;
        return localList.get(random.nextInt(localList.size()));
    }

    private static Block getVanillaCrop(RandomSource random) {
        Block[] crops = {Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS};
        return crops[random.nextInt(crops.length)];
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.setValue(property, from.getValue(property));
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CSOMain.COPPER_GREENHOUSE_PROCESSOR;
    }
}
