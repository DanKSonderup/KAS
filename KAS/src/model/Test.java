package model;

import controller.Controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.parse("2023-11-13");
        LocalDate d2 = LocalDate.parse("2023-11-15");

        long daysBetween = ChronoUnit.DAYS.between(d1, d2);

        System.out.println(daysBetween);

        System.out.println(d1);

        Deltager de1 = new Deltager("Oliver Buus", "Hjem", "88888888", "Aarhus", "Danmark");
        Konference k = new Konference("Konfi", "Aarhus", LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), 1100);

        // Tilmelding t1 = new Tilmelding(LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), true, de1, k);


        Udflugt u1 = new Udflugt("Byrundtur Odense", d1, 100, "Odense", true);
        Udflugt u2 = new Udflugt("Hyttetur", d1, 150, "Galten", true);
        Udflugt u3 = new Udflugt("Paintball", d2, 200, "Aarhus", false);

        Deltager de2 = new Deltager("Darwin Nunez", "Anfield Road", "22176433", "Liverpool", "England");
        Deltager de3 = new Deltager("Mo Salah", "Anfield Road v2", "4206900", "Liverpool", "England");

        // Tilmelding t2 = new Tilmelding(d1, d2, false, de2, k);
        // Tilmelding t3 = new Tilmelding(d1, d2, true, de3, k);

        Tilmelding t1 = Controller.createTilmelding(k, de1, d1, d2, true);
        Tilmelding t2 = Controller.createTilmelding(k, de2, d1, d2, false);
        Tilmelding t3 = Controller.createTilmelding(k, de3, d1, d2, true);


        k.addUdflugt(u1);
        k.addUdflugt(u2);
        k.addUdflugt(u3);
        k.addTilmelding(t1);
        k.addTilmelding(t2);
        k.addTilmelding(t3);

        Ledsager l1 = new Ledsager("Jude Bellingham", t2);
        Ledsager l2 = new Ledsager("Mason Mount", t3);

        u1.addLedsager(l1);
        u1.addLedsager(l2);

        System.out.println(k.visInfo());
    }
}
