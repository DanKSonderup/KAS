package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    /**
     * Opretter en konference
     * Pre: navn er ikke tomt, startDate <= endDDate, prisPerDag >= 0
     */
    public static Konference createKonference(String navn, String adresse, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        Konference konference = new Konference(navn, adresse, startDato, slutDato, prisPrDag);
        Storage.storeKonference(konference);
        return konference;
    }

    /**
     * Henter alle konferencer fra storage og returnere en ArrayList med dem
     */
    public static ArrayList<Konference> getAlleKonferencer() {
        return Storage.getKonferencer();
    }

    /**
     * Opretter en udflugt
     * Pre: navn er ikke tomt, startDate <= endDDate, pris >= 0
     * Throws IllegalArgumentException, hvis dato er udenfor konferencedage
     */
    public static Udflugt createUdflugt(Konference konference, String navn, LocalDate dato, double pris, String lokation, boolean frokost) throws IllegalArgumentException {
        Udflugt uf = new Udflugt(navn, dato, pris, lokation, frokost);
        konference.addUdflugt(uf);
        return uf;
    }


    /**
     * Opretter et hotel
     * Pre: navn er ikke tomt, lokation er ikke tomt, prisPrEnkelt/Dobbelt >= 0
     */
    public static HotelAftale createHotelAftale(String navn, double prisNatEnkeltVærelse, double prisNatDobbeltVærelse, String lokation, Konference konference) {
        HotelAftale h1 = new HotelAftale(navn, prisNatEnkeltVærelse, prisNatDobbeltVærelse, lokation);
        konference.addHotelAftale(h1);
        Storage.storeHotelAftale(h1);
        return h1;
    }

    /**
     * Henter alle hotelaftaler fra storage og returnerer en liste med dem
     */
    public static ArrayList<HotelAftale> getAllHotelAftaler() {
        return Storage.getHotelAftale();
    }

    /**
     * Opretter et tillæg
     * Pre: beskrivelse er ikke tomt, hotel ikke null, pris >= 0
     */
    public static void createTillæg(HotelAftale hotelAftale, String navn, double pris) {
        hotelAftale.createTillæg(new Tillæg(navn,pris));
    }


    /**
     * Henter alle udflugter fra en konference, mellem to datoer, og returnerer en liste med dem
     * Pre: at d1 og d2 er mellem konferencens datoer.
     */
    public static ArrayList<Udflugt> getUdflugterForKonferenceMellem(Konference konference, LocalDate d1, LocalDate d2) {
        ArrayList<Udflugt> udflugter = new ArrayList<>();
        for (Udflugt udflugt: konference.getUdflugter()) {
            if (!udflugt.getDato().isBefore(d1) && !udflugt.getDato().isAfter(d2)) {
                udflugter.add(udflugt);
            }
        }
        return udflugter;
    }


    /**
     * Opretter en tilmelding
     * Pre: Der eksisterer en konference, ankomstDato & afrejseDato skal være indenfor konferencens datoer
     */
    public static Tilmelding createTilmelding(Konference konference, Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder) {
        Tilmelding t1 = new Tilmelding(ankomstDato,afrejseDato,foredragsholder, deltager, konference);
        konference.addTilmelding(t1);
        deltager.addTilmelding(t1);
        Storage.storeDeltager(deltager);
        return t1;
    }

    /**
     * Opretter en HotelBooking.
     * Pre: der eksisterer en tilmelding.
     */
    public static HotelBooking createHotelBooking(Tilmelding tilmelding, ArrayList<Tillæg> valgteTillæg, HotelAftale hotelAftale) {
        HotelBooking h1 = new HotelBooking(tilmelding, valgteTillæg, hotelAftale);
        tilmelding.addHotelBooking(h1);
        hotelAftale.addHotelBooking(h1);
        return h1;
    }

    /**
     * Tilføjer et firma til en tilmelding.
     */
    public static void addFirmaTilTilmelding(Firma firma, Tilmelding tilmelding) {
        tilmelding.addFirma(firma);
        firma.addTilmelding(tilmelding);
        Storage.storeFirma(firma);
    }

    /**
     * Henter alle tilmeldinger til en specifik konference og returnerer en liste med dem.
     */
    public static ArrayList<Tilmelding> getAlleTilmeldinger(Konference konference) {
        ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
        for (Tilmelding t : konference.getTilmeldinger()) {
            tilmeldinger.add(t);
        }
        return tilmeldinger;
    }

    /**
     * Henter alle unikke deltagere som ikke er på konference og returnerer en liste med dem.
     */
    public static ArrayList<Deltager> getAlleUnikkeDeltagere(Konference konferenceInput) {
        ArrayList<Deltager> unikkeDeltagere = new ArrayList<>();
        for (Konference konference: Storage.getKonferencer()) {
            for (Tilmelding tilmelding: konference.getTilmeldinger()) {
                if (!unikkeDeltagere.contains(tilmelding.getDeltager()) && !tilmelding.getKonference().equals(konferenceInput)) {
                    unikkeDeltagere.add(tilmelding.getDeltager());
                }
            }
        }
        return unikkeDeltagere;
    }


    /**
     * Opretter en ledsager og tilføjer til en tilmelding
     * Pre: Der eksisterer en tilmelding
     */
    public static Ledsager createLedsager(String navn, Tilmelding tilmelding) {
        Ledsager l1 = new Ledsager(navn, tilmelding);
        tilmelding.setLedsager(l1);
        l1.setTilmelding(tilmelding);
        return l1;
    }

    /**
     * Tilføjer udflugter til en ledsager.
     */
    public static void addUdflugterTilLedsager(Ledsager ledsager, ArrayList<Udflugt> udflugter) {
        for (Udflugt udflugt: udflugter) {
            ledsager.addUdflugt(udflugt);
            udflugt.addLedsager(ledsager);
        }
    }

    /**
     * Returnerer en String med oplysninger om alle hoteller og deltagere, som har besøgt hotellet.
     */
    public static String visHotelOgDeltagerInfo() {
        String udskrift = "";
        for (String s : getUnikkeHotelNavne()) {
            ArrayList<HotelAftale> h1 = GetAlleHotelAftalerMedNavn(s.trim());
            udskrift += s + "\n";
            for (HotelAftale hotelAftale : h1) {
                for (HotelBooking hotelBooking : hotelAftale.getHotelBookings()) {
                    udskrift += "   " + hotelBooking.getTilmelding().getDeltager().getNavn();
                    if (hotelBooking.getTilmelding().getLedsager() != null) {
                        udskrift += " (" + hotelBooking.getTilmelding().getLedsager().getNavn() + ")";
                    }
                        udskrift +=
                                "\n    " + hotelBooking.getTilmelding().getAnkomstDato()
                                + " - " + hotelBooking.getTilmelding().getAfrejseDato() + "\n";
                    if (hotelBooking.getValgteTillæg() != null) {
                        for (Tillæg tillæg : hotelBooking.getValgteTillæg()) {
                            udskrift += "     " + tillæg.getNavn() + " " + tillæg.getPris() + "kr.\n";
                        }
                    }
                            udskrift += "Konference: " + hotelBooking.getTilmelding().getKonference().getNavn() + "\n";
                            udskrift += "Samlet pris: " + hotelBooking.samletPris() + "\n";
                    udskrift += "\n";
                }
            }
        }

        return udskrift;
    }

    /**
     * Henter alle hotelaftaler og tilføjer dem med unikke navne til en liste af Strings, som returneres.
     */
    public static ArrayList<String> getUnikkeHotelNavne() {
        ArrayList<String> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftale()) {
            if (!temp.contains(hotelAftale.getNavn())) {
                temp.add(hotelAftale.getNavn());
            }
        }
        return temp;
    }

    /**
     * Henter alle hotelaftaler med et specifikt navn og returnerer en liste med de hotelaftaler.
     */
    public static ArrayList<HotelAftale> GetAlleHotelAftalerMedNavn(String hotelNavn) {
        // Find alle hotelaftaler med samme navn
        ArrayList<HotelAftale> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftale()) {
            if (hotelAftale.getNavn().equals(hotelNavn)) {
                temp.add(hotelAftale);
            }
        }

        return temp;
    }
}