package model;

import java.util.ArrayList;

public class Ledsager {
    private String navn;
    private Tilmelding tilmelding;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Ledsager(String navn, Tilmelding tilmelding) {
        this.navn = navn;
        this.tilmelding = tilmelding;
    }

    public Ledsager(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public Tilmelding getTilmelding() {
        return tilmelding;
    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public void setTilmelding(Tilmelding tilmelding) {
        this.tilmelding = tilmelding;
    }

    @Override
    public String toString() {
        return navn;
    }
}
