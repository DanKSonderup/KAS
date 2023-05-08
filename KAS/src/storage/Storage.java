package storage;

import model.Konference;

import java.util.ArrayList;

public abstract class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();



    // Konference
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }
    public static void storeKonferencer(Konference konference) {
        konferencer.add(konference);
    }

    //
}
