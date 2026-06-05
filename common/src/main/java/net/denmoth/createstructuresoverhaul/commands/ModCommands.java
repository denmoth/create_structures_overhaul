package net.denmoth.createstructuresoverhaul.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Map;
import java.util.Optional;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(CreateStructuresOverhaulMod.MODID)
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("debug")
                        .then(Commands.literal("structures")
                                .executes(ModCommands::scanStructures))
                        .then(Commands.literal("loot")
                                .executes(ModCommands::testLoot))
                        .then(Commands.literal("biomes")
                                .then(Commands.literal("structure")
                                        .then(Commands.argument("id", ResourceLocationArgument.id())
                                                .suggests(SUGGEST_CSO_STRUCTURES)
                                                .executes(ModCommands::checkStructureBiomes)))
                                .then(Commands.literal("biome")
                                        .then(Commands.argument("id", ResourceLocationArgument.id())
                                                .suggests(SUGGEST_BIOMES)
                                                .executes(ModCommands::checkBiomeStructures)))
                        )
                )
        );
    }

    private static final SuggestionProvider<CommandSourceStack> SUGGEST_CSO_STRUCTURES = (context, builder) -> {
        return SharedSuggestionProvider.suggestResource(
                context.getSource().registryAccess().registryOrThrow(Registries.STRUCTURE).keySet().stream()
                        .filter(rl -> rl.getNamespace().equals(CreateStructuresOverhaulMod.MODID)),
                builder
        );
    };

    private static final SuggestionProvider<CommandSourceStack> SUGGEST_BIOMES = (context, builder) -> {
        return SharedSuggestionProvider.suggestResource(
                context.getSource().registryAccess().registryOrThrow(Registries.BIOME).keySet(),
                builder
        );
    };

    private static int scanStructures(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();
        BlockPos pos = BlockPos.containing(source.getPosition());

        source.sendSuccess(() -> Component.literal("§6[CSO] Scanning for structures (Radius 50 chunks)..."), false);

        var registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);

        registry.entrySet().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(CreateStructuresOverhaulMod.MODID))
                .forEach(entry -> {
                    ResourceKey<Structure> key = entry.getKey();
                    Structure structure = entry.getValue();
                    try {
                        var pair = level.getChunkSource().getGenerator().findNearestMapStructure(level, HolderSet.direct(Holder.direct(structure)), pos, 50, false);
                        if (pair != null) {
                            BlockPos p = pair.getFirst();
                            double dist = Math.sqrt(p.distSqr(pos));
                            String cmd = "/tp @s " + p.getX() + " " + p.getY() + " " + p.getZ();
                            Component coords = Component.literal("§e[" + p.getX() + ", " + p.getZ() + "]")
                                    .withStyle(Style.EMPTY
                                            .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, cmd))
                                            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Click to suggest TP command")))
                                            .withUnderlined(true));
                            source.sendSuccess(() -> Component.literal("§aFound " + key.location().getPath() + " (" + (int)dist + "m): ").append(coords), false);
                        }
                    } catch (Exception e) {
                        source.sendSuccess(() -> Component.literal("§4Error scanning " + key.location() + ": " + e.getMessage()), false);
                    }
                });

        return 1;
    }

    private static int testLoot(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();
        BlockPos startPos = BlockPos.containing(source.getPosition());

        var structureRegistry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        int offset = 0;

        for (Map.Entry<ResourceKey<Structure>, Structure> entry : structureRegistry.entrySet()) {
            if (!entry.getKey().location().getNamespace().equals(CreateStructuresOverhaulMod.MODID)) continue;

            String path = entry.getKey().location().getPath();
            BlockPos chestPos = startPos.offset(offset * 2, 0, 0);

            if (level.getBlockState(chestPos).isAir()) {
                level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 3);

                if (level.getBlockEntity(chestPos) instanceof RandomizableContainerBlockEntity chest) {
                    ResourceLocation lootTableLoc = new ResourceLocation(CreateStructuresOverhaulMod.MODID, path);
                    chest.setLootTable(lootTableLoc, level.getRandom().nextLong());
                    chest.setCustomName(Component.literal("§6Loot: " + path));
                    offset++;
                }
            }
        }

        int finalOffset = offset;
        source.sendSuccess(() -> Component.literal("§6Generated " + finalOffset + " chests for known structures."), false);
        return 1;
    }

    private static int checkStructureBiomes(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ResourceLocation id = ResourceLocationArgument.getId(context, "id");
        ServerLevel level = context.getSource().getLevel();
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);

        if (!registry.containsKey(id)) {
            context.getSource().sendFailure(Component.literal("Structure not found: " + id));
            return 0;
        }

        Structure structure = registry.get(id);
        HolderSet<Biome> biomes = structure.biomes();

        context.getSource().sendSuccess(() -> Component.literal("§6Biomes for " + id + ":"), false);
        biomes.stream().forEach(holder -> {
            ResourceKey<Biome> key = holder.unwrapKey().orElse(null);
            if (key != null) {
                context.getSource().sendSuccess(() -> Component.literal(" - §a" + key.location()), false);
            }
        });
        return 1;
    }

    private static int checkBiomeStructures(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ResourceLocation biomeId = ResourceLocationArgument.getId(context, "id");
        ServerLevel level = context.getSource().getLevel();
        Registry<Biome> biomeRegistry = level.registryAccess().registryOrThrow(Registries.BIOME);
        Registry<Structure> structureRegistry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);

        if (!biomeRegistry.containsKey(biomeId)) {
            context.getSource().sendFailure(Component.literal("Biome not found: " + biomeId));
            return 0;
        }

        Holder<Biome> biomeHolder = biomeRegistry.getHolder(ResourceKey.create(Registries.BIOME, biomeId)).orElseThrow();
        context.getSource().sendSuccess(() -> Component.literal("§6CSO Structures valid in " + biomeId + ":"), false);

        structureRegistry.entrySet().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(CreateStructuresOverhaulMod.MODID))
                .forEach(entry -> {
                    if (entry.getValue().biomes().contains(biomeHolder)) {
                        context.getSource().sendSuccess(() -> Component.literal(" - §a" + entry.getKey().location()), false);
                    }
                });
        return 1;
    }
}