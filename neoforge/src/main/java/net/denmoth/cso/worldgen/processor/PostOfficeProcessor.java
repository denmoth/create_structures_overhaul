package net.denmoth.cso.worldgen.processor;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.denmoth.cso.CSOMain;

import org.jetbrains.annotations.Nullable;

public class PostOfficeProcessor extends StructureProcessor {
    public static final com.mojang.serialization.MapCodec<PostOfficeProcessor> CODEC = com.mojang.serialization.MapCodec.unit(PostOfficeProcessor::new);

    public PostOfficeProcessor() {}

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos offset, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfoIn, StructureTemplate.StructureBlockInfo blockInfoOut, StructurePlaceSettings settings) {
        if (blockInfoOut.nbt() != null && blockInfoOut.state().getBlock().getDescriptionId().contains("sign")) {
            CompoundTag nbt = blockInfoOut.nbt().copy();
            boolean modified = false;
            
            if (nbt.contains("front_text", Tag.TAG_COMPOUND)) {
                CompoundTag frontText = nbt.getCompound("front_text");
                if (frontText.contains("messages", Tag.TAG_LIST)) {
                    ListTag messages = frontText.getList("messages", Tag.TAG_STRING);
                    for (int i = 0; i < messages.size(); i++) {
                        String msg = messages.getString(i);
                        if (msg.contains("Post")) {
                            long structureSeed = settings.getRandom(BlockPos.ZERO).nextLong();
                            RandomSource rand = RandomSource.create(structureSeed);
                            int postNum = 1 + rand.nextInt(999);
                            String newMsg = "{\"text\":\"Post #" + postNum + "\"}";
                            messages.set(i, StringTag.valueOf(newMsg));
                            modified = true;
                            break;
                        }
                    }
                }
            }
            
            if (modified) {
                return new StructureTemplate.StructureBlockInfo(blockInfoOut.pos(), blockInfoOut.state(), nbt);
            }
        }
        return blockInfoOut;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CSOMain.POST_OFFICE_PROCESSOR;
    }
}
