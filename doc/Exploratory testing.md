# Exploratory testing

Hogy ízelítőt kapjunk az API, illetve az általa definiált interfészek egy implementációjának (*simple* modul) működéséről, freestyle tesztelési módszert alkalmazzuk. A teszteseteket nem tervezzük meg előre, hanem on-the-fly írunk a könyvtár osztályait használó kódot és bővítjük további részletekkel, amik menet közben jutnak eszünkbe, ilyen módon próbálva feszegetni a rendszer határait, feltárni korlátait, kiaknázni az általa nyújtott lehetőségeket.

Első körben felfedezzük, hogy lehet dataseteket és négyeseket létrehozni, illetve utóbbiakat előbbiekhez hozzáadni, majd lekérdezni.

Hozzáadott személyek:

- Tony Soprano
- Tony Montana
- Christian Moltisanti
- Christian Bale

A hozzáadott kapcsolatok kifejezik az egyes személyek kapcsolatát a családi- illetve keresztnevük literáljaival, illetve hogy Tony Soprano ismeri (foaf:knows) Christian Moltisantit. A lekérdezések és műveletek ezen az adathalmazon lettek végrehajtva.

**Az alábbi műveleteket próbáltam ki:**

1. a fenti kapcsolatokat kifejező négyesek hozzáadása a datasethez
   iterálás a dataseten
2. szűrés a "Tony" literál object-et tartalmazó négyesekre a datasetben
3. az előbbi szűréssel megtalált négyesek subjectje  (személy) alapján az adott Tony-k családnevének kikeresése
4. ugyanezen négyesek hármas-reprezentációjának hozzáadása a graph objektumhoz
   iterálás a graphon
5. ugyanannyi állítás lett-e a gráfban, mint a datasetben így
6. bent van-e a dataset-ben néhány hozzáadott négyes, 
   illetve a graph-ben néhány hozzáadott hármas, 
   egyikben sincs-e benne nem létező állítás?
7. szűrés a gráfban a "Tony" literál object-et tartalmazó triple-ekre

