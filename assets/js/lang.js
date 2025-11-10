
const SUPPORTED = ['en','ru','ua'];
const DEFAULT = 'en';
function detectLang(){
  const saved = localStorage.getItem('cso_lang');
  if(saved && SUPPORTED.includes(saved)) return saved;
  const n = navigator.language || navigator.userLanguage || 'en';
  const primary = n.split('-')[0].toLowerCase();
  const chosen = SUPPORTED.includes(primary) ? primary : DEFAULT;
  localStorage.setItem('cso_lang', chosen);
  return chosen;
}
async function loadLang(lang){
  if(!SUPPORTED.includes(lang)) lang = DEFAULT;
  const res = await fetch(`../assets/lang/${lang}.json`).catch(()=>null);
  const resRoot = await fetch(`assets/lang/${lang}.json`).catch(()=>null);
  const dict = res && res.ok ? await res.json() : resRoot && resRoot.ok ? await resRoot.json() : {};
  for(const el of document.querySelectorAll('[data-i18n]')){
    const key = el.getAttribute('data-i18n');
    const val = key.split('.').reduce((a,k)=> a && a[k], dict) || key;
    el.innerHTML = val;
  }
  for(const a of document.querySelectorAll('a[data-page]')){
    const page = a.getAttribute('data-page');
    const base = location.pathname.includes('/pages/') ? '../' : '';
    a.href = `${base}${page}.html?lang=${lang}`;
  }
  document.title = (dict.meta && dict.meta.title) || document.title;
}
function getLangFromQuery(){
  const p = new URLSearchParams(location.search);
  const l = (p.get('lang') || '').toLowerCase();
  return SUPPORTED.includes(l) ? l : null;
}
function setupLangSelector(current){
  const sel = document.getElementById('langSelect');
  sel.value = current;
  sel.addEventListener('change', () => {
    const l = sel.value;
    localStorage.setItem('cso_lang', l);
    const url = new URL(location.href);
    url.searchParams.set('lang', l);
    location.href = url.toString();
  });
}
function setupTheme(){
  const root = document.documentElement;
  const saved = localStorage.getItem('cso_theme');
  const prefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
  const initial = saved || (prefersDark ? 'dark' : 'light');
  root.classList.toggle('light', initial === 'light');
  const btn = document.getElementById('themeToggle');
  btn.textContent = initial === 'light' ? 'Dark' : 'Light';
  btn.addEventListener('click', () => {
    const isLight = root.classList.toggle('light');
    localStorage.setItem('cso_theme', isLight ? 'light' : 'dark');
    btn.textContent = isLight ? 'Dark' : 'Light';
  });
}
function setupSearch(){
  const input = document.getElementById('search');
  const list = document.getElementById('sidebarList');
  if(!input || !list) return;
  input.addEventListener('input', () => {
    const q = input.value.toLowerCase();
    for(const a of list.querySelectorAll('a')){
      const t = a.textContent.toLowerCase();
      a.style.display = t.includes(q) ? '' : 'none';
    }
  });
}
document.addEventListener('DOMContentLoaded', async () => {
  setupTheme();
  setupSearch();
  const lang = getLangFromQuery() || detectLang();
  await loadLang(lang);
  setupLangSelector(lang);
});
