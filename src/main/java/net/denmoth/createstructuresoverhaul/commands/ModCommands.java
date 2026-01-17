package net.denmoth.createstructuresoverhaul.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.datagen.ModStructures;
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

import java.util.ArrayList;
import java.util.List;
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

        source.sendSuccess(() -> Component.literal("§6[CSO] Scanning for structures (Radius 100 chunks)..."), false);

        ResourceKey<Structure>[] structures = new ResourceKey[]{
                ModStructures.OAK_WINDMILL, ModStructures.BIRCH_WINDMILL, ModStructures.SPRUCE_WINDMILL,
                ModStructures.ACACIA_WINDMILL, ModStructures.CHERRY_WINDMILL,
                ModStructures.TOWER, ModStructures.GRAVEYARD,
                ModStructures.DARK_SPOOKY_HOUSE, ModStructures.SPOOKY_HOUSE_1, ModStructures.SPOOKY_HOUSE_2,
                ModStructures.NETHER_FORPOST, ModStructures.WARPED_GREENHOUSE,
                ModStructures.CAMP, ModStructures.MINER_HOUSE, ModStructures.LIGHTNING_ROD_TOWER,
                ModStructures.COPPER_GREENHOUSE, ModStructures.COPPER_STORAGE
        };

        for (ResourceKey<Structure> key : structures) {
            try {
                Optional<Holder.Reference<Structure>> structHolder = level.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolder(key);

                if (structHolder.isPresent()) {
                    var pair = level.getChunkSource().getGenerator().findNearestMapStructure(level, HolderSet.direct(structHolder.get()), pos, 100, false);

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
                    } else {
                        source.sendSuccess(() -> Component.literal("§cMissing " + key.location().getPath()), false);
                    }
                }

            } catch (Exception e) {
                source.sendSuccess(() -> Component.literal("§4Error: " + e.getMessage()), false);
            }
        }
        return 1;
    }

    private static int testLoot(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();
        BlockPos startPos = BlockPos.containing(source.getPosition());

        String[] lootTables = {
                "oak_windmill", "birch_windmill", "spruce_windmill", "acacia_windmill", "cherry_windmill",
                "dark_spooky_house", "graveyard", "spooky_house1", "spooky_house2", "tower",
                "nether_forpost", "warped_greenhouse", "camp", "copper_greenhouse", "copper_storage",
                "lightning_tower", "miner_house"
        };

        int offset = 0;
        for (String table : lootTables) {
            BlockPos chestPos = startPos.offset(offset * 2, 0, 0);

            level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 3);

            if (level.getBlockEntity(chestPos) instanceof RandomizableContainerBlockEntity chest) {
                ResourceLocation lootTableLoc = new ResourceLocation(CreateStructuresOverhaulMod.MODID, table);
                chest.setLootTable(lootTableLoc, level.getRandom().nextLong());
                chest.setCustomName(Component.literal("§6Loot: " + table));
            }

            offset++;
        }

        int finalOffset = offset;
        source.sendSuccess(() -> Component.literal("§6Generated " + finalOffset + " named chests. Hover over them to see the table."), false);
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