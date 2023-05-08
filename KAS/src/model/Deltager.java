package model;

import java.time.LocalDate;

public class Deltager {
    private String navn;
    private String adresse;
    private String by;
    private String land;
    private LocalDate ankomstdato;
    private LocalDate afrejsedato;

    // En deltager behøver ikke være knyttet til et firma. Der skal derfor tages forbehold for ændringer
    private Firma firma;
    private Ledsager ledsager;
    private Konference konference;
    private HotelAftale hotelAftale;
    private boolean foredagsholder;
    private String telefonnummer;
    private int id;

    // Tildeler hver deltager et 'ID'. Tænkte det kunne være en sjov måde at holde styr på f.eks. hvem der var d. 3 første tilmedlte
    private static int counter;

    // Har smidt alle datafelterne i constructeren med udgangspunkt fra tilmeldingsblanketten.
    // Jeg tager selvfølgelig forbehold for, at det ikke er alle der skal være der (f.eks. firma).

    public Deltager(String navn, String adresse, String by, String land, LocalDate ankomstdato,
                    LocalDate afrejsedato, Konference konference, boolean foredagsholder, String telefonnummer) {
        this.navn = navn;
        this.adresse = adresse;
        this.by = by;
        this.land = land;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        // this.firma = firma;
        this.konference = konference; // this.ledsager = ledsager;
        // this.hotel = hotel;
        this.foredagsholder = foredagsholder;
        this.telefonnummer = telefonnummer;
        counter++;
        this.id = counter;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public LocalDate getAnkomstdato() {
        return ankomstdato;
    }

    public void setAnkomstdato(LocalDate ankomstdato) {
        this.ankomstdato = ankomstdato;
    }

    public LocalDate getAfrejsedato() {
        return afrejsedato;
    }

    public void setAfrejsedato(LocalDate afrejsedato) {
        this.afrejsedato = afrejsedato;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        this.konference = konference;
    }

    public HotelAftale getHotelAftale() {
        return hotelAftale;
    }

    public void setHotelAftale(HotelAftale hotelAftale) {
        this.hotelAftale = hotelAftale;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }


    public boolean isForedagsholder() {
        return foredagsholder;
    }

    public void setForedagsholder(boolean foredagsholder) {
        this.foredagsholder = foredagsholder;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getId() {
        return id;
    }

    public void addDeltager(Firma firma) {
        for (Deltager d : firma.deltagere) {
            if (d.getTelefonnummer().compareTo(this.telefonnummer) != 0) {
                firma.deltagere.add(this);
            }
        }
    }

    public double samletPris() {
        double pris = 0;

        return pris;
    }
}
