
package smartfit.model;

import smartfit.interfejsi.Placanje;

public class Clanarina implements Placanje {

    // Cene clanarina
    private static final double CENA_MESECNE = 3000.0;
    private static final double CENA_TROMESECNE = 8000.0;
    private static final double CENA_GODISNJE = 28000.0;

    // Atributi
    private String tip;
    private double cena;
    private int trajanjeMeseci;

    // Konstruktor
    public Clanarina(String tip) {
        setTip(tip);
    }

    // Getter za tip
    public String getTip() {
        return tip;
    }

    // Setter za tip
    public void setTip(String tip) {

        if (tip == null || tip.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tip clanarine ne sme biti prazan!");
        }

        switch (tip.trim().toLowerCase()) {

            case "mesecna":
                this.tip = "Mesecna";
                this.cena = CENA_MESECNE;
                this.trajanjeMeseci = 1;
                break;

            case "tromesecna":
                this.tip = "Tromesecna";
                this.cena = CENA_TROMESECNE;
                this.trajanjeMeseci = 3;
                break;

            case "godisnja":
                this.tip = "Godisnja";
                this.cena = CENA_GODISNJE;
                this.trajanjeMeseci = 12;
                break;

            default:
                throw new IllegalArgumentException(
                    "Nepoznat tip clanarine!");
        }
    }

    // Getter za cenu
    public double getCena() {
        return cena;
    }

    // Getter za trajanje
    public int getTrajanjeMeseci() {
        return trajanjeMeseci;
    }

    // Implementacija metode iz interfejsa Placanje
    @Override
    public double izracunajCenu() {
        return cena;
    }

    // Prikaz podataka o clanarini
    public void prikaziClanarinu() {
        System.out.println("=== PODACI O CLANARINI ===");
        System.out.println("Tip: " + tip);
        System.out.println("Cena: " + izracunajCenu() + " RSD");
        System.out.println("Trajanje: " + trajanjeMeseci + " meseci");
    }
}
