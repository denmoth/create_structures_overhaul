
const langs = {
  en: "English", ru: "Русский", ua: "Українська", de: "Deutsch", fr: "Français"
};
const langSel = document.getElementById('language-selector');
for (const [code, name] of Object.entries(langs)) {
  const opt = document.createElement('option');
  opt.value = code; opt.textContent = name;
  langSel.appendChild(opt);
}
let userLang = localStorage.getItem('lang') || navigator.language.slice(0,2);
if (!langs[userLang]) userLang = 'en';
langSel.value = userLang;
async function loadLang(l) {
  const res = await fetch(`assets/lang/${l}.json`);
  const data = await res.json();
  document.querySelectorAll('[data-i18n]').forEach(el => {
    const key = el.dataset.i18n.split('.').reduce((o,i)=>o?o[i]:null,data);
    if (key) el.textContent = key;
  });
}
langSel.addEventListener('change', e=>{
  localStorage.setItem('lang', e.target.value);
  loadLang(e.target.value);
});
loadLang(userLang);
// theme toggle
const toggleBtn = document.getElementById('theme-toggle');
const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
const theme = localStorage.getItem('theme') || (prefersDark ? 'dark' : 'light');
document.body.classList.toggle('dark', theme === 'dark');
toggleBtn.textContent = theme === 'dark' ? '🌙' : '🌞';
toggleBtn.onclick = ()=>{
  const dark = !document.body.classList.contains('dark');
  document.body.classList.toggle('dark', dark);
  localStorage.setItem('theme', dark ? 'dark' : 'light');
  toggleBtn.textContent = dark ? '🌙' : '🌞';
};
