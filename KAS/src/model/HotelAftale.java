package model;

import storage.Storage;

import java.lang.reflect.Array;
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

    public ArrayList<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    @Override
    public String toString() {
        return navn + " " + lokation + " " + prisPrNatEnkeltVærelse + " " + prisPrDobbeltVærelse;
    }

    public ArrayList<String> GetHotelNavne() {
        // Finde alle unikke hotelnavne
        ArrayList<String> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftaler()) {
            if (!temp.contains(hotelAftale.getNavn())) {
                temp.add(hotelAftale.getNavn());
            }
        }
        return temp;
    }

    public ArrayList<HotelAftale> GetAlleHotelAftalerMedNavn(String hotelNavn) {
        // Find alle hotelaftaler med samme navn
        ArrayList<HotelAftale> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftaler()) {
            if (hotelAftale.getNavn().equals(hotelNavn)) {
                temp.add(hotelAftale);
            }
        }

        return temp;
    }

    public void addHotelBooking(HotelBooking hotelBooking) {
        hotelBookings.add(hotelBooking);
    }

}
