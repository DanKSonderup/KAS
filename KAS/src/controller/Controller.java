package controller;

import model.Hotel;
import model.Konference;
import model.Tillæg;
import model.Udflugt;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    /**
     * Opretter en konference
     * Pre: navn er ikke tomt, startDate <= endDDate, prisPerDag >= 0
     */
    public static Konference createKonference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, prisPrDag);
        Storage.storeKonferencer(konference);
        return konference;
    }

    public static ArrayList<Konference> getAlleKonferencer() {
        return Storage.getKonferencer();
    }

    /**
     * Opretter en udflugt
     * Pre: navn er ikke tomt, startDate <= endDDate, pris >= 0
     * Throws IllegalArgumentException, hvis dato er udenfor konferencedage
     */
    public static Udflugt createUdflugt(String navn, LocalDate dato, double pris, String lokation, boolean frokost, Konference konference) throws IllegalArgumentException {
        Udflugt udflugt = new Udflugt(navn, dato, pris, lokation, frokost, konference);
        return udflugt;
    }

    public static ArrayList<Udflugt> getAlleUdflugter(Konference konference) {
        return konference.getUdflugter();
    }

    // Hoteller

    public static Hotel createHotel(String navn, double prisNatEnkeltVærelse, double prisNatDobbeltVærelse, String lokation) {
        Hotel hotel = new Hotel(navn, prisNatEnkeltVærelse, prisNatDobbeltVærelse, lokation);
        Storage.storeHotel(hotel);
        return hotel;
    }

    public static ArrayList<Hotel> getAllHoteller() {
        return Storage.getHoteller();
    }

    /**
     * Opretter et tillæg
     * Pre: beskrivelse er ikke tomt, hotel ikke null, pris >= 0
     */

    public static Tillæg createTillæg(Hotel hotel, String beskrivelse, double pris) {
        Tillæg tillæg = new Tillæg(beskrivelse, pris, hotel);
        return tillæg;
    }

    public static ArrayList<Tillæg> getAlleKøbsTillæg(Hotel hotel) {
        ArrayList<Tillæg> købsTillæg = new ArrayList<>();
        for (Tillæg købs : hotel.getTillæg()) {
            if (købs.getPris() > 0) {
                købsTillæg.add(købs);
            }
        }
        return købsTillæg;
    }

    public static ArrayList<Tillæg> getAlleInkluderedeTillæg(Hotel hotel) {
        ArrayList<Tillæg> inkluderedeTillæg = new ArrayList<>();
        for (Tillæg inkluderede : hotel.getTillæg()) {
            if (inkluderede.getPris() == 0) {
                inkluderedeTillæg.add(inkluderede);
            }
        }
        return inkluderedeTillæg;
    }
}
