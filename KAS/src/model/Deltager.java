package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deltager {
    private String navn;
    private String adresse;
    private String telefonnummer;

    // Tildeler hver deltager et 'ID'. Tænkte det kunne være en sjov måde at holde styr på f.eks. hvem der var d. 3 første tilmedlte
    private int id;
    private String by;
    private String land;
    private LocalDate ankomstdato;
    private LocalDate afrejsedato;
    private boolean foredagsholder;

    // En deltager behøver ikke være knyttet til et firma. Der skal derfor tages forbehold for ændringer
    private Firma firma;
    private Ledsager ledsager;
    private ArrayList<Konference> konferencer = new ArrayList<>();
    private HotelAftale hotelAftale;
    private Tilmelding tilmelding;
    private static int counter;

    // Har smidt alle datafelterne i constructeren med udgangspunkt fra tilmeldingsblanketten.
    // Jeg tager selvfølgelig forbehold for, at det ikke er alle der skal være der (f.eks. firma).

    public Deltager(String navn, String adresse, String telefonnummer,
                    String by, String land, LocalDate ankomstdato, LocalDate afrejsedato, boolean foredagsholder) {
        this.navn = navn;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.by = by;
        this.land = land;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.foredagsholder = foredagsholder;
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

    public ArrayList<Konference> getKonferencer() {
        return konferencer;
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

    public void addKonference(Konference konference) {
        konferencer.add(konference);
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
