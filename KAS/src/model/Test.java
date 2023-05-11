package model;

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

        Tilmelding t1 = new Tilmelding(LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-15"), false, de1, k);

        System.out.println(t1.toString());

    }
}
