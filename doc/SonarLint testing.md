**SonarLint**

Statikus kód analízisre a SonarCloudot szerettük volna használni azonban hibába ütköztünk a buildeléskor. Nem tudtuk megoldani így SonarLintet használtuk Visual Studio Code-ból. Átvizsgálásra került a project a SonarLint segítségével és az alábbi hibákat fedezett fel a projectben:

·    AbstractRDFParser.java: Ez a bug téves risztás, hiszen a javítási lehetőség amit felkínál az szerepel a kódban.![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png) ![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

·    DatasetGraphView.java: bug-ot talált, nem lehet null értékkel visszatérni. ![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image005.png)Miden nem primitív típus nullable, azaz mindenhol lehet null értéket adni bármelyik változónak. Az Optional osztály csomagolóként szolgál a visszaadott objektumoknak. Az isPresent() meghívásával lehet megnézni, hogy milyen érték van benne, ha null akkor egy kivétellel elszáll a program. (nullPointerExeption)

·    DatasetImpl.java: ![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image007.png)Itt is az lenne a probléma, hogy mielőtt get() hívást csinálunk meg kell nézni, hogy mi van az Optional-ben. De az if feltételében ezt meg is tesszük.

 

**Jelzések, egyéb code smellek:**

·    DatasetImpl.java: Nem lehet olyan függvényünk, ami üres

·    BlankNodeImpl.java: if helyett if-else-then kapcsolatot kell volna alkalmazni

·    LiteralImpl.java: TODO kommenteket jelezte, illetve szólt, hogy kódrészleteket nem kellen kikommentezni

·    SimpleRDFTermFactory.java: @Deprecated azaz elavult annotáció a Javadoc-os taggel együtt használatos

·    IRIImpl.java: Null vizsgálatot nem használhatunk instanceof-al

·    AbstractRDFParser.java: nem kellene ThreadGroup-ot használni, „clone”-t nem lehetne túlterhelni, generikus kivételt nem szabadna dobni

·    stb.. 

Amiket lehetett kijavítottam, illetve volt olyan ami csak rontott volna a kódon illetve több hibához is vezetett volna, a mi feladatunk szempontjából nem releváns.

Ellenőrzés kérése:

![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image009.png)

![img](file:///C:/Users/juhas/AppData/Local/Temp/msohtmlclip1/01/clip_image011.png)

 