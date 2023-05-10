package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String adresse;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double prisPerDag;

    // Aggregering 1--> 0..* Udflugter
    ArrayList<Udflugt> udflugter = new ArrayList<>();
    ArrayList<HotelAftale> hoteller = new ArrayList<>();
    ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPerDag) {
        this.navn = navn;
        this.adresse = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPerDag = prisPerDag;
    }

    public void createUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getPrisPerDag() {
        return prisPerDag;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public ArrayList<HotelAftale> getHoteller() {
        return hoteller;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    @Override
    public String toString() {
        return navn + " fra " + startDato + " til " + slutDato + " - Pris: " + prisPerDag;
    }
}
