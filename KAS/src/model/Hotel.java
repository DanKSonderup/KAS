package model;

import java.util.ArrayList;

public class Hotel {

    private String navn;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;
    ArrayList<Tillæg> tillæg = new ArrayList<>();


    public Hotel(String navn, double prisPrNatEnkeltVærelse, double prisPrDobbeltVærelse) {
        this.navn = navn;
        this.prisPrNatEnkeltVærelse = prisPrNatEnkeltVærelse;
        this.prisPrDobbeltVærelse = prisPrDobbeltVærelse;
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
}
