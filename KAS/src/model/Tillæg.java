package model;

public class Tillæg {
    private String navn;
    private double pris;

    public Tillæg(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    @Override
    public String toString() {
        return navn + " - " + pris + " kr.";
    }
}
