package model;

public class Tillæg {
    private String beskrivelse;
    private double pris;
    private Hotel hotel;

    public Tillæg(String beskrivelse, double pris, Hotel hotel) {
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.hotel = hotel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getPris() {
        return pris;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
