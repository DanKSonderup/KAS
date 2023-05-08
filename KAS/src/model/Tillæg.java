package model;

public class Tillæg {
    private String beskrivelse;
    private double pris;

    public Tillæg(String beskrivelse, double pris) {
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getPris() {
        return pris;
    }
}
