package model;

public class Hotel {

    private String navn;
    private double prisPrNatEnkeltVærelse;
    private double prisPrDobbeltVærelse;

    public Hotel(String navn, double prisPrNatEnkeltVærelse, double prisPrDobbeltVærelse) {
        this.navn = navn;
        this.prisPrNatEnkeltVærelse = prisPrNatEnkeltVærelse;
        this.prisPrDobbeltVærelse = prisPrDobbeltVærelse;
    }
}
