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
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SolidGroundMarkerProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<SolidGroundMarkerProcessor> CODEC = com.mojang.serialization.MapCodec.unit(SolidGroundMarkerProcessor::new);

    public SolidGroundMarkerProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlaceSettings settings) {
        if (!(level instanceof WorldGenLevel worldGenLevel)) {
            return null; // Fallback if not generating properly
        }

        BlockPos belowPos = pos.below();
        if (!isSafe(level, belowPos)) return null;
        BlockState stateBelow = level.getBlockState(belowPos);
        
        // If the block below is already solid, just become a fitting solid block
        // This also enables "collusion": if a marker above already built a support here, we just anchor to it!
        if (stateBelow.isSolidRender(level, belowPos) || stateBelow.canOcclude()) {
            return new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), getPredominantNaturalBlock(level, belowPos), null);
        }

        // If it's floating, search for the nearest solid natural block in a larger radius
        int searchRadiusH = 6;
        int searchRadiusV = 18; // As requested, up to 18 blocks
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
            BlockState predominant = getPredominantNaturalBlock(level, closestSolid);
            buildSupportLedge(worldGenLevel, blockInfoGlobal.pos(), closestSolid, predominant);
            return new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), predominant, null);
        }
        
        return null; // Too high up or no mountain nearby, do not spawn anything
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
                        // Normalize grass block to dirt for the support structure to look better
                        if (s.getBlock().getDescriptionId().endsWith(".grass_block")) {
                            s = net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState();
                        }
                        counts.put(s, counts.getOrDefault(s, 0) + 1);
                    }
                }
            }
        }

        BlockState predominant = net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState();
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
            double t = (double) i / steps;
            int x = (int) Math.round(start.getX() + t * (end.getX() - start.getX()));
            int y = (int) Math.round(start.getY() + t * (end.getY() - start.getY()));
            int z = (int) Math.round(start.getZ() + t * (end.getZ() - start.getZ()));
            
            // Add slight wobble, tapering off near the end
            int noiseX = (int) (Math.sin(t * Math.PI * 4 + start.getX()) * 1.5 * (1.0 - t));
            int noiseZ = (int) (Math.cos(t * Math.PI * 4 + start.getZ()) * 1.5 * (1.0 - t));
            
            BlockPos centerP = new BlockPos(x + noiseX, y, z + noiseZ);
            placeIfEmpty(level, centerP, state);
            
            // Thicken to make it look robust and natural
            // Thicker at the top (near structure), thinner at the bottom
            int thickness = (int) (3 * (1.0 - t) + random.nextInt(2));
            if (thickness > 0) {
                for (int tx = -thickness/2; tx <= thickness/2; tx++) {
                    for (int tz = -thickness/2; tz <= thickness/2; tz++) {
                        if (tx*tx + tz*tz <= (thickness*thickness)/4.0 + 0.5) {
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
        return net.denmoth.cso.CSOMain.SOLID_GROUND_MARKER;
    }
}
