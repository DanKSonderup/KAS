package storage;

import model.Firma;
import model.HotelAftale;
import model.Konference;

import java.util.ArrayList;

public abstract class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<HotelAftale> HOTELLER = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();



    // Konference
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }
    public static void storeKonferencer(Konference konference) {
        konferencer.add(konference);
    }

    // Hotel
    public static ArrayList<HotelAftale> getHoteller() {
        return new ArrayList<>(HOTELLER);
    }
    public static void storeHotel(HotelAftale hotelAftale) {
        HOTELLER.add(hotelAftale);
    }


    // Firma
    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }
    public static void storeFirma(Firma firma) {
        firmaer.add(firma);
    }
}
