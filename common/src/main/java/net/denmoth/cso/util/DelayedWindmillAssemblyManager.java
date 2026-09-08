package net.denmoth.cso.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DelayedWindmillAssemblyManager {
    private static class Entry {
        public final BlockPos pos;
        public int ticksLeft;
        public Entry(BlockPos pos, int ticksLeft) {
            this.pos = pos;
            this.ticksLeft = ticksLeft;
        }
    }

    private static final Map<ResourceKey<Level>, List<Entry>> queues = new ConcurrentHashMap<>();

    public static void add(ServerLevel level, BlockPos pos, int delay) {
        queues.computeIfAbsent(level.dimension(), k -> new LinkedList<>()).add(new Entry(pos, delay));
    }

    public static void tick(ServerLevel level) {
        List<Entry> list = queues.get(level.dimension());
        if (list == null || list.isEmpty()) return;

        Iterator<Entry> it = list.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            e.ticksLeft--;
            if (e.ticksLeft <= 0) {
                if (level.isLoaded(e.pos)) {
                    it.remove();
                    BlockEntity be = level.getBlockEntity(e.pos);
                    if (be != null && BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(be.getType()).toString().equals("create:windmill_bearing")) {
                        CompoundTag tag = be.saveWithFullMetadata(level.registryAccess());
                        tag.putBoolean("QueueAssembly", true);
                        be.loadWithComponents(tag, level.registryAccess());
                    }
                } else {
                    e.ticksLeft = 100; // Check again in 5 seconds
                }
            }
        }
    }
}
