
package smartfit.model;

import java.time.LocalDateTime;

public class Rezervacija {

    // Atributi klase
    private Clan clan;
    private Trening trening;
    private LocalDateTime datumRezervacije;

    // Konstruktor
    public Rezervacija(Clan clan, Trening trening) {

        setClan(clan);
        setTrening(trening);

        // Automatski se postavlja trenutni datum i vreme
        this.datumRezervacije = LocalDateTime.now();
    }

    // Getter i setter za clana
    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        if (clan == null) {
            throw new IllegalArgumentException(
                "Rezervacija mora imati clana!");
        }

        this.clan = clan;
    }

    // Getter i setter za trening
    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        if (trening == null) {
            throw new IllegalArgumentException(
                "Rezervacija mora imati trening!");
        }

        this.trening = trening;
    }

    // Getter za datum rezervacije
    public LocalDateTime getDatumRezervacije() {
        return datumRezervacije;
    }

    // Prikaz podataka o rezervaciji
    public void prikaziRezervaciju() {
        System.out.println("=== PODACI O REZERVACIJI ===");
        System.out.println("Clan: " + clan.getIme() + " " + clan.getPrezime());
        System.out.println("Broj clanske karte: " + clan.getBrojClanskeKarte());
        System.out.println("Trening: " + trening.getNaziv());
        System.out.println("Trener: " + trening.getTrener().getIme()
                           + " " + trening.getTrener().getPrezime());
        System.out.println("Termin: " + trening.getDatumIVreme());
        System.out.println("Datum rezervacije: " + datumRezervacije);
    }
}
