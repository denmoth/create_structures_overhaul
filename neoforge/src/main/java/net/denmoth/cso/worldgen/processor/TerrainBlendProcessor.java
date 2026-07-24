package net.denmoth.cso.worldgen.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.block.Blocks;
import net.denmoth.cso.CSOMain;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TerrainBlendProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<TerrainBlendProcessor> CODEC = com.mojang.serialization.MapCodec.unit(TerrainBlendProcessor::new);

    public TerrainBlendProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlaceSettings settings) {
        if (!(level instanceof WorldGenLevel worldGenLevel)) {
            return blockInfoGlobal;
        }

        BlockState stateOut = blockInfoGlobal.state();
        if (stateOut.getBlock() != Blocks.GRASS_BLOCK) {
            return blockInfoGlobal;
        }
        
        // ЕСЛИ ТРАВА ВЫСОКО НЕ РЕАГИРУЙ
        if (blockInfoLocal.pos().getY() > 2) {
            return blockInfoGlobal;
        }

        BlockPos belowPos = pos.below();
        if (!isSafe(level, belowPos)) {
            return blockInfoGlobal;
        }
        BlockState stateBelow = level.getBlockState(belowPos);
        
        // If the block below is solid, adapt to the actual floor (belowPos)
        if (stateBelow.isSolidRender(level, belowPos) || stateBelow.canOcclude()) {
            BlockState predominant = getPredominantNaturalBlock(level, belowPos);
            return new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), predominant, blockInfoGlobal.nbt());
        }

        // If it's floating, search for the nearest solid natural block to build a wedge/slope
        int searchRadiusH = 6;
        int searchRadiusV = 18; 
        BlockPos closestSolid = null;
        double minDistance = Double.MAX_VALUE;

        for (int y = 0; y >= -searchRadiusV; y--) {
            for (int x = -searchRadiusH; x <= searchRadiusH; x++) {
                for (int z = -searchRadiusH; z <= searchRadiusH; z++) {
                    BlockPos p = pos.offset(x, y, z);
                    if (!isSafe(level, p)) continue;
                    BlockState s = level.getBlockState(p);
                    if ((s.isSolidRender(level, p) || s.canOcclude()) && isNaturalBlock(s)) {
                        double distSq = Math.pow(p.getX() - pos.getX(), 2) + Math.pow(p.getY() - pos.getY(), 2) + Math.pow(p.getZ() - pos.getZ(), 2);
                        if (distSq < minDistance) {
                            minDistance = distSq;
                            closestSolid = p;
                        }
                    }
                }
            }
        }

        if (closestSolid != null) {
            BlockState mountainPredominant = getPredominantNaturalBlock(level, closestSolid);
            buildSupportLedge(worldGenLevel, blockInfoGlobal.pos(), closestSolid, mountainPredominant);
            return new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), mountainPredominant, blockInfoGlobal.nbt());
        }
        
        BlockState fallbackPredominant = getPredominantNaturalBlock(level, pos);
        return new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), fallbackPredominant, blockInfoGlobal.nbt());
    }

    private BlockState getPredominantNaturalBlock(LevelReader level, BlockPos center) {
        Map<BlockState, Integer> counts = new HashMap<>();
        for (int x = -3; x <= 3; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -3; z <= 3; z++) {
                    BlockPos p = center.offset(x, y, z);
                    if (!isSafe(level, p)) continue;
                    BlockState s = level.getBlockState(p);
                    if (isNaturalBlock(s)) {
                        if (s.getBlock() == Blocks.GRASS_BLOCK) {
                            // Convert grass to dirt if it's underground, but since we want the surface block, keep grass if exposed?
                            // For simplicity, let's keep the block exactly as is to adapt perfectly to the biome (e.g. sand, podzol)
                        }
                        counts.put(s, counts.getOrDefault(s, 0) + 1);
                    }
                }
            }
        }

        BlockState predominant = Blocks.DIRT.defaultBlockState();
        int max = 0;
        for (Map.Entry<BlockState, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                predominant = entry.getKey();
            }
        }
        return predominant;
    }

    private boolean isNaturalBlock(BlockState state) {
        if (state.hasBlockEntity()) return false;
        String name = state.getBlock().getDescriptionId();
        return name.endsWith(".stone") || name.endsWith(".dirt") || name.endsWith(".grass_block") || name.endsWith(".sand") 
            || name.endsWith(".red_sand") || name.endsWith(".sandstone") || name.endsWith(".red_sandstone") 
            || name.endsWith(".terracotta") || name.contains("_terracotta") || name.endsWith(".deepslate") 
            || name.endsWith(".tuff") || name.endsWith(".diorite") || name.endsWith(".andesite") || name.endsWith(".granite") 
            || name.endsWith(".netherrack") || name.endsWith(".end_stone") || name.endsWith(".basalt") || name.endsWith(".blackstone")
            || name.endsWith(".gravel") || name.endsWith(".coarse_dirt") || name.endsWith(".podzol") || name.endsWith(".mycelium")
            || name.endsWith(".snow_block") || name.endsWith(".ice") || name.endsWith(".packed_ice") || name.endsWith(".blue_ice");
    }

    private void buildSupportLedge(WorldGenLevel level, BlockPos start, BlockPos end, BlockState state) {
        double dist = Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getZ() - start.getZ(), 2));
        int steps = (int) Math.ceil(dist);
        if (steps <= 0) return;
        
        Random random = new Random(start.asLong());
        
        for (int i = 1; i <= steps; i++) {
            double t = (double) i / steps; // 0 = structure, 1 = mountain
            int x = (int) Math.round(start.getX() + t * (end.getX() - start.getX()));
            int y = (int) Math.round(start.getY() + t * (end.getY() - start.getY()));
            int z = (int) Math.round(start.getZ() + t * (end.getZ() - start.getZ()));
            
            int noiseX = (int) (Math.sin(t * Math.PI * 4 + start.getX()) * 1.5 * (1.0 - t));
            int noiseZ = (int) (Math.cos(t * Math.PI * 4 + start.getZ()) * 1.5 * (1.0 - t));
            
            BlockPos centerP = new BlockPos(x + noiseX, y, z + noiseZ);
            placeIfEmpty(level, centerP, state);
            
            // Triangle wedge: thick at the mountain (t=1), thin at the structure (t=0)
            int thickness = (int) (4 * t + random.nextInt(2)); 
            if (thickness > 0) {
                for (int tx = -thickness; tx <= thickness; tx++) {
                    for (int tz = -thickness; tz <= thickness; tz++) {
                        if (tx*tx + tz*tz <= (thickness*thickness) + 0.5) {
                            if (random.nextFloat() < 0.7f) {
                                placeIfEmpty(level, centerP.offset(tx, 0, tz), state);
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeIfEmpty(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (!isSafe(level, pos)) return;
        if (level.isEmptyBlock(pos) || level.getBlockState(pos).canBeReplaced()) {
            level.setBlock(pos, state, 3);
        }
    }

    private boolean isSafe(LevelReader level, BlockPos pos) {
        return level.hasChunk(pos.getX() >> 4, pos.getZ() >> 4);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CSOMain.TERRAIN_BLEND_PROCESSOR;
    }
}
