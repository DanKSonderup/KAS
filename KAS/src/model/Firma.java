package model;

import java.util.ArrayList;

public class Firma {
    private String navn;
    private String telefonnummer;
    ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Firma(String navn, String telefonnummer) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    @Override
    public String toString() {
        return navn + " " + " tlf: " + telefonnummer;
    }
}
