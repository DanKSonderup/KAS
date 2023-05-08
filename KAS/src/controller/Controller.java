package controller;

import model.*;
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

    /**
     * Opretter et hotel
     * Pre: navn er ikke tomt, lokation er ikke tomt, prisPrEnkelt/Dobbelt >= 0
     */

    public static Hotel createHotel(String navn, double prisNatEnkeltVærelse, double prisNatDobbeltVærelse) {
        Hotel hotel = new Hotel(navn, prisNatEnkeltVærelse, prisNatDobbeltVærelse);
        Storage.storeHotel(hotel);
        return hotel;

    public static HotelAftale createHotel(String navn, double prisNatEnkeltVærelse, double prisNatDobbeltVærelse, String lokation, Konference konference) {
        HotelAftale hotelAftale = new HotelAftale(navn, prisNatEnkeltVærelse, prisNatDobbeltVærelse, lokation, konference);
        Storage.storeHotel(hotelAftale);
        return hotelAftale;
    }

    public static ArrayList<HotelAftale> getAllHoteller() {
        return Storage.getHoteller();
    }

    /**
     * Opretter et tillæg
     * Pre: beskrivelse er ikke tomt, hotel ikke null, pris >= 0
     */

    public static Tillæg createTillæg(HotelAftale hotelAftale, String beskrivelse, double pris) {
        Tillæg tillæg = new Tillæg(beskrivelse, pris, hotelAftale);
        return tillæg;
    }

    public static ArrayList<Tillæg> getAlleKøbsTillæg(HotelAftale hotelAftale) {
        ArrayList<Tillæg> købsTillæg = new ArrayList<>();
        for (Tillæg købs : hotelAftale.getTillæg()) {
            if (købs.getPris() > 0) {
                købsTillæg.add(købs);
            }
        }
        return købsTillæg;
    }

    public static ArrayList<Tillæg> getAlleInkluderedeTillæg(HotelAftale hotelAftale) {
        ArrayList<Tillæg> inkluderedeTillæg = new ArrayList<>();
        for (Tillæg inkluderede : hotelAftale.getTillæg()) {
            if (inkluderede.getPris() == 0) {
                inkluderedeTillæg.add(inkluderede);
            }
        }
        return inkluderedeTillæg;
    }

    // Firma

    public static Firma createFirma(String navn, String telefonnummer) {
        Firma firma = new Firma(navn, telefonnummer);
        Storage.storeFirma(firma);
        return firma;
    }

    // Deltager

    public static Deltager createDeltager(
            String navn, String adresse, String by, String land, LocalDate ankomstdato,
             LocalDate afrejsedato, Konference konference, HotelAftale hotelAftale,
             boolean foredagsholder, String telefonnummer) {
        Deltager deltager = new Deltager(navn, adresse, by, land, ankomstdato, afrejsedato, konference, hotelAftale, foredagsholder, telefonnummer);
        //Storage.storeDeltager(deltager);
        return deltager;
    }

    



}
