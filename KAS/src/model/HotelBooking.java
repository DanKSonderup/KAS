package model;

import java.util.ArrayList;

public class HotelBooking {
    private int antalNætter;
    private ArrayList<Tillæg> valgteTillæg = new ArrayList<>();

    public HotelBooking(int antalNætter, ArrayList<Tillæg> valgteTillæg) {
        this.antalNætter = antalNætter;
        this.valgteTillæg = valgteTillæg;
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
