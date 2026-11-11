
# SmartFit

SmartFit je konzolna Java aplikacija namenjena za upravljanje teretanom.

## Planirane funkcionalnosti

- Evidencija članova teretane
- Evidencija trenera
- Upravljanje članarinama
- Kreiranje i pregled treninga
- Rezervacija termina za trening
- Pretraga članova
- Evidencija uplata

## Tehnologije

- Java 17
- Eclipse IDE
- Git
- GitHub

## Pokretanje

1. Preuzeti ili klonirati repozitorijum.
2. Otvoriti projekat u Eclipse IDE-u.
3. Pokrenuti klasu `GlavnaAplikacija` kada bude implementirana.


## Pokretanje aplikacije pomoću Docker-a

SmartFit može da se pokrene pomoću Docker-a, bez potrebe za ručnim instaliranjem Java okruženja.

### Preduslovi

- Instaliran Docker Desktop (Windows) ili Docker Engine (Linux).
- Pokrenut Docker Engine.

### 1. Preuzimanje projekta

```bash
git clone https://github.com/I-v-a-n-lab/SmartFit.git
cd SmartFit
```

### 2. Izgradnja Maven aplikacije

```bash
mvn clean package
```

### 3. Izgradnja Docker image-a

```bash
docker build -t smartfit:1.0 .
```

### 4. Kreiranje trajnog volumena

```bash
docker volume create smartfit-data
```

### 5. Pokretanje aplikacije

```bash
docker run --rm -it -v smartfit-data:/app/data smartfit:1.0
```

### Čuvanje podataka

Aplikacija automatski čuva podatke u JSON fajlu `data/smartfit.json`.

Prilikom pokretanja u Docker-u, ovaj direktorijum se povezuje sa trajnim Docker volumenom `smartfit-data`.

Podaci ostaju sačuvani nakon zaustavljanja ili uklanjanja kontejnera.

Za ponovno pokretanje aplikacije koristi se ista `docker run` komanda.
