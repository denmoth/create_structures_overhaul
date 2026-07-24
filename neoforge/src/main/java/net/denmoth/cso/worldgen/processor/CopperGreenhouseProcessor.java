package net.denmoth.cso.worldgen.processor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class CopperGreenhouseProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<CopperGreenhouseProcessor> CODEC = com.mojang.serialization.MapCodec.unit(CopperGreenhouseProcessor::new);
    public static final CopperGreenhouseProcessor INSTANCE = new CopperGreenhouseProcessor();

    public CopperGreenhouseProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        return blockInfoOut;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.cso.CSOMain.COPPER_GREENHOUSE_PROCESSOR;
    }
}
