package model;

import java.util.ArrayList;

public class Firma {
    private String navn;
    private String telefonnummer;
    ArrayList<Deltager> deltagere = new ArrayList<>();

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

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
