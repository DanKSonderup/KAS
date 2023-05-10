package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private boolean foredragsholder;
    private Firma firma;
    private Deltager deltager;
    private Ledsager ledsager;
    private Konference konference;
    private HotelBooking hotelBooking;

    public Tilmelding(LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder,
                      Deltager deltager, Konference konference) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.foredragsholder = foredragsholder;
        this.deltager = deltager;
        this.konference = konference;
    }

    public Ledsager createLedsager(String navn) {
        Ledsager ledsager1 = new Ledsager(navn, this);
        return ledsager1;
    }

    public void addFirma(Firma firma) {
        this.firma = firma;
    }

    public HotelBooking createHotelBooking(ArrayList<Tillæg> valgteTillæg, HotelAftale hotelAftale) {
        HotelBooking hotelBooking1 = new HotelBooking(this, valgteTillæg, hotelAftale);
        return hotelBooking1;
    }
}
