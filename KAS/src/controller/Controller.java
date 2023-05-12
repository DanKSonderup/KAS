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
    public static Konference createKonference(String navn, String sted, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, prisPrDag);
        Storage.storeKonferencer(konference);
        return konference;
    }

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

    public static ArrayList<Udflugt> getAlleUdflugter(Konference konference) {
        return konference.getUdflugter();
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


    public static ArrayList<HotelAftale> getAllHotelAftaler() {
        return Storage.getHotelAftaler();
    }

    /**
     * Opretter et tillæg
     * Pre: beskrivelse er ikke tomt, hotel ikke null, pris >= 0
     */

    public static void createTillæg(HotelAftale hotelAftale, String navn, double pris) {
        hotelAftale.createTillæg(new Tillæg(navn,pris));
    }

    public static ArrayList<Tillæg> getAlleKøbsTillæg(HotelAftale hotelAftale) {
        ArrayList<Tillæg> købsTillæg = new ArrayList<>();
        for (Tillæg købs : hotelAftale.getTillæg()) {
            if (købs.getPris() > 0) {
                købsTillæg.add(købs);
            }
        }
        return købsTillæg;
    }

    public static ArrayList<Tillæg> getAlleInkluderedeTillæg(HotelAftale hotelAftale) {
        ArrayList<Tillæg> inkluderedeTillæg = new ArrayList<>();
        for (Tillæg inkluderede : hotelAftale.getTillæg()) {
            if (inkluderede.getPris() == 0) {
                inkluderedeTillæg.add(inkluderede);
            }
        }
        return inkluderedeTillæg;
    }

    // Firma

    public static Firma createFirma(String navn, String telefonnummer) {
        Firma firma = new Firma(navn, telefonnummer);
        Storage.storeFirma(firma);
        return firma;
    }

    // Deltager

    public static Deltager createDeltager(
            String navn, String adresse, String telefonnummer,
            String by, String land) {
        Deltager deltager = new Deltager(navn, adresse, telefonnummer, by, land);
        Storage.storeDeltager(deltager);
        return deltager;
    }


    public static Tilmelding createTilmelding(Konference konference, Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder) {
        Tilmelding t1 = new Tilmelding(ankomstDato,afrejseDato,foredragsholder, deltager, konference);
        konference.addTilmelding(t1);
        deltager.addTilmelding(t1);
        return t1;
    }

    public static HotelBooking createHotelBooking(Tilmelding tilmelding, ArrayList<Tillæg> valgteTillæg, HotelAftale hotelAftale) {
        HotelBooking h1 = new HotelBooking(tilmelding, valgteTillæg, hotelAftale);
        tilmelding.addHotelBooking(h1);
        hotelAftale.addHotelBooking(h1);
        return h1;
    }

    public static void addLedsagerTilTilmelding(Tilmelding tilmelding, Ledsager ledsager) {
        tilmelding.setLedsager(ledsager);
    }

    // public static void addFirmaTilTilmelding()

    public static ArrayList<Tilmelding> getAlleTilmeldinger(Konference konference) {
        ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
        for (Tilmelding t : konference.getTilmeldinger()) {
            tilmeldinger.add(t);
        }
        return tilmeldinger;
    }

    public static ArrayList<Deltager> getAlleDeltagere() {
        ArrayList<Deltager> deltagere = new ArrayList<>();
        for (Konference konference: Storage.getKonferencer()) {

            for (Tilmelding tilmelding: konference.getTilmeldinger()) {
                deltagere.add(tilmelding.getDeltager());
            }
        }
        return deltagere;
    }

    public static ArrayList<Deltager> getAlleDeltagerForKonference(Konference konference) {
        ArrayList<Deltager> deltagerne = new ArrayList<>();
        for (Tilmelding t : konference.getTilmeldinger()) {
            deltagerne.add(t.getDeltager());
        }
        return deltagerne;
    }

    public static ArrayList<String> getAlleTilmeldingerInfo(Konference konference) {
        ArrayList<String> info = new ArrayList<>();
        for (Tilmelding t : konference.getTilmeldinger()) {
            info.add(t.toString());
        }
        return info;
    }

    public static String getAlleUdflugterInfo(Konference konference) {
        String info = konference.visInfo();
        return info;
    }

    public ArrayList<String> GetHotelNavne() {
        // Finde alle unikke hotelnavne
        ArrayList<String> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftaler()) {
            if (!temp.contains(hotelAftale.getNavn())) {
                temp.add(hotelAftale.getNavn());
            }
        }
        return temp;
    }

    public static Ledsager createLedsager(String navn, Tilmelding tilmelding) {
        Ledsager l1 = new Ledsager(navn, tilmelding);
        tilmelding.setLedsager(l1);
        return l1;
    }

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
                    udskrift += "\n";
                }
            }
        }

        return udskrift;
    }

    public static ArrayList<String> getUnikkeHotelNavne() {
        ArrayList<String> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftaler()) {
            if (!temp.contains(hotelAftale.getNavn())) {
                temp.add(hotelAftale.getNavn());
            }
        }
        return temp;
    }

    public static ArrayList<HotelAftale> GetAlleHotelAftalerMedNavn(String hotelNavn) {
        // Find alle hotelaftaler med samme navn
        ArrayList<HotelAftale> temp = new ArrayList<>();
        for (HotelAftale hotelAftale : Storage.getHotelAftaler()) {
            if (hotelAftale.getNavn().equals(hotelNavn)) {
                temp.add(hotelAftale);
            }
        }

        return temp;
    }
}

