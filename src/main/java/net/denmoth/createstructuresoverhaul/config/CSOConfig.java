package net.denmoth.createstructuresoverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CSOConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> OVERWORLD_SPACING;
    public static final ForgeConfigSpec.ConfigValue<Integer> OVERWORLD_SEPARATION;

    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_SPACING;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_SEPARATION;

    static {
        BUILDER.comment("Create: Structures Overhaul Configuration").push("common");

        BUILDER.comment("Overworld Structures Settings (Windmills, Houses, Towers, etc.)");
        OVERWORLD_SPACING = BUILDER.comment("Average distance between structures (chunks). Default: 30")
                .define("overworld_spacing", 30);
        OVERWORLD_SEPARATION = BUILDER.comment("Minimum distance between structures (chunks). Default: 6")
                .define("overworld_separation", 6);

        BUILDER.comment("Nether Structures Settings");
        NETHER_SPACING = BUILDER.comment("Average distance between nether structures (chunks). Default: 30")
                .define("nether_spacing", 30);
        NETHER_SEPARATION = BUILDER.comment("Minimum distance (chunks). Default: 6")
                .define("nether_separation", 6);

        BUILDER.pop();
        COMMON_SPEC = BUILDER.build();
    }
}