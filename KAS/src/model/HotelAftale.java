package model;

import storage.Storage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HotelAftale {
    private String navn;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;
    private String lokation;
    private String inkluderedeTillæg = "";
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

    public void createTillæg(Tillæg tillægInput) {
        tillæg.add(tillægInput);
    }

    public ArrayList<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void addInkluderetTillæg(String inkluderedeTillæg) {
        this.inkluderedeTillæg += inkluderedeTillæg;
    }


    public void addHotelBooking(HotelBooking hotelBooking) {
        hotelBookings.add(hotelBooking);
    }

    @Override
    public String toString() {
        return navn + " " + lokation + " " + prisPrNatEnkeltVærelse + " " + prisPrDobbeltVærelse + (inkluderedeTillæg.length() == 0 ? "" : " inkl: " + inkluderedeTillæg);
    }

}
