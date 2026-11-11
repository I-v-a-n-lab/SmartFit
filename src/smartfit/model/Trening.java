
package smartfit.model;

import java.time.LocalDateTime;

public class Trening {

    // Atributi klase
    private String naziv;
    private Trener trener;
    private LocalDateTime datumIVreme;
    private int maksimalanBrojUcesnika;

    // Konstruktor
    public Trening(String naziv, Trener trener, LocalDateTime datumIVreme,
                   int maksimalanBrojUcesnika) {

        setNaziv(naziv);
        setTrener(trener);
        setDatumIVreme(datumIVreme);
        setMaksimalanBrojUcesnika(maksimalanBrojUcesnika);
    }

    // Getter i setter za naziv
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Naziv treninga ne sme biti prazan!");
        }

        this.naziv = naziv.trim();
    }

    // Getter i setter za trenera
    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        if (trener == null) {
            throw new IllegalArgumentException(
                "Trening mora imati dodeljenog trenera!");
        }

        this.trener = trener;
    }

    // Getter i setter za datum i vreme
    public LocalDateTime getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(LocalDateTime datumIVreme) {
        if (datumIVreme == null || datumIVreme.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                "Datum i vreme treninga nisu ispravni!");
        }

        this.datumIVreme = datumIVreme;
    }

    // Getter i setter za maksimalan broj ucesnika
    public int getMaksimalanBrojUcesnika() {
        return maksimalanBrojUcesnika;
    }

    public void setMaksimalanBrojUcesnika(int maksimalanBrojUcesnika) {
        if (maksimalanBrojUcesnika <= 0) {
            throw new IllegalArgumentException(
                "Broj ucesnika mora biti veci od nule!");
        }

        this.maksimalanBrojUcesnika = maksimalanBrojUcesnika;
    }

    // Prikaz podataka o treningu
    public void prikaziTrening() {
        System.out.println("=== PODACI O TRENINGU ===");
        System.out.println("Naziv: " + naziv);
        System.out.println("Trener: " + trener.getIme() + " " + trener.getPrezime());
        System.out.println("Datum i vreme: " + datumIVreme);
        System.out.println("Maksimalan broj ucesnika: " + maksimalanBrojUcesnika);
    }
}
