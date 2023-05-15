package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private boolean foredragsholder;
    private Firma firma;
    private Deltager deltager;
    private Ledsager ledsager;
    private Konference konference;
    private HotelBooking hotelBooking;

    public Tilmelding(LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder,
                      Deltager deltager, Konference konference) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.foredragsholder = foredragsholder;
        this.deltager = deltager;
        this.konference = konference;
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    public void addFirma(Firma firma) {
        this.firma = firma;
    }

    public void addHotelBooking(HotelBooking hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public LocalDate getAnkomstDato() {
        return ankomstDato;
    }

    public LocalDate getAfrejseDato() {
        return afrejseDato;
    }
    
    public Konference getKonference() {
        return konference;
    }

        @Override
        public String toString () {
            String udskrift = deltager.getNavn() + "\n" +
                    "fra " + ankomstDato + " til " + afrejseDato + "\n";
                    udskrift += "Antal dage: " + this.antalDage();
            if (ledsager != null) {
                udskrift += "\nLedsager: " + ledsager.getNavn();
                if (ledsager.getUdflugter() != null) {
                    udskrift += "\nUdflugter: \n";
                    for (Udflugt udflugt : ledsager.getUdflugter()) {
                        udskrift += udflugt.getNavn() + "   \n";
                    }
                }
            }
            if (foredragsholder) {
                udskrift += "\n" + "Er foredragsholder";
            }
            if (hotelBooking != null) {
                udskrift += "\n Hotel: " + hotelBooking.getHotelAftaleNavn();
            }
            if (firma != null) {
                udskrift += "\n Firma: " + firma.getNavn();
            }
            udskrift += "\n Samlet pris: " + beregnSamletPris();
            udskrift += "\n\n";

            return udskrift;
        }

        public int antalNætter() {
            long daysBetween = ChronoUnit.DAYS.between(ankomstDato, afrejseDato);
            int dage = (int) daysBetween;
            return dage;
        }

        public int antalDage() {
        long daysBetween = ChronoUnit.DAYS.between(ankomstDato, afrejseDato);
        int dage = (int) daysBetween;
        return dage + 1;
        }


    public double beregnSamletPris() {
        double samletPris = 0;
        if (!foredragsholder) {
            samletPris += konference.getPrisPerDag() * antalDage();
        }
        if (ledsager != null) {
            samletPris += ledsager.samletUdflugtsPris();
        }
        if (hotelBooking != null) {
            samletPris += hotelBooking.samletPris();
        }

        return samletPris;
    }
}
