# Egységtesztek kiegészítése, kódfedettség mérése

## Kezdőállapot

A kiválasztott projekt már kiinduló állapotban rendelkezik egységtesztekkel. Feladatunkban a simple mappára fókuszálunk, amely a Commons RDF API egyszerű implementációja, a meglevő tesztek pedig ennek megfelelően sokszor az API tesztjeire épülnek, amik úgy vannak megírva, hogy minden implementáción lefussanak. 

A kódfedettség mérésére a JaCoCo-t fogjuk használni, amelyet gyakorlaton megismerhettünk.
A kódfedettség a munkánk kezdetekor már eleve 84%-os volt, tehát mondhatjuk, hogy elég jó, a missed branches ehhez képest arányaiban nagyobb, mindössze 71%-os a fedettsége.

![](start.png)

## Fedettség növelése

### LiteralImpl

Ebben az osztályban azt teszteltük, hogy a konstruktorok dobnak-e kivételt, ebből az egyik exception-t nem tudtuk elérni, Objects.requireNonNull() miatt előbb kapunk NullPointerException-t.

### DatasetGraphView

Ennek az osztálynak sok függvénye nem volt meghívva eredetileg, ezekhez új teszteket hoztam létre. Ezek a tesztek eléggé magától értetődőek (hármas hozzáadása és eltávolítása, egyben valamint külön, stb), ezért nem részletezném őket.

### 

## Végső állapot