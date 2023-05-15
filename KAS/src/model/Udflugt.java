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


    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public ArrayList<Ledsager> getLedsagere() {
        return ledsagere;
    }

    public void addLedsager(Ledsager ledsager) {
        ledsagere.add(ledsager);
    }

    @Override
    public String toString() {
        String frokostString = "";
        if (frokost) {
            frokostString = "Frokost inkl.";
        }
        return  navn + ", " +  lokation + " , d. " + dato + ", Pris: " + pris + " kr. "
                + frokostString;
    }
}
