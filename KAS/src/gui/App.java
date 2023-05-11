package gui;

import controller.Controller;
import model.Deltager;
import model.Konference;
import model.Ledsager;
import model.Tilmelding;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        LoginPane.launch(LoginPane.class);
    }

    public static void initStorage() {
        Konference kf = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.parse("2023-10-08"), LocalDate.parse("2023-10-16"),1500);
        Deltager deltager1 = new Deltager("Hans", "Odensevej", "888888", "Odense", "Danmark");
        LocalDate date1 = LocalDate.parse("2023-11-13");
        LocalDate d2 = LocalDate.parse("2023-11-15");
        kf.addTilmelding(new Tilmelding(date1,d2,true, deltager1, kf));


        Deltager deltager2 = new Deltager("Hans", "Odensevej 22", "888888", "Odense", "Danmark");
        Tilmelding deltager2Tilmelding = new Tilmelding(date1,d2,true, deltager2, kf);
        kf.addTilmelding(deltager2Tilmelding);

        Ledsager ledsager = new Ledsager("Mette", deltager2Tilmelding);
        deltager2Tilmelding.setLedsager(ledsager);
        System.out.println(deltager2Tilmelding);
    }
}
