package model;

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


    public double samletPris() {
        double pris = 0;
        /** TODO
         * Do the mathing
         * I liked the part where Oliver said "Its mathing time" and then mathed all over the method
         */

        return pris;
    }
}
