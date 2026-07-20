package net.denmoth.cso.compat;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.denmoth.cso.config.CSOConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfigIntegration {

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("cso.config.title"));

        builder.setSavingRunnable(() -> {
            CSOConfig.save();
        });

        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("cso.config.category.general"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("cso.config.enable_all_structures"), CSOConfig.INSTANCE.enableAllStructures)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.enableAllStructures = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("cso.config.enable_windmills"), CSOConfig.INSTANCE.enableWindmills)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.enableWindmills = newValue)
                .build());
                
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("cso.config.enable_spooky_houses"), CSOConfig.INSTANCE.enableSpookyHouses)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.enableSpookyHouses = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("cso.config.enable_industrial"), CSOConfig.INSTANCE.enableIndustrial)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.enableIndustrial = newValue)
                .build());
                
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("cso.config.inject_into_villages"), CSOConfig.INSTANCE.injectIntoVillages)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.injectIntoVillages = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Component.translatable("cso.config.default_spacing"), CSOConfig.INSTANCE.defaultSpacing)
                .setDefaultValue(30)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.defaultSpacing = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Component.translatable("cso.config.default_separation"), CSOConfig.INSTANCE.defaultSeparation)
                .setDefaultValue(6)
                .setSaveConsumer(newValue -> CSOConfig.INSTANCE.defaultSeparation = newValue)
                .build());

        return builder.build();
    }
}
