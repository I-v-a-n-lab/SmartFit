
package smartfit.servis;

import java.util.ArrayList;

import smartfit.model.Osoba;
import smartfit.model.Clan;
import smartfit.model.Trener;
import smartfit.model.Trening;
import smartfit.model.Rezervacija;
import smartfit.model.Clanarina;

public class Teretana {

    // Liste za cuvanje podataka
    private ArrayList<Osoba> osobe;
    private ArrayList<Clan> clanovi;
    private ArrayList<Trener> treneri;
    private ArrayList<Trening> treninzi;
    private ArrayList<Rezervacija> rezervacije;

    // Konstruktor
    public Teretana() {
        osobe = new ArrayList<>();
        clanovi = new ArrayList<>();
        treneri = new ArrayList<>();
        treninzi = new ArrayList<>();
        rezervacije = new ArrayList<>();
    }

    // Dodavanje clana
    public void dodajClana(Clan clan) {

        if (clan == null) {
            throw new IllegalArgumentException(
                "Clan ne moze biti null!");
        }

        for (Clan postojeci : clanovi) {
            if (postojeci.getBrojClanskeKarte()
                    .equals(clan.getBrojClanskeKarte())) {
                throw new IllegalArgumentException(
                    "Clan sa ovim brojem clanske karte vec postoji!");
            }
        }

        clanovi.add(clan);
        osobe.add(clan);
    }

    // Dodavanje trenera
    public void dodajTrenera(Trener trener) {

        if (trener == null) {
            throw new IllegalArgumentException(
                "Trener ne moze biti null!");
        }

        treneri.add(trener);
        osobe.add(trener);
    }

    // Dodavanje treninga
    public void dodajTrening(Trening trening) {

        if (trening == null) {
            throw new IllegalArgumentException(
                "Trening ne moze biti null!");
        }

        if (!treneri.contains(trening.getTrener())) {
            throw new IllegalArgumentException(
                "Trener nije registrovan u teretani!");
        }

        treninzi.add(trening);
    }

    // Kreiranje rezervacije
    public void napraviRezervaciju(Clan clan, Trening trening) {

        if (clan == null || trening == null) {
            throw new IllegalArgumentException(
                "Clan i trening moraju biti izabrani!");
        }

        if (!clanovi.contains(clan)) {
            throw new IllegalArgumentException(
                "Clan nije registrovan u teretani!");
        }

        if (!treninzi.contains(trening)) {
            throw new IllegalArgumentException(
                "Trening ne postoji u sistemu!");
        }

        // Provera da li clan vec ima rezervaciju za isti trening
        for (Rezervacija postojeca : rezervacije) {
            if (postojeca.getClan() == clan
                    && postojeca.getTrening() == trening) {
                throw new IllegalArgumentException(
                    "Clan je vec rezervisao ovaj trening!");
            }
        }

        // Provera maksimalnog broja ucesnika
        int brojRezervacija = 0;

        for (Rezervacija postojeca : rezervacije) {
            if (postojeca.getTrening() == trening) {
                brojRezervacija++;
            }
        }

        if (brojRezervacija >= trening.getMaksimalanBrojUcesnika()) {
            throw new IllegalArgumentException(
                "Trening je popunjen!");
        }

        rezervacije.add(new Rezervacija(clan, trening));
    }
    
 // Dodavanje prethodno sacuvane rezervacije
    public void dodajSacuvanuRezervaciju(Rezervacija rezervacija) {

        if (rezervacija == null) {
            throw new IllegalArgumentException(
                "Rezervacija ne moze biti null!");
        }

        if (!clanovi.contains(rezervacija.getClan())) {
            throw new IllegalArgumentException(
                "Clan rezervacije nije registrovan!");
        }

        if (!treninzi.contains(rezervacija.getTrening())) {
            throw new IllegalArgumentException(
                "Trening rezervacije ne postoji!");
        }

        for (Rezervacija postojeca : rezervacije) {

            if (postojeca.getClan() == rezervacija.getClan()
                    && postojeca.getTrening() == rezervacija.getTrening()) {

                throw new IllegalArgumentException(
                    "Ova rezervacija vec postoji!");
            }
        }

        int brojRezervacija = 0;

        for (Rezervacija postojeca : rezervacije) {

            if (postojeca.getTrening() == rezervacija.getTrening()) {
                brojRezervacija++;
            }
        }

        if (brojRezervacija
                >= rezervacija.getTrening().getMaksimalanBrojUcesnika()) {

            throw new IllegalArgumentException(
                "Trening je popunjen!");
        }

        rezervacije.add(rezervacija);
    }

    // Prikaz svih osoba - demonstracija polimorfizma
    public void prikaziSveOsobe() {

        if (osobe.isEmpty()) {
            System.out.println("Nema registrovanih osoba.");
            return;
        }

        for (Osoba osoba : osobe) {
            osoba.prikaziPodatke();
            System.out.println();
        }
    }

    // Prikaz svih clanova
    public void prikaziSveClanove() {

        if (clanovi.isEmpty()) {
            System.out.println("Nema registrovanih clanova.");
            return;
        }

        for (Clan clan : clanovi) {
            clan.prikaziPodatke();
            System.out.println();
        }
    }

    // Prikaz svih trenera
    public void prikaziSveTrenere() {

        if (treneri.isEmpty()) {
            System.out.println("Nema registrovanih trenera.");
            return;
        }

        for (Trener trener : treneri) {
            trener.prikaziPodatke();
            System.out.println();
        }
    }

    // Prikaz svih treninga
    public void prikaziSveTreninge() {

        if (treninzi.isEmpty()) {
            System.out.println("Nema dostupnih treninga.");
            return;
        }

        for (Trening trening : treninzi) {
            trening.prikaziTrening();
            System.out.println();
        }
    }

    // Prikaz svih rezervacija
    public void prikaziSveRezervacije() {

        if (rezervacije.isEmpty()) {
            System.out.println("Nema napravljenih rezervacija.");
            return;
        }

        for (Rezervacija rezervacija : rezervacije) {
            rezervacija.prikaziRezervaciju();
            System.out.println();
        }
    }

    // Naplata clanarine
    public void naplatiClanarinu(Clan clan, Clanarina clanarina) {

    	  if (clanarina == null) {
              throw new IllegalArgumentException(
                  "Clanarina nije izabrana!");
          }

          if (!clanovi.contains(clan)) {
              throw new IllegalArgumentException(
                  "Clan nije registrovan u teretani!");
          }

          // Dodeljivanje nove clanarine clanu
          clan.setClanarina(clanarina);

          System.out.println("\nUspesno obavljena naplata!");
          System.out.println("Clan: "
                  + clan.getIme() + " " + clan.getPrezime());
          System.out.println("Tip clanarine: " + clanarina.getTip());
          System.out.println("Iznos: "
                  + clanarina.izracunajCenu() + " RSD");
      }
     
    

    // Getteri za liste
    public ArrayList<Clan> getClanovi() {
        return new ArrayList<>(clanovi);
    }

    public ArrayList<Trener> getTreneri() {
        return new ArrayList<>(treneri);
    }

    public ArrayList<Trening> getTreninzi() {
        return new ArrayList<>(treninzi);
    }

    public ArrayList<Rezervacija> getRezervacije() {
        return new ArrayList<>(rezervacije);
    }
}
