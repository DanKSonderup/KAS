package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.parse("2023-11-13");
        LocalDate d2 = LocalDate.parse("2023-11-15");

        long daysBetween = ChronoUnit.DAYS.between(d1, d2);

        System.out.println(daysBetween);

    }
}
