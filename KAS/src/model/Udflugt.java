package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private LocalDate dato;
    private double pris;
    private String lokation;
    private boolean frokost;
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();

    public Udflugt(String navn, LocalDate dato, double pris, String lokation, boolean frokost) {
        this.navn = navn;
        this.dato = dato;
        this.pris = pris;
        this.lokation = lokation;
        this.frokost = frokost;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getLokation() {
        return lokation;
    }

    public void setLokation(String lokation) {
        this.lokation = lokation;
    }

    public boolean isFrokost() {
        return frokost;
    }

    public void setFrokost(boolean frokost) {
        this.frokost = frokost;
    }
}
