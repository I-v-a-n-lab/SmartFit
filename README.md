
# SmartFit

SmartFit je konzolna Java aplikacija namenjena za upravljanje teretanom. Omogućava evidenciju članova i trenera, upravljanje članarinama, organizaciju treninga i rezervaciju termina.

Aplikacija koristi JSON fajl za čuvanje podataka, a zahvaljujući Docker podršci može se pokretati na različitim operativnim sistemima bez zasebne instalacije Java i Maven okruženja.

## Funkcionalnosti

- Evidencija i upravljanje članovima teretane.

- Evidencija i upravljanje trenerima.

- Upravljanje članarinama i naplata članarine.

- Kreiranje i pregled treninga.

- Rezervacija termina za trening.

- Prikaz podataka o članovima, trenerima i treninzima.

- Čuvanje i učitavanje podataka pomoću JSON fajla.

- Automatsko čuvanje podataka prilikom izmena i izlaska iz aplikacije.
## Tehnologije
-Java 17 – programski jezik.

-Maven – upravljanje zavisnostima i izgradnja projekta.

-Gson – serijalizacija i deserijalizacija JSON podataka.

-Docker – kontejnerizacija i prenosivost aplikacije.

-Git i GitHub – kontrola verzija i čuvanje izvornog koda.

-Eclipse IDE – razvojno okruženje.

## Pokretanje

###Preduslovi
-Java JDK 17 – potrebno za kompajliranje i pokretanje Java aplikacije.

-Apache Maven – potrebno za upravljanje zavisnostima i izgradnju projekta.

-Git – potreban za kloniranje repozitorijuma (alternativno se projekat može preuzeti kao ZIP arhiva).

-Eclipse IDE – opciono, za pregled i izmenu izvornog koda.

Nakon instalacije Java JDK-a i Maven-a, potrebno je proveriti da li su pravilno podešeni u sistemskim promenljivama PATH.

Ispravnost instalacije može se proveriti komandama:
```bash
java -version
mvn -version
````

###1.Preuzimanje projekta
Klonirati repozitorijum i ući u direktorijum projekta:
````bash
git clone https://github.com/I-v-a-n-lab/SmartFit.git
cd SmartFit
````
###2. Izgradnja projekta
U glavnom direktorijumu projekta izvršiti komandu:
````bash
mvn clean package
````
Maven će automatski preuzeti potrebne zavisnosti, kompajlirati izvorni kod i napraviti izvršni JAR fajl.

Nakon uspešne izgradnje, izvršni fajl nalazi se na putanji:
target/smartfit-1.0.0.jar
###3. Pokretanje aplikacije
Aplikacija se pokreće sledećom komandom:
````bash
java -jar target\smartfit-1.0.0.jar
````
## Pokretanje aplikacije pomoću Docker-a

SmartFit može da se pokrene pomoću Docker-a, bez potrebe za ručnim instaliranjem Java okruženja.

### Preduslovi
- Instaliran Docker Desktop ili Docker Engine.
- Pokrenut Docker Engine.
- Git za kloniranje repozitorijuma ili preuzeta ZIP arhiva projekta.
- Internet konekcija prilikom prve izgradnje Docker image-a.
Nije potrebno zasebno instalirati Java JDK, Maven ili Eclipse IDE.
### 1. Preuzimanje projekta

```bash
git clone https://github.com/I-v-a-n-lab/SmartFit.git
cd SmartFit
```
### 2. Izgradnja Docker image-a

```bash
docker build -t smartfit:1.0 .
```
Tokom ovog procesa Docker automatski:
- Preuzima Maven i Java 17 razvojno okruženje.
- Kompajlira Java izvorni kod.
- Preuzima potrebne Maven zavisnosti.
- Kreira izvršni JAR fajl.
- Priprema završni image sa Java 17 runtime okruženjem.
Nije potrebno prethodno izvršavati mvn clean package.
### 3. Kreiranje trajnog volumena

```bash
docker volume create smartfit-data
```

### 4. Pokretanje aplikacije

```bash
docker run --rm -it -v smartfit-data:/app/data smartfit:1.0
```
Nakon pokretanja prikazuje se glavni meni SmartFit aplikacije u terminalu.
### Čuvanje podataka

SmartFit koristi JSON fajl data/smartfit.json za čuvanje podataka.

Prilikom pokretanja u Docker-u, direktorijum /app/data povezuje se sa trajnim Docker volumenom smartfit-data.

To omogućava da:
- Podaci ostanu sačuvani nakon izlaska iz aplikacije.
- Podaci ostanu sačuvani nakon uklanjanja Docker kontejnera.
- Aplikacija učita prethodno sačuvane podatke prilikom sledećeg pokretanja.
Za ponovno pokretanje aplikacije koristi se ista docker run komanda.

Napomena: Docker volume je vezan za lokalni računar. Podaci se ne prenose automatski između različitih računara.
Kontejner se nakon izlaska automatski uklanja zahvaljujući opciji --rm, dok podaci ostaju sačuvani u Docker volumenu.
