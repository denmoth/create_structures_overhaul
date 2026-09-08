package net.denmoth.cso.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSOConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("CSO Config");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File CONFIG_FILE = new File("config", "cso.json");

    public static ConfigData INSTANCE = new ConfigData();
    public static boolean hasError = false;

    public static class ConfigData {
        public boolean enableAllStructures = true;
        
        public boolean enableWindmills = true;
        public boolean enableSpookyHouses = true;
        public boolean enableIndustrial = true;
        public boolean enableMiscellaneous = true;

        public boolean injectIntoVillages = true;
        public boolean allowWatermillTerraforming = false;
        
        public float customLootMultiplier = 1.0f;
        public boolean enableModCompat = false; // Experimental
        
        // Spacing and separation are advanced, we will load them here
        public int defaultSpacing = 30;
        public int defaultSeparation = 6;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ConfigData.class);
            } catch (Exception e) {
                LOGGER.error("Failed to parse CSO Config! Using fallback default values.", e);
                hasError = true;
                INSTANCE = new ConfigData();
            }
        } else {
            save();
        }
    }

    public static void save() {
        CONFIG_FILE.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save CSO Config!", e);
        }
    }
}
