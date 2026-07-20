package net.denmoth.createstructuresoverhaul.worldgen.processor;

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

    @Nullable
    @Override
    public StructureTemplate.StructureEntityInfo processEntity(LevelReader level, BlockPos offset, StructureTemplate.StructureEntityInfo entityInfoLocal, StructureTemplate.StructureEntityInfo entityInfoGlobal, StructurePlaceSettings settings, StructureTemplate template) {
        if (entityInfoGlobal.nbt != null && "create:super_glue".equals(entityInfoGlobal.nbt.getString("id"))) {
            CompoundTag nbt = entityInfoGlobal.nbt.copy();

            if (nbt.contains("From") && nbt.contains("To")) {
                net.minecraft.nbt.ListTag fromTag = nbt.getList("From", 6); // 6 is DoubleTag
                net.minecraft.nbt.ListTag toTag = nbt.getList("To", 6);

                if (fromTag.size() == 3 && toTag.size() == 3) {
                    double fx = fromTag.getDouble(0);
                    double fy = fromTag.getDouble(1);
                    double fz = fromTag.getDouble(2);

                    double tx = toTag.getDouble(0);
                    double ty = toTag.getDouble(1);
                    double tz = toTag.getDouble(2);

                    double nfx = fx, nfz = fz;
                    double ntx = tx, ntz = tz;

                    switch (settings.getRotation()) {
                        case CLOCKWISE_90:
                            nfx = -fz; nfz = fx;
                            ntx = -tz; ntz = tx;
                            break;
                        case CLOCKWISE_180:
                            nfx = -fx; nfz = -fz;
                            ntx = -tx; ntz = -tz;
                            break;
                        case COUNTERCLOCKWISE_90:
                            nfx = fz; nfz = -fx;
                            ntx = tz; ntz = -tx;
                            break;
                        case NONE:
                        default:
                            break;
                    }

                    net.minecraft.nbt.ListTag newFrom = new net.minecraft.nbt.ListTag();
                    newFrom.add(net.minecraft.nbt.DoubleTag.valueOf(Math.min(nfx, ntx)));
                    newFrom.add(net.minecraft.nbt.DoubleTag.valueOf(Math.min(fy, ty)));
                    newFrom.add(net.minecraft.nbt.DoubleTag.valueOf(Math.min(nfz, ntz)));

                    net.minecraft.nbt.ListTag newTo = new net.minecraft.nbt.ListTag();
                    newTo.add(net.minecraft.nbt.DoubleTag.valueOf(Math.max(nfx, ntx)));
                    newTo.add(net.minecraft.nbt.DoubleTag.valueOf(Math.max(fy, ty)));
                    newTo.add(net.minecraft.nbt.DoubleTag.valueOf(Math.max(nfz, ntz)));

                    nbt.put("From", newFrom);
                    nbt.put("To", newTo);
                }
            }
            return new StructureTemplate.StructureEntityInfo(entityInfoGlobal.pos, entityInfoGlobal.blockPos, nbt);
        }
        return entityInfoGlobal;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.denmoth.createstructuresoverhaul.CSOMain.WINDMILL_BEARING.get();
    }
}
