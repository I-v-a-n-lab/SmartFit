
package smartfit.servis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import smartfit.model.Clan;
import smartfit.model.Clanarina;
import smartfit.model.Rezervacija;
import smartfit.model.Trener;
import smartfit.model.Trening;

public class DataManager {

    private static final String PUTANJA_FAJLA = "data/smartfit.json";

    private final Gson gson;

    public DataManager() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    // Cuvanje svih podataka u JSON fajl
    public void sacuvajPodatke(Teretana teretana) {

        if (teretana == null) {
            throw new IllegalArgumentException(
                "Teretana ne sme biti null!");
        }

        JsonPodaci podaci = new JsonPodaci();

        ArrayList<Clan> clanovi = teretana.getClanovi();
        ArrayList<Trener> treneri = teretana.getTreneri();
        ArrayList<Trening> treninzi = teretana.getTreninzi();
        ArrayList<Rezervacija> rezervacije = teretana.getRezervacije();

        // Cuvanje clanova
        for (Clan clan : clanovi) {

            ClanPodaci c = new ClanPodaci();

            c.ime = clan.getIme();
            c.prezime = clan.getPrezime();
            c.email = clan.getEmail();
            c.telefon = clan.getTelefon();
            c.brojClanskeKarte = clan.getBrojClanskeKarte();
            c.datumUclanjenja = clan.getDatumUclanjenja().toString();

            if (clan.getClanarina() != null) {
                c.tipClanarine = clan.getClanarina().getTip();
            }

            podaci.clanovi.add(c);
        }

        // Cuvanje trenera
        for (Trener trener : treneri) {

            TrenerPodaci t = new TrenerPodaci();

            t.ime = trener.getIme();
            t.prezime = trener.getPrezime();
            t.email = trener.getEmail();
            t.telefon = trener.getTelefon();
            t.specijalizacija = trener.getSpecijalizacija();
            t.godineIskustva = trener.getGodineIskustva();

            podaci.treneri.add(t);
        }

        // Cuvanje treninga
        for (Trening trening : treninzi) {

            TreningPodaci t = new TreningPodaci();

            t.naziv = trening.getNaziv();
            t.indeksTrenera = treneri.indexOf(trening.getTrener());
            t.datumIVreme = trening.getDatumIVreme().toString();
            t.maksimalanBrojUcesnika =
                    trening.getMaksimalanBrojUcesnika();

            if (t.indeksTrenera < 0) {
                throw new IllegalStateException(
                    "Trener treninga nije pronadjen!");
            }

            podaci.treninzi.add(t);
        }

        // Cuvanje rezervacija
        for (Rezervacija rezervacija : rezervacije) {

            RezervacijaPodaci r = new RezervacijaPodaci();

            r.indeksClana = clanovi.indexOf(rezervacija.getClan());
            r.indeksTreninga = treninzi.indexOf(rezervacija.getTrening());
            r.datumRezervacije =
                    rezervacija.getDatumRezervacije().toString();

            if (r.indeksClana < 0 || r.indeksTreninga < 0) {
                throw new IllegalStateException(
                    "Podaci o rezervaciji nisu ispravni!");
            }

            podaci.rezervacije.add(r);
        }

        // Kreiranje direktorijuma i upis JSON podataka
        try {
            Path putanja = Path.of(PUTANJA_FAJLA);

            Files.createDirectories(putanja.getParent());

            try (var writer = Files.newBufferedWriter(putanja)) {
                gson.toJson(podaci, writer);
            }

            System.out.println("Podaci su uspesno sacuvani.");

        } catch (IOException e) {
            throw new IllegalStateException(
                "Greska prilikom cuvanja podataka!", e);
        }
    }

