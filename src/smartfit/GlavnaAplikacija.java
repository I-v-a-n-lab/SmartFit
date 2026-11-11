
package smartfit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import smartfit.model.Clan;
import smartfit.model.Clanarina;
import smartfit.model.Trener;
import smartfit.model.Trening;
import smartfit.servis.Teretana;
import smartfit.servis.DataManager;

public class GlavnaAplikacija {

	private Scanner scanner;
	private Teretana teretana;
	private DataManager dataManager;

    private static final DateTimeFormatter FORMAT_DATUMA_I_VREMENA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Konstruktor
    public GlavnaAplikacija() {
        scanner = new Scanner(System.in);
        teretana = new Teretana();
        dataManager = new DataManager();

        // Automatsko ucitavanje podataka
        dataManager.ucitajPodatke(teretana);
    }
    // Glavna metoda
    public static void main(String[] args) {
        GlavnaAplikacija aplikacija = new GlavnaAplikacija();
        aplikacija.pokreni();
    }

    // Glavni meni
    public void pokreni() {

        int izbor;

        do {
            System.out.println("\n================================");
            System.out.println("       SMARTFIT - TERETANA");
            System.out.println("================================");
            System.out.println("1. Dodavanje clana");
            System.out.println("2. Prikaz svih clanova");
            System.out.println("3. Dodavanje trenera");
            System.out.println("4. Prikaz svih trenera");
            System.out.println("5. Kreiranje treninga");
            System.out.println("6. Prikaz svih treninga");
            System.out.println("7. Pravljenje rezervacije");
            System.out.println("8. Prikaz svih rezervacija");
            System.out.println("9. Naplata clanarine");
            System.out.println("0. Izlaz iz programa");
            System.out.println("10. Prikaz svih osoba");
            System.out.println("================================");

            izbor = ucitajBroj("Izaberite opciju: ");

            switch (izbor) {
                case 1:
                    dodajClana();
                    
                    break;
                case 2:
                    teretana.prikaziSveClanove();
                    break;
                case 3:
                    dodajTrenera();
                    
                    break;
                case 4:
                    teretana.prikaziSveTrenere();
                    break;
                case 5:
                    kreirajTrening();
                    
                    break;
                case 6:
                    teretana.prikaziSveTreninge();
                    break;
                case 7:
                    napraviRezervaciju();
                    
                    break;
                case 8:
                    teretana.prikaziSveRezervacije();
                    break;
                case 9:
                    naplatiClanarinu();
                    
                    break;
                case 10:
                    teretana.prikaziSveOsobe();
                    break;
                case 0:
                	dataManager.sacuvajPodatke(teretana);
                    System.out.println("Hvala sto ste koristili SmartFit!");
                    break;
                default:
                    System.out.println("Nepostojeca opcija. Pokusajte ponovo.");
            }

        } while (izbor != 0);

        scanner.close();
    }

    // Dodavanje clana
    private void dodajClana() {

        try {
            System.out.println("\n--- DODAVANJE CLANA ---");

            String ime = ucitajTekst("Ime: ");
            String prezime = ucitajTekst("Prezime: ");
            String email = ucitajTekst("Email: ");
            String telefon = ucitajTekst("Telefon: ");
            String brojKarte = ucitajTekst("Broj clanske karte: ");
            LocalDate datum = ucitajDatum("Datum uclanjenja (yyyy-MM-dd): ");

            Clan clan = new Clan(
                    ime, prezime, email, telefon, brojKarte, datum);

            teretana.dodajClana(clan);
            dataManager.sacuvajPodatke(teretana);
            
            System.out.println("Clan je uspesno dodat!");

        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
        }
    }

    // Dodavanje trenera
    private void dodajTrenera() {

        try {
            System.out.println("\n--- DODAVANJE TRENERA ---");

            String ime = ucitajTekst("Ime: ");
            String prezime = ucitajTekst("Prezime: ");
            String email = ucitajTekst("Email: ");
            String telefon = ucitajTekst("Telefon: ");
            String specijalizacija = ucitajTekst("Specijalizacija: ");
            int iskustvo = ucitajBroj("Godine iskustva: ");

            Trener trener = new Trener(
                    ime, prezime, email, telefon,
                    specijalizacija, iskustvo);

            teretana.dodajTrenera(trener);
            dataManager.sacuvajPodatke(teretana);
            
            System.out.println("Trener je uspesno dodat!");

        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
        }
    }

    // Kreiranje treninga
    private void kreirajTrening() {

        try {
            if (teretana.getTreneri().isEmpty()) {
                System.out.println("Prvo morate dodati trenera.");
                return;
            }

            System.out.println("\n--- KREIRANJE TRENINGA ---");

            String naziv = ucitajTekst("Naziv treninga: ");

            System.out.println("\nDostupni treneri:");

            for (int i = 0; i < teretana.getTreneri().size(); i++) {
                Trener trener = teretana.getTreneri().get(i);

                System.out.println((i + 1) + ". "
                        + trener.getIme() + " " + trener.getPrezime()
                        + " - " + trener.getSpecijalizacija());
            }

            int izborTrenera = ucitajBroj("Izaberite trenera: ");

            if (izborTrenera < 1
                    || izborTrenera > teretana.getTreneri().size()) {
                System.out.println("Izabrani trener ne postoji.");
                return;
            }

            Trener trener = teretana.getTreneri().get(izborTrenera - 1);

            LocalDateTime datumIVreme =
                    ucitajDatumIVreme("Termin (yyyy-MM-dd HH:mm): ");

            int kapacitet =
                    ucitajBroj("Maksimalan broj ucesnika: ");

            Trening trening = new Trening(
                    naziv, trener, datumIVreme, kapacitet);

            teretana.dodajTrening(trening);
            dataManager.sacuvajPodatke(teretana);
            
            System.out.println("Trening je uspesno kreiran!");

        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
        }
    }

