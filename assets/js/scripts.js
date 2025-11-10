/*
 * scripts.js – Handles theme switching, language selection, sidebar toggle,
 * search functionality and translation for the Create: Structures Overhaul
 * wiki. This file defines a translations object containing all the
 * internationalised strings for six languages (English, Russian,
 * Ukrainian, German, French and Spanish). It registers event
 * listeners for UI controls (theme toggle, language select, search)
 * and updates the DOM accordingly.  The search bar supports simple
 * client‑side search using a small in‑memory index that updates
 * whenever the language is changed.
 */

document.addEventListener('DOMContentLoaded', () => {
  /* ------------------------------------------------------------------
   * Translation definitions
   * Each language entry contains keys for all of the text used in the
   * pages. When adding new text to the HTML, ensure you provide a
   * corresponding key here so that the page updates correctly when
   * changing languages.  Fallbacks default to English if a key is
   * missing.
   */
  const translations = {
    en: {
      site_title: "Create: Structures Overhaul — Wiki",
      search_placeholder: "Search documentation…",
      nav_home: "Home",
      nav_installation: "Installation",
      nav_structures: "Structures",
      nav_addons: "Addons & Integrations",
      nav_changelog: "Changelog",
      nav_faq: "FAQ",
      nav_credits: "Credits",
      nav_about: "About",
      hero_title: "Create: Structures Overhaul — Wiki",
      hero_subtitle: "Expand Create with stunning world structures.",
      hero_badge: "World Generation Add-on for Create",
      hero_get_started: "Get Started",
      hero_view_structures: "Browse Structures",
      hero_github: "GitHub",
      hero_download: "Download Mod",
      hero_version: "Latest release: 1.5.0 • Minecraft 1.20.1",
      hero_license: "Open Source • MIT License",
      features_title: "Features Overview",
      feature_dynamic_title: "Dynamic Generation",
      feature_dynamic_desc: "Structures appear seamlessly in world generation.",
      feature_biome_title: "Biome Integration",
      feature_biome_desc: "Adapted to different environments and biomes.",
      feature_addon_title: "Addon Support",
      feature_addon_desc: "Extensible and modular design with addons.",
      quickstart_title: "Quick start",
      quickstart_subtitle: "A fast overview for players and pack makers who want to jump right into the add-on.",
      quickstart_step_install_title: "Install the dependencies",
      quickstart_step_install_desc: "Grab Create, Flywheel and Kotlin for Forge/NeoForge, then drop the Structures Overhaul jar into /mods.",
      quickstart_step_install_link: "Open installation guide",
      quickstart_step_config_title: "Tune world generation",
      quickstart_step_config_desc: "Use the supplied datapacks or tweak the JSON config to control spawn weights, biome tags and loot tables.",
      quickstart_step_config_link: "See configuration options",
      quickstart_step_explore_title: "Explore and expand",
      quickstart_step_explore_desc: "Discover hand-crafted facilities across the overworld, then extend them with community-made structure packs.",
      quickstart_step_explore_link: "Discover addons",
      spotlight_title: "Structure spotlights",
      spotlight_subtitle: "Signature builds that showcase how Create automation meets exploration.",
      spotlight_skyport_title: "Skyport Docks",
      spotlight_skyport_desc: "Floating gantries loaded with mechanical arms, perfect for introducing players to deployers and rope pulleys.",
      spotlight_skyport_feature1: "Appears above temperate oceans between Y120–Y150.",
      spotlight_skyport_feature2: "Loot includes early Create components and kinetic templates.",
      spotlight_foundry_title: "Ashen Foundry",
      spotlight_foundry_desc: "A basalt-forged refinery nestled in volcanic biomes with built-in sequenced assembly examples.",
      spotlight_foundry_feature1: "Generates in hot-overworld biome tags with adjustable rarity.",
      spotlight_foundry_feature2: "Features blaze-powered smelteries and deployable automation chains.",
      spotlight_observatory_title: "Aurora Observatory",
      spotlight_observatory_desc: "A snowy research outpost showcasing rotational power routing and contraption logistics.",
      spotlight_observatory_feature1: "Rare spawn in snowy slopes and grove biomes.",
      spotlight_observatory_feature2: "Hidden journal pages unlock lore-friendly advancement chain.",
      updates_title: "Latest updates",
      updates_subtitle: "Track the development focus and plan your server upgrades accordingly.",
      update_entry_1_date: "March 2025",
      update_entry_1_title: "1.5.0 — Automation Pass",
      update_entry_1_desc: "Reworked loot tables to include new kinetic starters, added multilingual tooltips and optimized structure palette swaps.",
      update_entry_2_date: "January 2025",
      update_entry_2_title: "1.4.2 — Performance Hotfix",
      update_entry_2_desc: "Improved chunk batching for large schematics and resolved crashes with experimental Flywheel builds.",
      update_entry_3_date: "November 2024",
      update_entry_3_title: "1.4.0 — Biome Diversity",
      update_entry_3_desc: "Introduced region-specific loot variants, added 12 new structure palettes and refreshed datapack templates.",
      docs_card_title: "Documentation hub",
      docs_card_desc: "Reference pages break down installation, structure behaviour, datapack hooks and integration APIs.",
      docs_card_link_install: "Installation checklist",
      docs_card_link_structures: "Structure catalogue",
      docs_card_link_addons: "Addon & integration index",
      community_card_title: "Community resources",
      community_card_desc: "Join the conversation, share schematics and help shape the roadmap.",
      community_card_link_discord: "Discord server",
      community_card_link_github: "GitHub issues & contributions",
      community_card_link_modrinth: "Modrinth page",
      roadmap_card_title: "Roadmap highlights",
      roadmap_card_desc: "Upcoming work includes narrative quests, Nether expansion packs and configurable contraption exports.",
      roadmap_card_callout: "Have an idea? Drop suggestions in the Discord feedback channel.",
      installation_title: "Installation",
      installation_desc: "Follow these steps to install the mod.",
      structures_title: "Structures",
      structures_desc: "Overview of all structures and their attributes.",
      addons_title: "Addons & Integrations",
      addons_desc: "Addons to expand the mod's capabilities.",
      changelog_title: "Changelog",
      changelog_desc: "Quality placeholder: semantic versioning notes, recent updates, bugfix highlights.",
      faq_title: "Frequently Asked Questions",
      faq_desc: "Common questions and answers.",
      credits_title: "Credits",
      credits_desc: "Acknowledgements to contributors.",
      about_title: "About",
      about_desc: "Learn more about the project.",
      credits_creator: "Creator",
      credits_builder: "Builder",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "How do I install the mod?",
      faq_a1: "Download the mod jar, install Create, and place the jar into your /mods folder.",
      faq_q2: "Does it work with Biomes O’ Plenty?",
      faq_a2: "Yes, install the BOP Integration addon to add structures in BOP biomes.",
      faq_q3: "Is it compatible with modpacks?",
      faq_a3: "It should work with most modpacks built on Forge/NeoForge; check for version compatibility.",
      about_text: "Create: Structures Overhaul is a world generation addon for the Create mod. It enriches exploration by adding thematic structures to your world. It is open source and welcomes contributions.",
    },
    ru: {
      site_title: "Create: Structures Overhaul — Вики",
      search_placeholder: "Поиск в документации…",
      nav_home: "Главная",
      nav_installation: "Установка",
      nav_structures: "Структуры",
      nav_addons: "Аддоны и интеграции",
      nav_changelog: "Список изменений",
      nav_faq: "Частые вопросы",
      nav_credits: "Авторы",
      nav_about: "О проекте",
      hero_title: "Create: Structures Overhaul — Вики",
      hero_subtitle: "Расширьте Create потрясающими структурами.",
      hero_badge: "Аддон генерации мира для Create",
      hero_get_started: "Начать",
      hero_view_structures: "Посмотреть структуры",
      hero_github: "GitHub",
      hero_download: "Скачать мод",
      hero_version: "Последний релиз: 1.5.0 • Minecraft 1.20.1",
      hero_license: "Открытый код • Лицензия MIT",
      features_title: "Обзор возможностей",
      feature_dynamic_title: "Динамическая генерация",
      feature_dynamic_desc: "Структуры плавно появляются при генерации мира.",
      feature_biome_title: "Интеграция с биомами",
      feature_biome_desc: "Адаптировано под разные биомы и окружения.",
      feature_addon_title: "Поддержка аддонов",
      feature_addon_desc: "Расширяемый и модульный дизайн с аддонами.",
      quickstart_title: "Быстрый старт",
      quickstart_subtitle: "Краткое руководство для игроков и сборщиков модпаков, которые хотят сразу приступить к аддону.",
      quickstart_step_install_title: "Установите зависимости",
      quickstart_step_install_desc: "Скачайте Create, Flywheel и Kotlin для Forge/NeoForge, затем поместите jar Structures Overhaul в /mods.",
      quickstart_step_install_link: "Открыть руководство по установке",
      quickstart_step_config_title: "Настройте генерацию мира",
      quickstart_step_config_desc: "Используйте готовые датапаки или измените JSON-конфиг, чтобы регулировать частоту спавна, биомы и лут.",
      quickstart_step_config_link: "См. параметры конфигурации",
      quickstart_step_explore_title: "Исследуйте и расширяйте",
      quickstart_step_explore_desc: "Открывайте проработанные объекты по всему миру и дополняйте их наборами структур от сообщества.",
      quickstart_step_explore_link: "Найти аддоны",
      spotlight_title: "Знаковые структуры",
      spotlight_subtitle: "Проекты, показывающие, как сочетаются автоматизация Create и исследование.",
      spotlight_skyport_title: "Небесные доки",
      spotlight_skyport_desc: "Парящие фермы с механическими манипуляторами — идеальный ввод в деплойеры и канатные лебёдки.",
      spotlight_skyport_feature1: "Появляются над умеренными океанами на высоте Y120–Y150.",
      spotlight_skyport_feature2: "В сундуках — базовые компоненты Create и кинетические шаблоны.",
      spotlight_foundry_title: "Пепельный литейный цех",
      spotlight_foundry_desc: "Базальтовый комбинат в вулканических биомах с примерами последовательной сборки.",
      spotlight_foundry_feature1: "Генерируется в тегах горячих биомов Верхнего мира с настраиваемой редкостью.",
      spotlight_foundry_feature2: "Содержит печи на всполохах и разворачиваемые цепочки автоматизации.",
      spotlight_observatory_title: "Полярная обсерватория",
      spotlight_observatory_desc: "Заснеженный форпост, демонстрирующий маршрутизацию вращения и логистику механизмов.",
      spotlight_observatory_feature1: "Редко появляется в снежных склонах и рощах.",
      spotlight_observatory_feature2: "Скрытые журналы открывают цепочку достижений с лором.",
      updates_title: "Последние обновления",
      updates_subtitle: "Следите за приоритетами разработки и планируйте обновления сервера.",
      update_entry_1_date: "Март 2025",
      update_entry_1_title: "1.5.0 — Пакет автоматизации",
      update_entry_1_desc: "Переработаны таблицы лута с новыми кинетическими стартерами, добавлены многоязычные подсказки и оптимизирована смена палитр структур.",
      update_entry_2_date: "Январь 2025",
      update_entry_2_title: "1.4.2 — Исправление производительности",
      update_entry_2_desc: "Улучшена пакетная генерация чанков для крупных схем и исправлены вылеты с экспериментальными сборками Flywheel.",
      update_entry_3_date: "Ноябрь 2024",
      update_entry_3_title: "1.4.0 — Разнообразие биомов",
      update_entry_3_desc: "Добавлены региональные варианты лута, 12 новых палитр структур и обновлены шаблоны датапаков.",
      docs_card_title: "Центр документации",
      docs_card_desc: "Справочные страницы описывают установку, поведение структур, хуки датапаков и API интеграций.",
      docs_card_link_install: "Чек-лист установки",
      docs_card_link_structures: "Каталог структур",
      docs_card_link_addons: "Индекс аддонов и интеграций",
      community_card_title: "Сообщество",
      community_card_desc: "Общайтесь, делитесь схематиками и влияйте на дорожную карту.",
      community_card_link_discord: "Сервер Discord",
      community_card_link_github: "GitHub — задачи и вклад",
      community_card_link_modrinth: "Страница на Modrinth",
      roadmap_card_title: "Основные планы",
      roadmap_card_desc: "В разработке сюжетные задания, дополнения для Незера и настраиваемый экспорт контрапций.",
      roadmap_card_callout: "Есть идея? Оставьте её в канале обратной связи Discord.",
      installation_title: "Установка",
      installation_desc: "Следуйте этим шагам для установки мода.",
      structures_title: "Структуры",
      structures_desc: "Обзор всех структур и их свойств.",
      addons_title: "Аддоны и интеграции",
      addons_desc: "Аддоны для расширения возможностей мода.",
      changelog_title: "Список изменений",
      changelog_desc: "Текст-заглушка: заметки о версиях, последние обновления, исправления.",
      faq_title: "Частые вопросы",
      faq_desc: "Распространённые вопросы и ответы.",
      credits_title: "Авторы",
      credits_desc: "Благодарности участникам проекта.",
      about_title: "О проекте",
      about_desc: "Узнайте больше о моде.",
      credits_creator: "Создатель",
      credits_builder: "Строитель",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "Как установить мод?",
      faq_a1: "Скачайте jar файл мода, установите Create и поместите файл в папку /mods.",
      faq_q2: "Работает ли с Biomes O’ Plenty?",
      faq_a2: "Да, установите аддон BOP Integration для появления структур в биомах BOP.",
      faq_q3: "Совместим ли мод с модпаками?",
      faq_a3: "Он должен работать с большинством модпаков на Forge/NeoForge; проверяйте совместимость версий.",
      about_text: "Create: Structures Overhaul — аддон генерации мира для мода Create. Он обогащает исследование, добавляя тематические структуры в мир. Проект открыт и приветствует вклад от сообщества.",
    },
    ua: {
      site_title: "Create: Structures Overhaul — Вікі",
      search_placeholder: "Пошук в документації…",
      nav_home: "Головна",
      nav_installation: "Встановлення",
      nav_structures: "Структури",
      nav_addons: "Аддони та інтеграції",
      nav_changelog: "Список змін",
      nav_faq: "Часті питання",
      nav_credits: "Автори",
      nav_about: "Про проект",
      hero_title: "Create: Structures Overhaul — Вікі",
      hero_subtitle: "Розширте Create захоплюючими структурами.",
      hero_badge: "Аддон генерації світу для Create",
      hero_get_started: "Почати",
      hero_view_structures: "Переглянути структури",
      hero_github: "GitHub",
      hero_download: "Завантажити мод",
      hero_version: "Останній реліз: 1.5.0 • Minecraft 1.20.1",
      hero_license: "Відкритий код • Ліцензія MIT",
      features_title: "Огляд можливостей",
      feature_dynamic_title: "Динамічна генерація",
      feature_dynamic_desc: "Структури плавно з'являються під час генерації світу.",
      feature_biome_title: "Інтеграція з біомами",
      feature_biome_desc: "Підлаштовано під різні біоми та середовища.",
      feature_addon_title: "Підтримка аддонів",
      feature_addon_desc: "Розширюваний і модульний дизайн з аддонами.",
      quickstart_title: "Швидкий старт",
      quickstart_subtitle: "Короткий гайд для гравців та авторів модпаків, які хочуть одразу зануритись у аддон.",
      quickstart_step_install_title: "Встановіть залежності",
      quickstart_step_install_desc: "Завантажте Create, Flywheel і Kotlin для Forge/NeoForge, після чого помістіть jar Structures Overhaul у /mods.",
      quickstart_step_install_link: "Відкрити інструкцію",
      quickstart_step_config_title: "Налаштуйте генерацію світу",
      quickstart_step_config_desc: "Скористайтеся готовими датапаками або змініть JSON-конфіг, щоб керувати частотою появи, біомами та здобиччю.",
      quickstart_step_config_link: "Переглянути параметри",
      quickstart_step_explore_title: "Досліджуйте та розширюйте",
      quickstart_step_explore_desc: "Знаходьте створені вручну об'єкти по всьому світу та доповнюйте їх наборами структур від спільноти.",
      quickstart_step_explore_link: "Знайти аддони",
      spotlight_title: "Структури у фокусі",
      spotlight_subtitle: "Проекти, що демонструють поєднання автоматизації Create та дослідження.",
      spotlight_skyport_title: "Небесні доки",
      spotlight_skyport_desc: "Плавучі естакади з механічними маніпуляторами — чудовий вступ до деплойерів та канатних лебідок.",
      spotlight_skyport_feature1: "З'являються над помірними океанами на висоті Y120–Y150.",
      spotlight_skyport_feature2: "У скринях — базові компоненти Create та кінетичні шаблони.",
      spotlight_foundry_title: "Попеляста ливарня",
      spotlight_foundry_desc: "Базальтовий завод у вулканічних біомах із прикладами послідовного складання.",
      spotlight_foundry_feature1: "Генерується в гарячих біомах Верхнього світу з налаштовуваною рідкістю.",
      spotlight_foundry_feature2: "Містить печі на блейзах і розгортані ланцюги автоматизації.",
      spotlight_observatory_title: "Полярна обсерваторія",
      spotlight_observatory_desc: "Засніжений форпост, що демонструє маршрутизацію обертання та логістику механізмів.",
      spotlight_observatory_feature1: "Рідкісна поява у сніжних схилах та гаях.",
      spotlight_observatory_feature2: "Приховані журнали відкривають сюжетний ланцюжок досягнень.",
      updates_title: "Останні оновлення",
      updates_subtitle: "Слідкуйте за фокусом розробки та плануйте оновлення сервера.",
      update_entry_1_date: "Березень 2025",
      update_entry_1_title: "1.5.0 — Пакет автоматизації",
      update_entry_1_desc: "Перероблено таблиці здобичі з новими кінетичними стартерами, додано багатомовні підказки та оптимізовано зміну палітр структур.",
      update_entry_2_date: "Січень 2025",
      update_entry_2_title: "1.4.2 — Виправлення продуктивності",
      update_entry_2_desc: "Поліпшено пакетну генерацію чанків для великих схем і виправлено вильоти з експериментальними збірками Flywheel.",
      update_entry_3_date: "Листопад 2024",
      update_entry_3_title: "1.4.0 — Різноманіття біомів",
      update_entry_3_desc: "Додано регіональні варіанти здобичі, 12 нових палітр структур і оновлено шаблони датапаків.",
      docs_card_title: "Центр документації",
      docs_card_desc: "Довідкові сторінки описують установку, поведінку структур, гачки датапаків та API інтеграцій.",
      docs_card_link_install: "Контрольний список установки",
      docs_card_link_structures: "Каталог структур",
      docs_card_link_addons: "Індекс аддонів та інтеграцій",
      community_card_title: "Спільнота",
      community_card_desc: "Спілкуйтеся, діліться схематиками та впливайте на дорожню карту.",
      community_card_link_discord: "Сервер Discord",
      community_card_link_github: "GitHub — питання та внесок",
      community_card_link_modrinth: "Сторінка на Modrinth",
      roadmap_card_title: "Ключові плани",
      roadmap_card_desc: "У роботі сюжетні квести, доповнення для Нижнього світу та налаштовуваний експорт механізмів.",
      roadmap_card_callout: "Є ідея? Напишіть її в каналі зворотного зв'язку Discord.",
      installation_title: "Встановлення",
      installation_desc: "Дотримуйтесь цих кроків для встановлення моду.",
      structures_title: "Структури",
      structures_desc: "Огляд усіх структур та їх властивостей.",
      addons_title: "Аддони та інтеграції",
      addons_desc: "Аддони для розширення можливостей моду.",
      changelog_title: "Список змін",
      changelog_desc: "Текст-заглушка: нотатки про версії, останні оновлення, виправлення.",
      faq_title: "Часті питання",
      faq_desc: "Поширені питання та відповіді.",
      credits_title: "Автори",
      credits_desc: "Подяки учасникам проекту.",
      about_title: "Про проект",
      about_desc: "Дізнайтеся більше про мод.",
      credits_creator: "Творець",
      credits_builder: "Будівельник",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "Як встановити мод?",
      faq_a1: "Завантажте jar файл моду, встановіть Create і помістіть файл у папку /mods.",
      faq_q2: "Чи працює з Biomes O’ Plenty?",
      faq_a2: "Так, встановіть аддон BOP Integration для появи структур у біомах BOP.",
      faq_q3: "Чи сумісний мод із модпаками?",
      faq_a3: "Він повинен працювати з більшістю модпаків на Forge/NeoForge; перевіряйте сумісність версій.",
      about_text: "Create: Structures Overhaul — аддон генерації світу для модифікації Create. Він збагачує дослідження, додаючи тематичні структури у світ. Проект відкритий для внесків від спільноти.",
    },
    de: {
      site_title: "Create: Structures Overhaul — Wiki",
      search_placeholder: "Dokumentation durchsuchen…",
      nav_home: "Startseite",
      nav_installation: "Installation",
      nav_structures: "Strukturen",
      nav_addons: "Addons & Integration",
      nav_changelog: "Änderungsprotokoll",
      nav_faq: "FAQ",
      nav_credits: "Mitwirkende",
      nav_about: "Über",
      hero_title: "Create: Structures Overhaul — Wiki",
      hero_subtitle: "Erweitere Create mit beeindruckenden Strukturen.",
      hero_badge: "Weltgenerator-Addon für Create",
      hero_get_started: "Loslegen",
      hero_view_structures: "Strukturen ansehen",
      hero_github: "GitHub",
      hero_download: "Mod herunterladen",
      hero_version: "Neueste Version: 1.5.0 • Minecraft 1.20.1",
      hero_license: "Open Source • MIT-Lizenz",
      features_title: "Funktionsübersicht",
      feature_dynamic_title: "Dynamische Generierung",
      feature_dynamic_desc: "Strukturen erscheinen nahtlos in der Weltgeneration.",
      feature_biome_title: "Biome‑Integration",
      feature_biome_desc: "An verschiedene Umgebungen und Biome angepasst.",
      feature_addon_title: "Addon‑Unterstützung",
      feature_addon_desc: "Erweiterbares und modulares Design mit Addons.",
      quickstart_title: "Schnellstart",
      quickstart_subtitle: "Kurzer Überblick für Spieler und Modpack-Autoren, die sofort loslegen möchten.",
      quickstart_step_install_title: "Abhängigkeiten installieren",
      quickstart_step_install_desc: "Lade Create, Flywheel und Kotlin für Forge/NeoForge und lege die Structures Overhaul-JAR in /mods.",
      quickstart_step_install_link: "Installationsanleitung öffnen",
      quickstart_step_config_title: "Weltgeneration anpassen",
      quickstart_step_config_desc: "Nutze die mitgelieferten Datapacks oder passe die JSON-Konfiguration für Spawnraten, Biome und Loot an.",
      quickstart_step_config_link: "Konfigurationsoptionen ansehen",
      quickstart_step_explore_title: "Entdecken und erweitern",
      quickstart_step_explore_desc: "Erkunde handgefertigte Anlagen in der Oberwelt und erweitere sie mit Struktursammlungen der Community.",
      quickstart_step_explore_link: "Addons entdecken",
      spotlight_title: "Strukturen im Fokus",
      spotlight_subtitle: "Vorzeigebauten, die Automatisierung und Erkundung verbinden.",
      spotlight_skyport_title: "Himmelshafen-Docks",
      spotlight_skyport_desc: "Schwebende Gerüste mit mechanischen Armen – ideal, um Deployer und Seilwinden kennenzulernen.",
      spotlight_skyport_feature1: "Erscheinen über gemäßigten Ozeanen in Höhe Y120–Y150.",
      spotlight_skyport_feature2: "Beute enthält frühe Create-Komponenten und kinetische Vorlagen.",
      spotlight_foundry_title: "Aschenschmiede",
      spotlight_foundry_desc: "Eine basaltgefertigte Raffinerie in vulkanischen Biomen mit Beispielen für Sequenzmontagen.",
      spotlight_foundry_feature1: "Generiert in heißen Overworld-Biomen mit anpassbarer Seltenheit.",
      spotlight_foundry_feature2: "Beinhaltet lohengespeiste Schmelzen und ausklappbare Automationsketten.",
      spotlight_observatory_title: "Aurora-Observatorium",
      spotlight_observatory_desc: "Schneebedeckter Außenposten, der Rotationsverteilung und Logistik von Konstruktionen zeigt.",
      spotlight_observatory_feature1: "Seltene Spawnrate in verschneiten Hängen und Wäldern.",
      spotlight_observatory_feature2: "Versteckte Tagebuchseiten schalten eine lorefreundliche Erfolgsreihe frei.",
      updates_title: "Aktuelle Updates",
      updates_subtitle: "Verfolge den Entwicklungsfokus und plane Server-Upgrades.",
      update_entry_1_date: "März 2025",
      update_entry_1_title: "1.5.0 — Automatisierungspaket",
      update_entry_1_desc: "Loot-Tabellen überarbeitet, neue kinetische Starter ergänzt, mehrsprachige Tooltips hinzugefügt und Palettenwechsel optimiert.",
      update_entry_2_date: "Januar 2025",
      update_entry_2_title: "1.4.2 — Performance-Hotfix",
      update_entry_2_desc: "Verbesserte Chunk-Bündelung für große Schemata und Abstürze mit experimentellen Flywheel-Builds behoben.",
      update_entry_3_date: "November 2024",
      update_entry_3_title: "1.4.0 — Biome-Vielfalt",
      update_entry_3_desc: "Regionale Loot-Varianten eingeführt, 12 neue Strukturpaletten ergänzt und Datapack-Vorlagen aktualisiert.",
      docs_card_title: "Dokumentationshub",
      docs_card_desc: "Referenzseiten erklären Installation, Strukturverhalten, Datapack-Hooks und Integrations-APIs.",
      docs_card_link_install: "Installations-Checkliste",
      docs_card_link_structures: "Strukturkatalog",
      docs_card_link_addons: "Addon- & Integrationsindex",
      community_card_title: "Community-Ressourcen",
      community_card_desc: "Tritt der Diskussion bei, teile Schemata und gestalte die Roadmap mit.",
      community_card_link_discord: "Discord-Server",
      community_card_link_github: "GitHub – Issues & Beiträge",
      community_card_link_modrinth: "Modrinth-Seite",
      roadmap_card_title: "Roadmap-Highlights",
      roadmap_card_desc: "In Arbeit sind Story-Quests, Nether-Erweiterungen und konfigurierbare Konstruktionsexporte.",
      roadmap_card_callout: "Idee? Hinterlasse Feedback im Discord-Kanal.",
      installation_title: "Installation",
      installation_desc: "Folgen Sie diesen Schritten, um die Mod zu installieren.",
      structures_title: "Strukturen",
      structures_desc: "Übersicht über alle Strukturen und ihre Eigenschaften.",
      addons_title: "Addons & Integration",
      addons_desc: "Addons zur Erweiterung der Modfunktionen.",
      changelog_title: "Änderungsprotokoll",
      changelog_desc: "Platzhalter: Versionshinweise, neueste Updates, Fehlerbehebungen.",
      faq_title: "Häufig gestellte Fragen",
      faq_desc: "Häufige Fragen und Antworten.",
      credits_title: "Mitwirkende",
      credits_desc: "Danksagungen an die Beteiligten.",
      about_title: "Über das Projekt",
      about_desc: "Erfahren Sie mehr über die Mod.",
      credits_creator: "Schöpfer",
      credits_builder: "Builder",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "Wie installiere ich die Mod?",
      faq_a1: "Laden Sie die Mod-JAR herunter, installieren Sie Create und legen Sie die Datei in Ihren /mods Ordner.",
      faq_q2: "Funktioniert es mit Biomes O’ Plenty?",
      faq_a2: "Ja, installieren Sie das BOP Integration Addon, um Strukturen in BOP-Biomen zu erhalten.",
      faq_q3: "Ist die Mod mit Modpacks kompatibel?",
      faq_a3: "Sie sollte mit den meisten Modpacks auf Forge/NeoForge funktionieren; prüfen Sie die Versionskompatibilität.",
      about_text: "Create: Structures Overhaul ist ein Welten‑Generation‑Addon für die Create‑Mod. Es bereichert die Erkundung, indem es thematische Strukturen hinzufügt. Das Projekt ist Open Source und freut sich über Beiträge.",
    },
    fr: {
      site_title: "Create: Structures Overhaul — Wiki",
      search_placeholder: "Rechercher dans la documentation…",
      nav_home: "Accueil",
      nav_installation: "Installation",
      nav_structures: "Structures",
      nav_addons: "Add-ons et intégrations",
      nav_changelog: "Journal des changements",
      nav_faq: "FAQ",
      nav_credits: "Crédits",
      nav_about: "À propos",
      hero_title: "Create: Structures Overhaul — Wiki",
      hero_subtitle: "Étendez Create avec de superbes structures.",
      hero_badge: "Extension de génération de monde pour Create",
      hero_get_started: "Commencer",
      hero_view_structures: "Voir les structures",
      hero_github: "GitHub",
      hero_download: "Télécharger le mod",
      hero_version: "Dernière version : 1.5.0 • Minecraft 1.20.1",
      hero_license: "Open source • Licence MIT",
      features_title: "Aperçu des fonctionnalités",
      feature_dynamic_title: "Génération dynamique",
      feature_dynamic_desc: "Les structures apparaissent de manière transparente dans la génération du monde.",
      feature_biome_title: "Intégration des biomes",
      feature_biome_desc: "Adapté à différents environnements et biomes.",
      feature_addon_title: "Prise en charge des add-ons",
      feature_addon_desc: "Conception extensible et modulaire avec des add-ons.",
      quickstart_title: "Démarrage rapide",
      quickstart_subtitle: "Aperçu pour les joueurs et créateurs de modpacks qui veulent commencer immédiatement.",
      quickstart_step_install_title: "Installer les dépendances",
      quickstart_step_install_desc: "Téléchargez Create, Flywheel et Kotlin pour Forge/NeoForge puis placez le JAR Structures Overhaul dans /mods.",
      quickstart_step_install_link: "Ouvrir le guide d'installation",
      quickstart_step_config_title: "Ajuster la génération du monde",
      quickstart_step_config_desc: "Utilisez les datapacks fournis ou modifiez le JSON pour gérer la fréquence d'apparition, les biomes et le butin.",
      quickstart_step_config_link: "Voir les options de configuration",
      quickstart_step_explore_title: "Explorer et étendre",
      quickstart_step_explore_desc: "Découvrez des installations artisanales dans l'Overworld et enrichissez-les avec les packs de structures communautaires.",
      quickstart_step_explore_link: "Découvrir les add-ons",
      spotlight_title: "Structures à la une",
      spotlight_subtitle: "Des constructions emblématiques mêlant automatisation Create et exploration.",
      spotlight_skyport_title: "Dock du ciel",
      spotlight_skyport_desc: "Pontons flottants bourrés de bras mécaniques, parfaits pour découvrir les déployeurs et les poulies.",
      spotlight_skyport_feature1: "Apparaît au-dessus des océans tempérés entre Y120 et Y150.",
      spotlight_skyport_feature2: "Le butin contient des composants Create de départ et des modèles cinétiques.",
      spotlight_foundry_title: "Fonderie cendrée",
      spotlight_foundry_desc: "Raffinerie en basalte dans les biomes volcaniques avec exemples d'assemblage séquencé.",
      spotlight_foundry_feature1: "Générée dans les biomes chauds de l'Overworld avec rareté ajustable.",
      spotlight_foundry_feature2: "Comprend des fonderies alimentées par des blazes et des chaînes d'automatisation déployables.",
      spotlight_observatory_title: "Observatoire auroral",
      spotlight_observatory_desc: "Avant-poste enneigé présentant la distribution de puissance rotative et la logistique des machines.",
      spotlight_observatory_feature1: "Apparition rare dans les pentes et forêts enneigées.",
      spotlight_observatory_feature2: "Des pages de journal cachées débloquent une série de succès immersive.",
      updates_title: "Dernières mises à jour",
      updates_subtitle: "Suivez les priorités de développement et planifiez vos mises à jour serveur.",
      update_entry_1_date: "Mars 2025",
      update_entry_1_title: "1.5.0 — Passage automatisation",
      update_entry_1_desc: "Refonte des tables de butin avec de nouveaux starters cinétiques, ajout de bulles d'aide multilingues et optimisation des changements de palette.",
      update_entry_2_date: "Janvier 2025",
      update_entry_2_title: "1.4.2 — Correctif de performances",
      update_entry_2_desc: "Amélioration du traitement des chunks pour les grands schémas et corrections des crashs avec les versions expérimentales de Flywheel.",
      update_entry_3_date: "Novembre 2024",
      update_entry_3_title: "1.4.0 — Diversité des biomes",
      update_entry_3_desc: "Ajout de variantes de butin régionales, de 12 nouvelles palettes de structures et mise à jour des modèles de datapack.",
      docs_card_title: "Centre de documentation",
      docs_card_desc: "Les pages de référence détaillent l'installation, le comportement des structures, les hooks de datapack et les API d'intégration.",
      docs_card_link_install: "Liste d'installation",
      docs_card_link_structures: "Catalogue des structures",
      docs_card_link_addons: "Index des add-ons et intégrations",
      community_card_title: "Ressources communautaires",
      community_card_desc: "Discutez, partagez des schémas et participez à la feuille de route.",
      community_card_link_discord: "Serveur Discord",
      community_card_link_github: "GitHub — tickets & contributions",
      community_card_link_modrinth: "Page Modrinth",
      roadmap_card_title: "Points clés de la feuille de route",
      roadmap_card_desc: "Prochainement : quêtes scénarisées, packs d'extension du Nether et export configurables des machines.",
      roadmap_card_callout: "Une idée ? Partagez-la sur le canal feedback du Discord.",
      installation_title: "Installation",
      installation_desc: "Suivez ces étapes pour installer le mod.",
      structures_title: "Structures",
      structures_desc: "Vue d'ensemble de toutes les structures et de leurs caractéristiques.",
      addons_title: "Add-ons et intégrations",
      addons_desc: "Add-ons pour étendre les fonctionnalités du mod.",
      changelog_title: "Journal des changements",
      changelog_desc: "Espace réservé : notes de version, mises à jour récentes, corrections de bugs.",
      faq_title: "Questions fréquemment posées",
      faq_desc: "Questions et réponses communes.",
      credits_title: "Crédits",
      credits_desc: "Remerciements aux contributeurs.",
      about_title: "À propos",
      about_desc: "En savoir plus sur le projet.",
      credits_creator: "Créateur",
      credits_builder: "Builder",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "Comment installer le mod ?",
      faq_a1: "Téléchargez le fichier JAR du mod, installez Create et placez le fichier dans le dossier /mods.",
      faq_q2: "Fonctionne-t-il avec Biomes O’ Plenty ?",
      faq_a2: "Oui, installez l'add-on BOP Integration pour faire apparaître des structures dans les biomes BOP.",
      faq_q3: "Est-ce compatible avec les modpacks ?",
      faq_a3: "Il devrait fonctionner avec la plupart des modpacks basés sur Forge/NeoForge ; vérifiez la compatibilité des versions.",
      about_text: "Create: Structures Overhaul est un module d'extension de génération de monde pour le mod Create. Il enrichit l'exploration en ajoutant des structures thématiques au monde. Le projet est open source et accueille les contributions.",
    },
    es: {
      site_title: "Create: Structures Overhaul — Wiki",
      search_placeholder: "Buscar en la documentación…",
      nav_home: "Inicio",
      nav_installation: "Instalación",
      nav_structures: "Estructuras",
      nav_addons: "Complementos e integraciones",
      nav_changelog: "Registro de cambios",
      nav_faq: "Preguntas frecuentes",
      nav_credits: "Créditos",
      nav_about: "Acerca de",
      hero_title: "Create: Structures Overhaul — Wiki",
      hero_subtitle: "Amplía Create con impresionantes estructuras.",
      hero_badge: "Complemento de generación de mundo para Create",
      hero_get_started: "Comenzar",
      hero_view_structures: "Ver estructuras",
      hero_github: "GitHub",
      hero_download: "Descargar mod",
      hero_version: "Última versión: 1.5.0 • Minecraft 1.20.1",
      hero_license: "Código abierto • Licencia MIT",
      features_title: "Resumen de funciones",
      feature_dynamic_title: "Generación dinámica",
      feature_dynamic_desc: "Las estructuras aparecen sin interrupciones en la generación del mundo.",
      feature_biome_title: "Integración de biomas",
      feature_biome_desc: "Adaptado a diferentes entornos y biomas.",
      feature_addon_title: "Compatibilidad con complementos",
      feature_addon_desc: "Diseño extensible y modular con complementos.",
      quickstart_title: "Inicio rápido",
      quickstart_subtitle: "Resumen para jugadores y creadores de modpacks que quieren empezar al instante.",
      quickstart_step_install_title: "Instala las dependencias",
      quickstart_step_install_desc: "Descarga Create, Flywheel y Kotlin para Forge/NeoForge y coloca el JAR de Structures Overhaul en /mods.",
      quickstart_step_install_link: "Abrir guía de instalación",
      quickstart_step_config_title: "Ajusta la generación del mundo",
      quickstart_step_config_desc: "Usa los datapacks incluidos o modifica el JSON para controlar frecuencias de aparición, biomas y botín.",
      quickstart_step_config_link: "Ver opciones de configuración",
      quickstart_step_explore_title: "Explora y amplía",
      quickstart_step_explore_desc: "Descubre instalaciones artesanales por todo el Overworld y amplíalas con paquetes de estructuras de la comunidad.",
      quickstart_step_explore_link: "Descubrir complementos",
      spotlight_title: "Estructuras destacadas",
      spotlight_subtitle: "Construcciones emblemáticas que unen automatización de Create y exploración.",
      spotlight_skyport_title: "Muelles celestes",
      spotlight_skyport_desc: "Andenes flotantes llenos de brazos mecánicos, perfectos para aprender sobre deployers y poleas.",
      spotlight_skyport_feature1: "Aparecen sobre océanos templados entre Y120 y Y150.",
      spotlight_skyport_feature2: "El botín incluye componentes básicos de Create y plantillas cinéticas.",
      spotlight_foundry_title: "Fundición de ceniza",
      spotlight_foundry_desc: "Refinería de basalto en biomas volcánicos con ejemplos de ensamblaje secuencial.",
      spotlight_foundry_feature1: "Se genera en biomas cálidos del Overworld con rareza configurable.",
      spotlight_foundry_feature2: "Incluye fundiciones alimentadas por blazes y cadenas de automatización desplegables.",
      spotlight_observatory_title: "Observatorio aurora",
      spotlight_observatory_desc: "Puesto de investigación nevado que muestra el enrutamiento de potencia rotativa y la logística de contraptions.",
      spotlight_observatory_feature1: "Aparición rara en laderas y bosques nevados.",
      spotlight_observatory_feature2: "Páginas de diario ocultas desbloquean una cadena de logros acorde al lore.",
      updates_title: "Últimas novedades",
      updates_subtitle: "Sigue el foco de desarrollo y planifica las actualizaciones de tu servidor.",
      update_entry_1_date: "Marzo 2025",
      update_entry_1_title: "1.5.0 — Pase de automatización",
      update_entry_1_desc: "Tablas de botín revisadas con nuevos inicios cinéticos, se añadieron ayudas multilingües y se optimizaron los cambios de paleta.",
      update_entry_2_date: "Enero 2025",
      update_entry_2_title: "1.4.2 — Corrección de rendimiento",
      update_entry_2_desc: "Mejorado el procesamiento por lotes de chunks para esquemas grandes y resueltos los fallos con compilaciones experimentales de Flywheel.",
      update_entry_3_date: "Noviembre 2024",
      update_entry_3_title: "1.4.0 — Diversidad de biomas",
      update_entry_3_desc: "Se añadieron variantes regionales de botín, 12 nuevas paletas de estructuras y se actualizaron las plantillas de datapack.",
      docs_card_title: "Centro de documentación",
      docs_card_desc: "Las páginas de referencia detallan la instalación, el comportamiento de las estructuras, los hooks de datapack y las API de integración.",
      docs_card_link_install: "Lista de instalación",
      docs_card_link_structures: "Catálogo de estructuras",
      docs_card_link_addons: "Índice de complementos e integraciones",
      community_card_title: "Recursos de la comunidad",
      community_card_desc: "Únete a la conversación, comparte esquemas y ayuda a definir la hoja de ruta.",
      community_card_link_discord: "Servidor de Discord",
      community_card_link_github: "GitHub — incidencias y aportes",
      community_card_link_modrinth: "Página de Modrinth",
      roadmap_card_title: "Aspectos de la hoja de ruta",
      roadmap_card_desc: "Próximamente: misiones narrativas, packs de expansión del Nether y exportaciones de contraptions configurables.",
      roadmap_card_callout: "¿Tienes una idea? Compártela en el canal de feedback de Discord.",
      installation_title: "Instalación",
      installation_desc: "Siga estos pasos para instalar el mod.",
      structures_title: "Estructuras",
      structures_desc: "Descripción general de todas las estructuras y sus atributos.",
      addons_title: "Complementos e integraciones",
      addons_desc: "Complementos para ampliar las capacidades del mod.",
      changelog_title: "Registro de cambios",
      changelog_desc: "Espacio reservado: notas de la versión, actualizaciones recientes, corrección de errores.",
      faq_title: "Preguntas frecuentes",
      faq_desc: "Preguntas y respuestas comunes.",
      credits_title: "Créditos",
      credits_desc: "Agradecimientos a los colaboradores.",
      about_title: "Acerca de",
      about_desc: "Obtén más información sobre el proyecto.",
      credits_creator: "Creador",
      credits_builder: "Constructor",
      footer_text: "© 2025 Denmoth & Timy228e",
      link_github: "GitHub",
      link_modrinth: "Modrinth",
      link_curseforge: "CurseForge",
      link_discord: "Discord",
      faq_q1: "¿Cómo instalo el mod?",
      faq_a1: "Descarga el archivo JAR del mod, instala Create y coloca el archivo en la carpeta /mods.",
      faq_q2: "¿Funciona con Biomes O’ Plenty?",
      faq_a2: "Sí, instala el complemento BOP Integration para generar estructuras en los biomas BOP.",
      faq_q3: "¿Es compatible con modpacks?",
      faq_a3: "Debería funcionar con la mayoría de los modpacks basados en Forge/NeoForge; verifica la compatibilidad de la versión.",
      about_text: "Create: Structures Overhaul es un complemento de generación de mundos para el mod Create. Enriquece la exploración al añadir estructuras temáticas al mundo. El proyecto es de código abierto y da la bienvenida a las contribuciones.",
    },
  };

  // Determine initial language from localStorage or browser setting
  let currentLang = localStorage.getItem('cso_lang') || navigator.language.slice(0, 2).toLowerCase();
  if (!translations[currentLang]) currentLang = 'en';

  // Determine initial theme
  let darkMode = localStorage.getItem('cso_theme') === 'dark' || (window.matchMedia('(prefers-color-scheme: dark)').matches && !localStorage.getItem('cso_theme'));

  // Apply initial theme
  function applyTheme() {
    document.body.classList.toggle('dark', darkMode);
    document.body.classList.toggle('light', !darkMode);
  }
  applyTheme();

  // Populate language selector
  const langSelect = document.getElementById('language-select');
  if (langSelect) {
    langSelect.value = currentLang;
    langSelect.addEventListener('change', () => {
      currentLang = langSelect.value;
      localStorage.setItem('cso_lang', currentLang);
      loadLanguage();
      buildSearchIndex();
    });
  }

  // Theme toggle handler
  const themeToggle = document.getElementById('theme-toggle');
  if (themeToggle) {
    themeToggle.addEventListener('click', () => {
      darkMode = !darkMode;
      localStorage.setItem('cso_theme', darkMode ? 'dark' : 'light');
      applyTheme();
    });
  }

  // Sidebar toggle for mobile
  const sidebarToggle = document.querySelector('.sidebar-toggle');
  const sidebar = document.getElementById('sidebar');
  if (sidebarToggle && sidebar) {
    sidebarToggle.addEventListener('click', () => {
      sidebar.classList.toggle('open');
    });
  }

  // Function to apply translations to elements with data-i18n
  function loadLanguage() {
    const langDict = translations[currentLang] || translations.en;
    document.querySelectorAll('[data-i18n]').forEach(el => {
      const key = el.getAttribute('data-i18n');
      if (langDict[key]) {
        el.textContent = langDict[key];
      } else if (translations.en[key]) {
        el.textContent = translations.en[key];
      }
    });
    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
      const key = el.getAttribute('data-i18n-placeholder');
      if (langDict[key]) {
        el.setAttribute('placeholder', langDict[key]);
      } else if (translations.en[key]) {
        el.setAttribute('placeholder', translations.en[key]);
      }
    });
    // Update site title
    const titleKey = 'site_title';
    document.title = langDict[titleKey] || translations.en[titleKey];
  }
  loadLanguage();

  // Build search index based on current language
  let searchIndex = [];
  function buildSearchIndex() {
    const t = translations[currentLang] || translations.en;
    searchIndex = [
      { title: t.nav_home, content: t.hero_subtitle || '', url: 'index.html' },
      { title: t.quickstart_title || '', content: t.quickstart_subtitle || '', url: 'index.html#quickstart' },
      { title: t.spotlight_title || '', content: t.spotlight_subtitle || '', url: 'index.html#spotlights' },
      { title: t.updates_title || '', content: t.update_entry_1_desc || '', url: 'index.html#updates' },
      { title: t.docs_card_title || '', content: t.docs_card_desc || '', url: 'index.html#resources' },
      { title: t.nav_installation, content: t.installation_desc || '', url: 'pages/installation.html' },
      { title: t.nav_structures, content: t.structures_desc || '', url: 'pages/structures.html' },
      { title: t.nav_addons, content: t.addons_desc || '', url: 'pages/addons.html' },
      { title: t.nav_changelog, content: t.changelog_desc || '', url: 'pages/changelog.html' },
      { title: t.nav_faq, content: t.faq_desc || '', url: 'pages/faq.html' },
      { title: t.nav_credits, content: t.credits_desc || '', url: 'pages/credits.html' },
      { title: t.nav_about, content: t.about_desc || '', url: 'pages/about.html' },
    ];
  }
  buildSearchIndex();

  // Search functionality
  const searchInput = document.getElementById('search-input');
  const resultsContainer = document.querySelector('.search-results');
  if (searchInput && resultsContainer) {
    searchInput.addEventListener('input', () => {
      const query = searchInput.value.trim().toLowerCase();
      if (query.length === 0) {
        resultsContainer.classList.remove('active');
        resultsContainer.innerHTML = '';
        return;
      }
      const results = searchIndex.filter(item => {
        return (item.title.toLowerCase().includes(query) || item.content.toLowerCase().includes(query));
      });
      if (results.length === 0) {
        resultsContainer.classList.remove('active');
        resultsContainer.innerHTML = '';
        return;
      }
      const listItems = results.map(r => `<li data-url="${r.url}"><strong>${r.title}</strong><br><small>${r.content}</small></li>`).join('');
      resultsContainer.innerHTML = `<ul>${listItems}</ul>`;
      resultsContainer.classList.add('active');
    });
    resultsContainer.addEventListener('click', e => {
      const li = e.target.closest('li');
      if (li) {
        const url = li.getAttribute('data-url');
        resultsContainer.classList.remove('active');
        searchInput.value = '';
        window.location.href = url;
      }
    });
    // Hide on outside click
    document.addEventListener('click', e => {
      if (!resultsContainer.contains(e.target) && !searchInput.contains(e.target)) {
        resultsContainer.classList.remove('active');
      }
    });
  }

  // Highlight active navigation item
  function highlightActiveNav() {
    const currentFile = (() => {
      const segments = window.location.pathname.split('/');
      const last = segments.pop() || segments.pop();
      if (!last || last === '') return 'index.html';
      return last;
    })();
    document.querySelectorAll('#sidebar nav a, .top-nav a').forEach(link => {
      const href = link.getAttribute('href');
      const normalized = href.replace(/^\.\/?/, '');
      const matchesHome = normalized === '' || normalized === 'index.html';
      const isActive = matchesHome ? currentFile === 'index.html' : (normalized.endsWith(currentFile));
      link.classList.toggle('active', isActive);
    });
  }
  highlightActiveNav();
});