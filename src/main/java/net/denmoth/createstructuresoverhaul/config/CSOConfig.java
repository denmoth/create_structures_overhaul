package net.denmoth.createstructuresoverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CSOConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    // Windmills
    public static final ForgeConfigSpec.ConfigValue<Integer> WINDMILLS_SPACING;
    public static final ForgeConfigSpec.ConfigValue<Integer> WINDMILLS_SEPARATION;

    // Spooky & Industrial
    public static final ForgeConfigSpec.ConfigValue<Integer> SPOOKY_SPACING;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPOOKY_SEPARATION;

    // Nether
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_SPACING;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_SEPARATION;

    static {
        BUILDER.comment("Create: Structures Overhaul Configuration").push("common");

        BUILDER.comment("Windmills Generation Settings");
        WINDMILLS_SPACING = BUILDER.comment("Average distance between windmills (chunks). Default: 34")
                .define("windmills_spacing", 34);
        WINDMILLS_SEPARATION = BUILDER.comment("Minimum distance between windmills (chunks). Must be smaller than spacing. Default: 12")
                .define("windmills_separation", 12);

        BUILDER.comment("Spooky & Industrial Structures Generation Settings");
        SPOOKY_SPACING = BUILDER.comment("Average distance between spooky/industrial structures (chunks). Default: 50")
                .define("spooky_spacing", 50);
        SPOOKY_SEPARATION = BUILDER.comment("Minimum distance (chunks). Default: 20")
                .define("spooky_separation", 20);

        BUILDER.comment("Nether Structures Generation Settings");
        NETHER_SPACING = BUILDER.comment("Average distance between nether structures (chunks). Default: 25")
                .define("nether_spacing", 25);
        NETHER_SEPARATION = BUILDER.comment("Minimum distance (chunks). Default: 10")
                .define("nether_separation", 10);

        BUILDER.pop();
        COMMON_SPEC = BUILDER.build();
    }
}