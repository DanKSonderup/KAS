package storage;


import model.Deltager;
import model.HotelAftale;
import model.Firma;
import model.HotelAftale;
import model.Konference;

import java.util.ArrayList;

public abstract class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<HotelAftale> hotelAftaler = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();


    // Konference
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }
    public static void storeKonferencer(Konference konference) {
        konferencer.add(konference);
    }

    // Hotel
    public static ArrayList<HotelAftale> getHotelAftaler() {
        return new ArrayList<>(hotelAftaler);
    }
    public static void storeHotelAftale(HotelAftale hotelAftale) {
        hotelAftaler.add(hotelAftale);
    }
    // Firma
    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }
    public static void storeFirma(Firma firma) {
        firmaer.add(firma);
    }

    // Deltager
    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }
    public static void storeDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }
}
