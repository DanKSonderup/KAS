package model;

import java.util.ArrayList;

public class HotelAftale {
    private String navn;
    private String lokation;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;
    private Konference konference;
    private ArrayList<Tillæg> tillæg = new ArrayList<>();


    public HotelAftale(String navn, double prisPrNatEnkeltVærelse, double prisPrDobbeltVærelse, String lokation, Konference konference) {
        this.navn = navn;
        this.prisPrNatEnkeltVærelse = prisPrNatEnkeltVærelse;
        this.prisPrDobbeltVærelse = prisPrDobbeltVærelse;
        this.lokation = lokation;
        this.konference = konference;
    }

    public String getNavn() {
        return navn;
    }

    public double getPrisPrNatEnkeltVærelse() {
        return prisPrNatEnkeltVærelse;
    }

    public double getPrisPrDobbeltVærelse() {
        return prisPrDobbeltVærelse;
    }

    public ArrayList<Tillæg> getTillæg() {
        return tillæg;
    }

    public String getLokation() {
        return lokation;
    }

    public Konference getKonference() {
        return konference;
    }
    public void createTillæg(Tillæg tillægInput) {
        tillæg.add(tillægInput);
    }

    @Override
    public String toString() {
        return konference + " " + navn + " " + lokation + " " + prisPrNatEnkeltVærelse + " " + prisPrDobbeltVærelse + " " + tillæg;
    }
}
