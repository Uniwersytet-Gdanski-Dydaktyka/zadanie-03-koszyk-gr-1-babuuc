decyzje projektowe

promocje sa zrobione jako strategy
kazda promocja ma interface promotion dzieki temu mozna dodac nowa promocje bez zmiany starego kodu, to pasuje do open closed principle

nie uzylem command bo zadanie nie wymaga cofania operacji ani kolejki polecen

sortowanie jest przez comparator, dzieki temu koszyk nie zalezy od jednego stalego sposobu sortowania, to pasuje do dependency inversion principle

product jest immutable

nie zmieniam produktu po utworzeniu

promocja tworzy kopie produktu z nowa cena po promocji, to chroni dane przed przypadkowa zmiana

pierwsza wersja w zadaniu miala byc na tablicy a po refaktoryzacji kod pracuje na list product, dzieki temu latwiej dodawac produkty i zmieniac implementacje kolekcji

bestpromotionfinder sprawdza rozne kolejnosci promocji i wybiera najnizsza kwote do zaplaty, przy tej samej kwocie wybiera wynik z wieksza liczba gratisow
