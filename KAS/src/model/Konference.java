package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String adresse;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double prisPerDag;

    // Aggregering 1--> 0..* Udflugter
    ArrayList<Udflugt> udflugter = new ArrayList<>();
    ArrayList<HotelAftale> hotelaftaler = new ArrayList<>();
    ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPerDag) {
        this.navn = navn;
        this.adresse = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPerDag = prisPerDag;
    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public void addHotelAftale(HotelAftale hotelAftale) {
        hotelaftaler.add(hotelAftale);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getPrisPerDag() {
        return prisPerDag;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public ArrayList<HotelAftale> getHotelaftaler() {
        return hotelaftaler;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    @Override
    public String toString() {
        return navn + " fra " + startDato + " til " + slutDato + " - Pris: " + prisPerDag;
    }



    public String visInfoUdflugter() {
        String udskrift = "Udflugter knyttet til konferencen " + navn + "\n";

        if (udflugter != null) {
            for (Udflugt u : udflugter) {
                udskrift += u.getNavn() + " " + u.getDato() + " Pris: " + u.getPris() + "\n";
                for (Ledsager ledsager: u.getLedsagere()) {
                    udskrift += "   " + ledsager.getNavn() + " (" + ledsager.getTilmelding().getDeltager().getNavn() + ")\n";
                }
            }
        }
        return udskrift;
    }
}
