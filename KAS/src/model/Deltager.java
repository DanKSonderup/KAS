package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deltager {
    private String navn;
    private String adresse;
    private String telefonnummer;
    private int id;
    private String by;
    private String land;
    ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private static int counter;

    public Deltager(String navn, String adresse, String telefonnummer, String by, String land) {
        this.navn = navn;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.by = by;
        this.land = land;
        counter++;
        this.id = counter;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public int getId() {
        return id;
    }

    public String getBy() {
        return by;
    }

    public String getLand() {
        return land;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public double samletPris() {
        double pris = 0;

        return pris;
    }

    public Tilmelding findTilmeldingTilKonference(Konference konference) {
        for (Tilmelding tilmelding: tilmeldinger) {
            if (tilmelding.getKonference().equals(konference)) {
                return tilmelding;
            }
        }
        return null;
    }
}
