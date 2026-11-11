
package smartfit.model;

public abstract class Osoba {

    // Atributi klase
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    // Konstruktor
    public Osoba(String ime, String prezime, String email, String telefon) {
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setTelefon(telefon);
    }

    // Getter i setter za ime
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno!");
        }
        this.ime = ime.trim();
    }

    // Getter i setter za prezime
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        if (prezime == null || prezime.trim().isEmpty()) {
            throw new IllegalArgumentException("Prezime ne sme biti prazno!");
        }
        this.prezime = prezime.trim();
    }

    // Getter i setter za email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email adresa nije ispravna!");
        }
        this.email = email.trim();
    }

    // Getter i setter za telefon
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        if (telefon == null || !telefon.matches("[0-9+\\- ]{6,20}")) {
            throw new IllegalArgumentException("Broj telefona nije ispravan!");
        }
        this.telefon = telefon.trim();
    }

    // Apstraktna metoda
    public abstract void prikaziPodatke();
}
