package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String sted;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double prisPerDag;
    ArrayList<Udflugt> udflugter = new ArrayList<>();
    ArrayList<Hotel> hoteller = new ArrayList<>();

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPerDag) {
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPerDag = prisPerDag;
    }
}
