package model;

import controller.Controller;

import javax.naming.ldap.Control;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.parse("2023-11-13");
        LocalDate d2 = LocalDate.parse("2023-11-15");

        long daysBetween = ChronoUnit.DAYS.between(d1, d2);

        System.out.println(daysBetween);

        System.out.println(d1);

        Deltager de1 = Controller.createDeltager("Oliver Buus", "Hjem", "88888888", "Aarhus", "Danmark");
        Konference k = new Konference("Konfi", "Aarhus", LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), 1100);
        Konference k2 = new Konference("HEHE", "Odense", LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), 2000);

        // Tilmelding t1 = new Tilmelding(LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), true, de1, k);


        Udflugt u1 = new Udflugt("Byrundtur Odense", d1, 100, "Odense", true);
        Udflugt u2 = new Udflugt("Hyttetur", d1, 150, "Galten", true);
        Udflugt u3 = new Udflugt("Paintball", d2, 200, "Aarhus", false);

        Deltager de2 = Controller.createDeltager("Darwin Nunez", "Anfield Road", "22176433", "Liverpool", "England");
        Deltager de3 = Controller.createDeltager("Mo Salah", "Anfield Road v2", "4206900", "Liverpool", "England");
        Deltager de4 = Controller.createDeltager("Oliver Buus", "Anfield Road v2", "4206900", "Liverpool", "England");


        // Tilmelding t2 = new Tilmelding(d1, d2, false, de2, k);
        // Tilmelding t3 = new Tilmelding(d1, d2, true, de3, k);

        Tilmelding t1 = Controller.createTilmelding(k, de1, d1, d2, true);
        Tilmelding t2 = Controller.createTilmelding(k, de2, d1, d2, false);
        Tilmelding t3 = Controller.createTilmelding(k, de3, d1, d2, true);
        Tilmelding t4 = Controller.createTilmelding(k2, de4, d1, d2, true);



        k.addUdflugt(u1);
        k.addUdflugt(u2);
        k.addUdflugt(u3);
        k.addTilmelding(t1);
        k.addTilmelding(t2);
        k.addTilmelding(t3);
        k2.addTilmelding(t4);


        Ledsager l1 = new Ledsager("Jude Bellingham", t2);
        Ledsager l2 = new Ledsager("Mason Mount", t3);
        Ledsager l3 = ("Daniel Cock", t1);



        System.out.println(k.visInfo());







        HotelAftale h1 = Controller.createHotelAftale("EsbjergHotel", 1000, 1200, "Esbjerg", k);
        HotelAftale h2 = Controller.createHotelAftale("Odense", 700, 1000, "Odense", k);
        HotelAftale h3 = Controller.createHotelAftale("EsbjergHotel", 1500, 2000, "Esbjerg", k2);

        h1.getTillæg();

        HotelBooking hb1 = Controller.createHotelBooking(t1, h1.getTillæg(), h1);
        HotelBooking hb2 = Controller.createHotelBooking(t2, h1.getTillæg(), h2);
        HotelBooking hb3 = Controller.createHotelBooking(t3, h1.getTillæg(), h3);
        HotelBooking hb4 = Controller.createHotelBooking(t4, h1.getTillæg(), h3);


        System.out.println(Controller.visHotelOgDeltagerInfo());

    }
}
