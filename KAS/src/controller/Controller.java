package controller;

import model.Hotel;
import model.Konference;
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
    public static Udflugt createUdflugt(String navn, LocalDate dato, double pris, String lokation, boolean frokost) throws IllegalArgumentException {
        Udflugt udflugt = new Udflugt(navn, dato, pris, lokation, frokost);
        return udflugt;
    }

    public static ArrayList<Udflugt> getAlleUdflugter(Konference konference) {
        return konference.getUdflugter();
    }

    public static Hotel createHotel(String navn, double prisNatEnkeltVærelse, double prisNatDobbeltVærelse) {
        Hotel hotel = new Hotel(navn, prisNatEnkeltVærelse, prisNatDobbeltVærelse);
        Storage.storeHoteller(hotel);
        return hotel;
    }

    public static ArrayList<Hotel> getAllHoteller() {
        return Storage
    }

}
