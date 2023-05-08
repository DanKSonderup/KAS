package model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String lokation;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;
    ArrayList<Tillæg> tillæg = new ArrayList<>();


    public Hotel(String navn, double prisPrNatEnkeltVærelse, double prisPrDobbeltVærelse, String lokation) {
        this.navn = navn;
        this.prisPrNatEnkeltVærelse = prisPrNatEnkeltVærelse;
        this.prisPrDobbeltVærelse = prisPrDobbeltVærelse;
        this.lokation = lokation;
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
}
