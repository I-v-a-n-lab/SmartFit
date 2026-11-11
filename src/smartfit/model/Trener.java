
package smartfit.model;

public class Trener extends Osoba {

    private String specijalizacija;
    private int godineIskustva;

    // Konstruktor
    public Trener(String ime, String prezime, String email, String telefon,
                  String specijalizacija, int godineIskustva) {

        super(ime, prezime, email, telefon);

        setSpecijalizacija(specijalizacija);
        setGodineIskustva(godineIskustva);
    }

    // Getter i setter za specijalizaciju
    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(String specijalizacija) {
        if (specijalizacija == null || specijalizacija.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Specijalizacija ne sme biti prazna!");
        }

        this.specijalizacija = specijalizacija.trim();
    }

    // Getter i setter za godine iskustva
    public int getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(int godineIskustva) {
        if (godineIskustva < 0 || godineIskustva > 60) {
            throw new IllegalArgumentException(
                "Broj godina iskustva nije ispravan!");
        }

        this.godineIskustva = godineIskustva;
    }

    // Redefinisanje metode iz klase Osoba
    @Override
    public void prikaziPodatke() {
        System.out.println("=== PODACI O TRENERU ===");
        System.out.println("Ime: " + getIme());
        System.out.println("Prezime: " + getPrezime());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefon: " + getTelefon());
        System.out.println("Specijalizacija: " + specijalizacija);
        System.out.println("Godine iskustva: " + godineIskustva);
    }
}
