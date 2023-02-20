Simple Java EE application:
Ukládání a náhled kurzovních lístků
Cílem je vytvořit aplikaci, která pracuje s kurzovními lístky.
Zadání má dvě části. FE část, která se zaměřuje na tvorbu frontendového řešení (HTML,
JavaScript / Angular / React) a BE část, která se zaměřuje na tvorbu backendu a aplikační
logiky.
FE:
Vytvořte FE aplikaci pomocí React / Angular nebo HTML a JS. Aplikace má dvě
obrazovky. Jedna, která získá z API pomocí GET requestu kurzovní lístky a zobrazí je.
Druhá, která zobrazí detail konkrétního kurzovního lístku.
Kurzovní lístky jsou dostupné zde:
https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangera
tes?web-api-key=c52a0682-4806-4903-828f-6cc66508329e
BE:
Vytvořte Enterprise Java Aplikaci či SpringBoot java. Aplikace bude mít jedno REST API,
které vrátí kurzovní lístky. REST API rozeznává jeden parametr, který se mu posílá jako
query atribut a má název usedb s možnými hodnoty (true nebo false). V případě true, vrátí
data z DB. V případě false, se vaše aplikace dotáže pro aktuální seznam kurzovních lístků
systému v České spořitelně, pomocí http dotazu: Metoda GET:
https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangera
tes?web-api-key=c52a0682-4806-4903-828f-6cc66508329e a tyto hodnoty si uloží do
databáze (DB může být libovolná).
FE druhá část:
Po implementaci BE části proveďte na FE takové změny, aby se kurzovní lístky
nestahovali přímo z České spořitelny, ale z vašeho lokálního BE.
Umístění zdrojových kódů a postup:
Zdrojové kódy umístěte na GITHUB – stačí veřejný profil, zadání není nic tajného. Pro
vytvoření balíčku aplikace využijte Maven či Gradle. Aplikaci lze postavit na Spring bootu
či na libovolném jiném framework. Dejte nám prosím vědět, až dokončíte implementaci.
