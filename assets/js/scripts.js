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
      hero_get_started: "Get Started",
      hero_github: "GitHub",
      hero_download: "Download Mod",
      features_title: "Features Overview",
      feature_dynamic_title: "Dynamic Generation",
      feature_dynamic_desc: "Structures appear seamlessly in world generation.",
      feature_biome_title: "Biome Integration",
      feature_biome_desc: "Adapted to different environments and biomes.",
      feature_addon_title: "Addon Support",
      feature_addon_desc: "Extensible and modular design with addons.",
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
      hero_get_started: "Начать",
      hero_github: "GitHub",
      hero_download: "Скачать мод",
      features_title: "Обзор возможностей",
      feature_dynamic_title: "Динамическая генерация",
      feature_dynamic_desc: "Структуры плавно появляются при генерации мира.",
      feature_biome_title: "Интеграция с биомами",
      feature_biome_desc: "Адаптировано под разные биомы и окружения.",
      feature_addon_title: "Поддержка аддонов",
      feature_addon_desc: "Расширяемый и модульный дизайн с аддонами.",
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
      hero_get_started: "Почати",
      hero_github: "GitHub",
      hero_download: "Завантажити мод",
      features_title: "Огляд можливостей",
      feature_dynamic_title: "Динамічна генерація",
      feature_dynamic_desc: "Структури плавно з'являються під час генерації світу.",
      feature_biome_title: "Інтеграція з біомами",
      feature_biome_desc: "Підлаштовано під різні біоми та середовища.",
      feature_addon_title: "Підтримка аддонів",
      feature_addon_desc: "Розширюваний і модульний дизайн з аддонами.",
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
      hero_get_started: "Loslegen",
      hero_github: "GitHub",
      hero_download: "Mod herunterladen",
      features_title: "Funktionsübersicht",
      feature_dynamic_title: "Dynamische Generierung",
      feature_dynamic_desc: "Strukturen erscheinen nahtlos in der Weltgeneration.",
      feature_biome_title: "Biome‑Integration",
      feature_biome_desc: "An verschiedene Umgebungen und Biome angepasst.",
      feature_addon_title: "Addon‑Unterstützung",
      feature_addon_desc: "Erweiterbares und modulares Design mit Addons.",
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
      hero_get_started: "Commencer",
      hero_github: "GitHub",
      hero_download: "Télécharger le mod",
      features_title: "Aperçu des fonctionnalités",
      feature_dynamic_title: "Génération dynamique",
      feature_dynamic_desc: "Les structures apparaissent de manière transparente dans la génération du monde.",
      feature_biome_title: "Intégration des biomes",
      feature_biome_desc: "Adapté à différents environnements et biomes.",
      feature_addon_title: "Prise en charge des add-ons",
      feature_addon_desc: "Conception extensible et modulaire avec des add-ons.",
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
      hero_get_started: "Comenzar",
      hero_github: "GitHub",
      hero_download: "Descargar mod",
      features_title: "Resumen de funciones",
      feature_dynamic_title: "Generación dinámica",
      feature_dynamic_desc: "Las estructuras aparecen sin interrupciones en la generación del mundo.",
      feature_biome_title: "Integración de biomas",
      feature_biome_desc: "Adaptado a diferentes entornos y biomas.",
      feature_addon_title: "Compatibilidad con complementos",
      feature_addon_desc: "Diseño extensible y modular con complementos.",
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
      { title: t.nav_home, content: t.hero_subtitle || '', url: '/' },
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
    const currentPath = window.location.pathname.replace(/\/[^/]*$/, '').replace(/\/$/, '');
    document.querySelectorAll('#sidebar nav a').forEach(link => {
      const href = link.getAttribute('href');
      // Match root or page path
      if (href === '/' && (window.location.pathname === '/' || window.location.pathname.endsWith('index.html'))) {
        link.classList.add('active');
      } else if (window.location.pathname.endsWith(href)) {
        link.classList.add('active');
      } else {
        link.classList.remove('active');
      }
    });
  }
  highlightActiveNav();
});