    // Pravljenje rezervacije
    private void napraviRezervaciju() {

        try {
            if (teretana.getClanovi().isEmpty()) {
                System.out.println("Nema registrovanih clanova.");
                return;
            }

            if (teretana.getTreninzi().isEmpty()) {
                System.out.println("Nema dostupnih treninga.");
                return;
            }

            System.out.println("\n--- PRAVLJENJE REZERVACIJE ---");
            System.out.println("Dostupni clanovi:");

            for (int i = 0; i < teretana.getClanovi().size(); i++) {
                Clan clan = teretana.getClanovi().get(i);

                System.out.println((i + 1) + ". "
                        + clan.getIme() + " " + clan.getPrezime()
                        + " - " + clan.getBrojClanskeKarte());
            }

            int izborClana = ucitajBroj("Izaberite clana: ");

            if (izborClana < 1
                    || izborClana > teretana.getClanovi().size()) {
                System.out.println("Izabrani clan ne postoji.");
                return;
            }

            System.out.println("\nDostupni treninzi:");

            for (int i = 0; i < teretana.getTreninzi().size(); i++) {
                Trening trening = teretana.getTreninzi().get(i);

                System.out.println((i + 1) + ". "
                        + trening.getNaziv()
                        + " - " + trening.getDatumIVreme());
            }

            int izborTreninga = ucitajBroj("Izaberite trening: ");

            if (izborTreninga < 1
                    || izborTreninga > teretana.getTreninzi().size()) {
                System.out.println("Izabrani trening ne postoji.");
                return;
            }

            Clan clan = teretana.getClanovi().get(izborClana - 1);
            Trening trening =
                    teretana.getTreninzi().get(izborTreninga - 1);

            teretana.napraviRezervaciju(clan, trening);
            dataManager.sacuvajPodatke(teretana);
            
            System.out.println("Rezervacija je uspesno napravljena!");

        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
        }
    }

    

    // Naplata clanarine
    private void naplatiClanarinu() {

        try {
            if (teretana.getClanovi().isEmpty()) {
                System.out.println("Nema registrovanih clanova.");
                return;
            }

            System.out.println("\n--- NAPLATA CLANARINE ---");
            System.out.println("Registrovani clanovi:");

            // Prikaz svih registrovanih clanova
            for (int i = 0; i < teretana.getClanovi().size(); i++) {

                Clan clan = teretana.getClanovi().get(i);

                System.out.println((i + 1) + ". "
                        + clan.getIme() + " "
                        + clan.getPrezime()
                        + " - " + clan.getBrojClanskeKarte());

                if (clan.getClanarina() != null) {
                    System.out.println("   Trenutna clanarina: "
                            + clan.getClanarina().getTip());
                } else {
                    System.out.println("   Trenutna clanarina: Nema");
                }
            }

            // Izbor clana
            int izborClana = ucitajBroj(
                    "\nIzaberite clana: ");

            if (izborClana < 1
                    || izborClana > teretana.getClanovi().size()) {

                System.out.println("Izabrani clan ne postoji.");
                return;
            }

            Clan clan = teretana.getClanovi().get(izborClana - 1);

            // Izbor tipa clanarine
            System.out.println("\nIzaberite tip clanarine:");
            System.out.println("1. Mesecna - 3000 RSD");
            System.out.println("2. Tromesecna - 8000 RSD");
            System.out.println("3. Godisnja - 28000 RSD");

            int izbor = ucitajBroj(
                    "Izaberite tip clanarine: ");

            String tip;

            switch (izbor) {
                case 1:
                    tip = "mesecna";
                    break;
                case 2:
                    tip = "tromesecna";
                    break;
                case 3:
                    tip = "godisnja";
                    break;
                default:
                    System.out.println(
                            "Nepostojeci tip clanarine.");
                    return;
            }

            Clanarina clanarina = new Clanarina(tip);

            // Dodela clanarine i obrada naplate
            teretana.naplatiClanarinu(clan, clanarina);
            dataManager.sacuvajPodatke(teretana);
            
            System.out.println("Clanarina je uspesno naplacena!");

        } catch (IllegalArgumentException e) {
            System.out.println("Greska: " + e.getMessage());
        }
    }


    // Pomocna metoda za unos teksta
    private String ucitajTekst(String poruka) {
        System.out.print(poruka);
        return scanner.nextLine().trim();
    }

    // Pomocna metoda za unos celog broja
    private int ucitajBroj(String poruka) {

        while (true) {
            System.out.print(poruka);
            String unos = scanner.nextLine();

            try {
                return Integer.parseInt(unos.trim());
            } catch (NumberFormatException e) {
                System.out.println("Unesite ispravan ceo broj.");
            }
        }
    }

    // Pomocna metoda za unos datuma
    private LocalDate ucitajDatum(String poruka) {

        while (true) {
            System.out.print(poruka);

            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Neispravan format. Primer: 2026-11-15");
            }
        }
    }

    // Pomocna metoda za unos datuma i vremena
    private LocalDateTime ucitajDatumIVreme(String poruka) {

        while (true) {
            System.out.print(poruka);

            try {
                return LocalDateTime.parse(
                        scanner.nextLine().trim(),
                        FORMAT_DATUMA_I_VREMENA);
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Neispravan format. Primer: 2026-11-15 18:30");
            }
        }
    }
}
