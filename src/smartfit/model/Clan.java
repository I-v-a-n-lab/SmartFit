
package smartfit.model;

import java.time.LocalDate;

public class Clan extends Osoba {

    private String brojClanskeKarte;
    private LocalDate datumUclanjenja;

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
    }
}
