package storage;

import model.Hotel;
import model.Konference;

import java.util.ArrayList;

public abstract class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();



    // Konference
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }
    public static void storeKonferencer(Konference konference) {
        konferencer.add(konference);
    }

    // Hotel
    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }
    public static void storeHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

}
