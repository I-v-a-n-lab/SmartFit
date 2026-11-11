
package smartfit.model;

import java.time.LocalDate;

public class Clan extends Osoba {

    private String brojClanskeKarte;
    private LocalDate datumUclanjenja;
    private Clanarina clanarina;

    // Konstruktor
    public Clan(String ime, String prezime, String email, String telefon,
                String brojClanskeKarte, LocalDate datumUclanjenja) {

        super(ime, prezime, email, telefon);

        setBrojClanskeKarte(brojClanskeKarte);
        setDatumUclanjenja(datumUclanjenja);
    }

    // Getter i setter za broj clanske karte
    public String getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(String brojClanskeKarte) {
        if (brojClanskeKarte == null || brojClanskeKarte.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Broj clanske karte ne sme biti prazan!");
        }

        this.brojClanskeKarte = brojClanskeKarte.trim();
    }

    // Getter i setter za datum uclanjenja
    public LocalDate getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(LocalDate datumUclanjenja) {
        if (datumUclanjenja == null || datumUclanjenja.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Datum uclanjenja nije ispravan!");
        }

        this.datumUclanjenja = datumUclanjenja;
    }
 // Getter za clanarinu
    public Clanarina getClanarina() {
        return clanarina;
    }

    // Setter za clanarinu
    public void setClanarina(Clanarina clanarina) {

        if (clanarina == null) {
            throw new IllegalArgumentException(
                "Clanarina ne sme biti prazna!");
        }

        this.clanarina = clanarina;
    }
    
    // Redefinisanje metode iz klase Osoba
    @Override
    public void prikaziPodatke() {
        System.out.println("=== PODACI O CLANU ===");
        System.out.println("Ime: " + getIme());
        System.out.println("Prezime: " + getPrezime());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefon: " + getTelefon());
        System.out.println("Broj clanske karte: " + brojClanskeKarte);
        System.out.println("Datum uclanjenja: " + datumUclanjenja);
        if (clanarina != null) {
            System.out.println("Clanarina: " + clanarina.getTip());
            System.out.println("Cena clanarine: "
                    + clanarina.izracunajCenu() + " RSD");
            System.out.println("Trajanje clanarine: "
                    + clanarina.getTrajanjeMeseci() + " meseci");
        } else {
            System.out.println("Clanarina: Nije dodeljena");
        }
    }
}
