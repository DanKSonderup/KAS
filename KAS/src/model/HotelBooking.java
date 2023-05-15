package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelBooking {
    private Tilmelding tilmelding;
    private ArrayList<Tillæg> valgteTillæg = new ArrayList<>();
    private HotelAftale hotelAftale;

    public HotelBooking(Tilmelding tilmelding, ArrayList<Tillæg> valgteTillæg, HotelAftale hotelAftale) {
        this.tilmelding = tilmelding;
        this.valgteTillæg = valgteTillæg;
        this.hotelAftale = hotelAftale;
    }

    public Tilmelding getTilmelding() {
        return tilmelding;
    }

    public HotelAftale getHotelAftale() {
        return hotelAftale;
    }

    public ArrayList<Tillæg> getValgteTillæg() {
        return valgteTillæg;
    }

    public double samletPris() {
        double pris = 0;

        double prisPrNat = 0;

        if (tilmelding.getLedsager() != null) {
            prisPrNat = hotelAftale.getPrisPrDobbeltVærelse();
        } else {
            prisPrNat = hotelAftale.getPrisPrNatEnkeltVærelse();
        }
        pris = (tilmelding.antalDage()) * prisPrNat;
        for (Tillæg tillæg : valgteTillæg) {
            pris += tillæg.getPris() * tilmelding.antalDage();
        }
        return pris;
    }

    public String getHotelAftaleNavn() {
        return hotelAftale.getNavn();
    }
}
