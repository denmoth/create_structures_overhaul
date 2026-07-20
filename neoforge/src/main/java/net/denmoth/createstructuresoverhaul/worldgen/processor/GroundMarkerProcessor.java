package net.denmoth.createstructuresoverhaul.worldgen.processor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class GroundMarkerProcessor extends StructureProcessor {
    public static final MapCodec<GroundMarkerProcessor> CODEC = MapCodec.unit(GroundMarkerProcessor::new);

    public GroundMarkerProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlaceSettings settings) {
        // Обычный граунд маркер безусловно заменяет себя на землю, создавая фундамент для структуры
        return new StructureTemplate.StructureBlockInfo(pos, net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState(), null);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.createstructuresoverhaul.CSOMain.GROUND_MARKER.get();
    }
}
