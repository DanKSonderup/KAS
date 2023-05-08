package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String sted;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double prisPerDag;

    // Komposition 1--> 0..* Udflugter
    ArrayList<Udflugt> udflugter = new ArrayList<>();
    ArrayList<Hotel> hoteller = new ArrayList<>();

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPerDag) {
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPerDag = prisPerDag;
    }

    public String getNavn() {
        return navn;
    }

    public String getSted() {
        return sted;
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

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    @Override
    public String toString() {
        return navn + " fra " + startDato + " til " + slutDato + " - Pris: " + prisPerDag;
    }
}
