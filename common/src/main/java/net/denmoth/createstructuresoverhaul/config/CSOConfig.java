package net.denmoth.createstructuresoverhaul.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CSOConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static CSOConfig INSTANCE = new CSOConfig();

    public int overworldSpacing = 30;
    public int overworldSeparation = 6;
    public int netherSpacing = 30;
    public int netherSeparation = 6;

    public static void load(File configDir) {
        File file = new File(configDir, "cso-common.json");
        try {
            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    INSTANCE = GSON.fromJson(reader, CSOConfig.class);
                }
            } else {
                file.getParentFile().mkdirs();
                try (FileWriter writer = new FileWriter(file)) {
                    GSON.toJson(INSTANCE, writer);
                }
            }
        } catch (Exception e) {
            CreateStructuresOverhaulMod.LOGGER.error("Failed to load CSO config", e);
        }
    }
}