    // Ucitavanje svih podataka iz JSON fajla
    public void ucitajPodatke(Teretana teretana) {

        if (teretana == null) {
            throw new IllegalArgumentException(
                "Teretana ne sme biti null!");
        }

        Path putanja = Path.of(PUTANJA_FAJLA);

        if (!Files.exists(putanja)) {
            System.out.println(
                "JSON fajl ne postoji. Pokrece se prazna teretana.");
            return;
        }

        try (var reader = Files.newBufferedReader(putanja)) {

            JsonPodaci podaci = gson.fromJson(reader, JsonPodaci.class);

            if (podaci == null) {
                System.out.println("JSON fajl je prazan.");
                return;
            }

            // Ucitavanje clanova
            for (ClanPodaci c : podaci.clanovi) {

                Clan clan = new Clan(
                    c.ime,
                    c.prezime,
                    c.email,
                    c.telefon,
                    c.brojClanskeKarte,
                    LocalDate.parse(c.datumUclanjenja)
                );

                if (c.tipClanarine != null) {
                    clan.setClanarina(new Clanarina(c.tipClanarine));
                }

                teretana.dodajClana(clan);
            }

            // Ucitavanje trenera
            for (TrenerPodaci t : podaci.treneri) {

                Trener trener = new Trener(
                    t.ime,
                    t.prezime,
                    t.email,
                    t.telefon,
                    t.specijalizacija,
                    t.godineIskustva
                );

                teretana.dodajTrenera(trener);
            }

            // Ucitavanje treninga
            ArrayList<Trener> treneri = teretana.getTreneri();

            for (TreningPodaci t : podaci.treninzi) {

                if (t.indeksTrenera < 0
                        || t.indeksTrenera >= treneri.size()) {
                    throw new IllegalStateException(
                        "Neispravan indeks trenera u JSON fajlu!");
                }

                Trener trener = treneri.get(t.indeksTrenera);

                Trening trening = new Trening(
                    t.naziv,
                    trener,
                    LocalDateTime.parse(t.datumIVreme),
                    t.maksimalanBrojUcesnika,
                    true
                );

                teretana.dodajTrening(trening);
            }

            // Ucitavanje rezervacija
            ArrayList<Clan> clanovi = teretana.getClanovi();
            ArrayList<Trening> treninzi = teretana.getTreninzi();

            for (RezervacijaPodaci r : podaci.rezervacije) {

                if (r.indeksClana < 0
                        || r.indeksClana >= clanovi.size()
                        || r.indeksTreninga < 0
                        || r.indeksTreninga >= treninzi.size()) {

                    throw new IllegalStateException(
                        "Neispravan indeks u rezervaciji!");
                }

                Clan clan = clanovi.get(r.indeksClana);
                Trening trening = treninzi.get(r.indeksTreninga);

                Rezervacija rezervacija = new Rezervacija(
                    clan,
                    trening,
                    LocalDateTime.parse(r.datumRezervacije)
                );

                teretana.dodajSacuvanuRezervaciju(rezervacija);
            }

            System.out.println("Podaci su uspesno ucitani.");

        } catch (IOException e) {
            throw new IllegalStateException(
                "Greska prilikom ucitavanja podataka!", e);
        }
    }

    // Pomocne klase za JSON strukturu

    private static class JsonPodaci {
        ArrayList<ClanPodaci> clanovi = new ArrayList<>();
        ArrayList<TrenerPodaci> treneri = new ArrayList<>();
        ArrayList<TreningPodaci> treninzi = new ArrayList<>();
        ArrayList<RezervacijaPodaci> rezervacije = new ArrayList<>();
    }

    private static class ClanPodaci {
        String ime;
        String prezime;
        String email;
        String telefon;
        String brojClanskeKarte;
        String datumUclanjenja;
        String tipClanarine;
    }

    private static class TrenerPodaci {
        String ime;
        String prezime;
        String email;
        String telefon;
        String specijalizacija;
        int godineIskustva;
    }

    private static class TreningPodaci {
        String naziv;
        int indeksTrenera;
        String datumIVreme;
        int maksimalanBrojUcesnika;
    }

    private static class RezervacijaPodaci {
        int indeksClana;
        int indeksTreninga;
        String datumRezervacije;
    }
}
