package net.denmoth.cso.worldgen.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

/**
 * Custom processor from MothLib that targets create:windmill_bearing directly
 * and applies QueueAssembly NBT flag.
 */
public class WindmillBearingProcessor extends StructureProcessor {
    public static final Codec<WindmillBearingProcessor> CODEC = Codec.unit(WindmillBearingProcessor::new);
    public static final WindmillBearingProcessor INSTANCE = new WindmillBearingProcessor();

    private WindmillBearingProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoLocal, StructureTemplate.StructureBlockInfo blockInfoGlobal, StructurePlaceSettings settings) {
        if (BuiltInRegistries.BLOCK.getKey(blockInfoGlobal.state().getBlock()).toString().equals("create:windmill_bearing")) {
            CompoundTag nbt = blockInfoGlobal.nbt();
            if (nbt == null) {
                nbt = new CompoundTag();
            } else {
                nbt = nbt.copy();
            }
            nbt.putString("id", "create:windmill_bearing");
            // Create expects boolean or byte, byte 1b is the most compatible standard for QueueAssembly
            nbt.putBoolean("QueueAssembly", true);
            return new StructureTemplate.StructureBlockInfo(pos, blockInfoGlobal.state(), nbt);
        }
        return blockInfoGlobal;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.cso.CSOMain.WINDMILL_BEARING;
    }
}
