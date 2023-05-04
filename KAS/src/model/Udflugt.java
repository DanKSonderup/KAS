package model;

import java.time.LocalDate;

public class Udflugt {
    private String navn;
    private LocalDate dato;
    private double pris;
    private String lokation;
    private boolean frokost;

    public Udflugt(String navn, LocalDate dato, double pris, String lokation, boolean frokost) {
        this.navn = navn;
        this.dato = dato;
        this.pris = pris;
        this.lokation = lokation;
        this.frokost = frokost;
    }
}
