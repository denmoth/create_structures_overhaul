package net.denmoth.createstructuresoverhaul.datagen;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider {

    public static class EN extends LanguageProvider {
        public EN(PackOutput output) { super(output, CreateStructuresOverhaulMod.MODID, "en_us"); }
        @Override
        protected void addTranslations() {
            add("itemGroup.create_structures_overhaul", "Create: Structures Overhaul");
            add("advancements.cso.root.title", "The Age of Excavation");
            add("advancements.cso.root.desc", "Welcome to the world of industrial ruins.");

            add("advancements.cso.windmills.title", "Don Quixote");
            add("advancements.cso.windmills.desc", "Find any abandoned windmill.");

            add("advancements.cso.spooky.title", "Ghost Story");
            add("advancements.cso.spooky.desc", "Discover a haunted location.");

            add("advancements.cso.industrial.title", "Rusty Ruins");
            add("advancements.cso.industrial.desc", "Find an industrial remnant.");

            add("advancements.cso.trading.title", "Cartography");
            add("advancements.cso.trading.desc", "Buy a structure map from a Cartographer.");

            add("advancements.cso.nether.title", "Hellish Expansion");
            add("advancements.cso.nether.desc", "Find a structure in the Nether.");

            add("advancements.cso.invasion.title", "Dimensional Breach");
            add("advancements.cso.invasion.desc", "Find a Nether structure in the Overworld.");

            add("advancements.cso.all.title", "The Grand Tour");
            add("advancements.cso.all.desc", "Visit every single structure added by the mod.");

            add("item.cso.haunted_map", "Haunted Explorer Map");
            add("item.cso.nether_map", "Nether Explorer Map");
        }
    }

    public static class RU extends LanguageProvider {
        public RU(PackOutput output) { super(output, CreateStructuresOverhaulMod.MODID, "ru_ru"); }
        @Override
        protected void addTranslations() {
            add("itemGroup.create_structures_overhaul", "Create: Structures Overhaul");
            add("advancements.cso.root.title", "Эпоха Раскопок");
            add("advancements.cso.root.desc", "Добро пожаловать в мир индустриальных руин.");

            add("advancements.cso.windmills.title", "Дон Кихот");
            add("advancements.cso.windmills.desc", "Найдите любую заброшенную мельницу.");

            add("advancements.cso.spooky.title", "Страшилка");
            add("advancements.cso.spooky.desc", "Обнаружьте проклятое место.");

            add("advancements.cso.industrial.title", "Ржавые руины");
            add("advancements.cso.industrial.desc", "Найдите индустриальные останки.");

            add("advancements.cso.trading.title", "Картография");
            add("advancements.cso.trading.desc", "Купите карту структуры у картографа.");

            add("advancements.cso.nether.title", "Адская Экспансия");
            add("advancements.cso.nether.desc", "Найдите структуру в Аду.");

            add("advancements.cso.invasion.title", "Пространственный разлом");
            add("advancements.cso.invasion.desc", "Найдите адскую структуру в Верхнем мире.");

            add("advancements.cso.all.title", "Большое Путешествие");
            add("advancements.cso.all.desc", "Посетите абсолютно каждую структуру из мода.");

            add("item.cso.haunted_map", "Карта проклятых земель");
            add("item.cso.nether_map", "Карта адских аванпостов");
        }
    }

    public static class UA extends LanguageProvider {
        public UA(PackOutput output) { super(output, CreateStructuresOverhaulMod.MODID, "uk_ua"); }
        @Override
        protected void addTranslations() {
            add("itemGroup.create_structures_overhaul", "Create: Structures Overhaul");
            add("advancements.cso.root.title", "Епоха Розкопок");
            add("advancements.cso.root.desc", "Ласкаво просимо у світ індустріальних руїн.");

            add("advancements.cso.windmills.title", "Дон Кіхот");
            add("advancements.cso.windmills.desc", "Знайдіть будь-який покинутий млин.");

            add("advancements.cso.spooky.title", "Страшилка");
            add("advancements.cso.spooky.desc", "Знайдіть прокляте місце.");

            add("advancements.cso.industrial.title", "Іржаві руїни");
            add("advancements.cso.industrial.desc", "Знайдіть індустріальні залишки.");

            add("advancements.cso.trading.title", "Картографія");
            add("advancements.cso.trading.desc", "Купіть карту структури у картографа.");

            add("advancements.cso.nether.title", "Пекельна Експансія");
            add("advancements.cso.nether.desc", "Знайдіть структуру в Пеклі.");

            add("advancements.cso.invasion.title", "Просторовий розлом");
            add("advancements.cso.invasion.desc", "Знайдіть пекельну структуру у Верхньому світі.");

            add("advancements.cso.all.title", "Велика Подорож");
            add("advancements.cso.all.desc", "Відвідайте абсолютно кожну структуру з моду.");

            add("item.cso.haunted_map", "Мапа проклятих земель");
            add("item.cso.nether_map", "Мапа пекельних аванпостів");
        }
    }
}