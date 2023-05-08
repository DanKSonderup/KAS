package controller;

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
}
