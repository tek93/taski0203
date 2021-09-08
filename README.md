

Zadanie 2 oraz 3  są w jednym projekcie, wykorzystują, ten sam model logowania 
Logowanie i rejestracja 
/api/user/registration/api/user/registration <-- endpoint do rejestracji nowego konta
/api/user/login <-- endpoint do logowania (tu otrzymujemy Bearer token (JWT token który posłuży nam do autoryzacji))
aplikacja posiada możliwość tworznia Usera oraz Admina, jednak na ten moment oprócz nazwy nie równią się uprawnieniami 
/api/localization/save <-- dodanie informacji deviceId, latitiude, longitude obarczone walidacją 
/api/localization/all <-- wyszukanie wszystkich rekordów 
/api/localization/{id} <-- gdzie id to id konkretnego rekordu
/api/localization/device{deviceId} <-- wyszukanie listy wszystkich rekordów dla urządzenia o konkretnym id

3)
/api/invo/createUser <-- endpoint służy do utworzenia użytkownika w bazie danych striple
pole  "stripeId" posłuży on nam jako identyfikator dodają i wyszukując faktury dla konkretnego użytkownika 
/api/invo/createInvoice" <-- endpoint służy do kreowania  faktury, musimy przekazać parametry takie jak "productName" -nazwa produktu, "qty"-ilosc, "ua"- ilość jednostkową,
są to parametry wymagane przez platformę striple.
niestety nie udało mi się zaimplementować drugiej częsci zadania pobierania faktur,  konkretnego użytkownika, paltforma striple umożliwia  w swoim api pobieranie jedynie wszystkich faktor,
następnie należało by je przefiltrować i wyślietlić jedynie te dla konkretnego użytkownika, domyślam się ze o to chodzi w zadaniu, niestety nie starczyło mi czasu przed umówionym deadline'm
niestety również przeliczyłem się czasowo z testami są one nie ruszone, jeśli byłaby możliwość dosłałbym  wersje z testami do 2 dni.

(  w pliku application properties należy podać swoj kluczy prywatny do Striple w miejscu "stripe.apikey", aplikacja  oparta jest na niejako "porttable" bazie danych H2 )
