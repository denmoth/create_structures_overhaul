package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CreateStructuresOverhaulMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ================= SERVER DATA =================

        ModWorldGenProvider worldGen = new ModWorldGenProvider(packOutput, lookupProvider);
        generator.addProvider(event.includeServer(), worldGen);

        generator.addProvider(event.includeServer(), new ModStructureTagsProvider(packOutput, worldGen.getRegistryProvider(), existingFileHelper));

        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

        // ИСПРАВЛЕНО ЗДЕСЬ: Просто вызываем твой класс напрямую
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(
                packOutput, lookupProvider, existingFileHelper
        ));

        // ================= CLIENT: LANGUAGES (OPTIMIZED LIST) =================

        // 1. English Family
        generator.addProvider(event.includeClient(), new ModLanguageProvider.EN(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.EN_GB(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.EN_AU(packOutput));

        // 2. Slavic & Baltic
        generator.addProvider(event.includeClient(), new ModLanguageProvider.RU(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.RU_PRE(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.RU_RY(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.UA(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.UA_GAL(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.PL(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.CZ(packOutput));

        // 3. Germanic & Nordic
        generator.addProvider(event.includeClient(), new ModLanguageProvider.DE(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.NL(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.SV(packOutput));

        // 4. Romance
        generator.addProvider(event.includeClient(), new ModLanguageProvider.FR(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.ES(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.ES_MX(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.PT(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.BR(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.IT(packOutput));

        // 5. Asian & Others
        generator.addProvider(event.includeClient(), new ModLanguageProvider.CN(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.TW(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.JP(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.KR(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.TR(packOutput));

        // 6. Fun
        generator.addProvider(event.includeClient(), new ModLanguageProvider.PIRATE(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.LOL(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.WS(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.UD(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.TLH(packOutput));
        generator.addProvider(event.includeClient(), new ModLanguageProvider.QYA(packOutput));
    }
}