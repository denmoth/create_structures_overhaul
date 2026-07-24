package net.denmoth.cso.worldgen.processor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class GroundMarkerProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<GroundMarkerProcessor> CODEC = com.mojang.serialization.MapCodec.unit(GroundMarkerProcessor::new);
    public static final GroundMarkerProcessor INSTANCE = new GroundMarkerProcessor();

    public GroundMarkerProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        return null;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.cso.CSOMain.GROUND_MARKER;
    }
}
