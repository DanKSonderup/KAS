package model;

import java.util.ArrayList;

public class HotelAftale {
    private String navn;
    private String lokation;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;
    private ArrayList<Tillæg> tillæg = new ArrayList<>();
    private ArrayList<HotelBooking> hotelBookings = new ArrayList<>();


    public HotelAftale(String navn, double prisPrNatEnkeltVærelse, double prisPrDobbeltVærelse, String lokation) {
        this.navn = navn;
        this.prisPrNatEnkeltVærelse = prisPrNatEnkeltVærelse;
        this.prisPrDobbeltVærelse = prisPrDobbeltVærelse;
        this.lokation = lokation;
    }

    public String getNavn() {
        return navn;
    }

    public double getPrisPrNatEnkeltVærelse() {
        return prisPrNatEnkeltVærelse;
    }

    public double getPrisPrDobbeltVærelse() {
        return prisPrDobbeltVærelse;
    }

    public ArrayList<Tillæg> getTillæg() {
        return tillæg;
    }

    public String getLokation() {
        return lokation;
    }

    public void createTillæg(Tillæg tillægInput) {
        tillæg.add(tillægInput);
    }

    @Override
    public String toString() {
        return navn + " " + lokation + " " + prisPrNatEnkeltVærelse + " " + prisPrDobbeltVærelse + " " + tillæg;
    }
}
