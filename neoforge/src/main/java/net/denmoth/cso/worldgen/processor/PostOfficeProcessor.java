package net.denmoth.cso.worldgen.processor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class PostOfficeProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<PostOfficeProcessor> CODEC = com.mojang.serialization.MapCodec.unit(PostOfficeProcessor::new);
    public static final PostOfficeProcessor INSTANCE = new PostOfficeProcessor();

    public PostOfficeProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        return blockInfoOut;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.cso.CSOMain.POST_OFFICE_PROCESSOR;
    }
}
