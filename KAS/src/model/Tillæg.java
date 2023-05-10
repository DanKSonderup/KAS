package model;

public class Tillæg {
    private String navn;
    private double pris;
    private HotelAftale hotelAftale;

    public Tillæg(String navn, double pris, HotelAftale hotelAftale) {
        this.navn = navn;
        this.pris = pris;
        this.hotelAftale = hotelAftale;
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
