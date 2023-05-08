package model;

public class Tillæg {
    private String beskrivelse;
    private double pris;
    private HotelAftale hotelAftale;

    public Tillæg(String beskrivelse, double pris, HotelAftale hotelAftale) {
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.hotelAftale = hotelAftale;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getPris() {
        return pris;
    }

    public HotelAftale getHotel() {
        return hotelAftale;
    }
}
