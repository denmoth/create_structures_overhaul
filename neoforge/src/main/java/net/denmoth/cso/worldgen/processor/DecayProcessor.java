package net.denmoth.cso.worldgen.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.denmoth.cso.CSOMain;

import org.jetbrains.annotations.Nullable;

public class DecayProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<DecayProcessor> CODEC = com.mojang.serialization.MapCodec.unit(DecayProcessor::new);
    public static final DecayProcessor INSTANCE = new DecayProcessor();

    private DecayProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        RandomSource random = settings.getRandom(blockInfoOut.pos());

        if (blockInfoOut.state().is(Blocks.STONE_BRICKS)) {
            float f = random.nextFloat();
            if (f < 0.2f) {
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), blockInfoOut.nbt());
            } else if (f < 0.4f) {
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), blockInfoOut.nbt());
            }
        } else if (blockInfoOut.state().is(Blocks.COBBLESTONE)) {
            if (random.nextFloat() < 0.3f) {
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), Blocks.MOSSY_COBBLESTONE.defaultBlockState(), blockInfoOut.nbt());
            }
        }

        return blockInfoOut;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CSOMain.DECAY_PROCESSOR;
    }
}
