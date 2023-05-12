package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        LoginPane.launch(LoginPane.class);
    }

    public static void initStorage() {
        Konference kf = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.parse("2023-10-08"), LocalDate.parse("2023-10-16"),1500);
        LocalDate date1 = LocalDate.parse("2023-10-13");
        LocalDate d2 = LocalDate.parse("2023-10-15");

        Deltager deltager1 = new Deltager("Hans", "Odensevej 22", "888888", "Odense", "Danmark");
        Deltager deltager2 = new Deltager("Peter", "Odensevej 22", "888888", "Odense", "Danmark");
        Deltager deltager3 = new Deltager("Oliver", "Tabervej 69", "69696969", "Odense", "Letland");
        Deltager deltager4 = new Deltager("Daniel", "Vindervej 420", "13376969", "Herning", "Danmark");


        // Tilmelding deltager2Tilmelding = new Tilmelding(date1,d2,true, deltager2, kf);


        Tilmelding t2 = Controller.createTilmelding(kf, deltager1,date1,d2,true);
        Tilmelding t1 = Controller.createTilmelding(kf, deltager2,date1,d2,true);
        Tilmelding t3 = Controller.createTilmelding(kf, deltager3, date1, d2, false);
        Tilmelding t4 = Controller.createTilmelding(kf, deltager4, date1, d2, false);

        Ledsager l1 = new Ledsager("Jeppe", t1);
        Ledsager l2 = new Ledsager("Trent Alexander Arnold", t3);
        Controller.addLedsagerTilTilmelding(t1,l1);
        Controller.addLedsagerTilTilmelding(t3,l2);

        Udflugt u1 = Controller.createUdflugt(kf,"Byrundtur Odense", date1, 100, "Odense", true);
        Udflugt u2 = Controller.createUdflugt(kf,"MCH Arena", date1, 100, "Herning", false);

        HotelAftale h1 = Controller.createHotelAftale("Esbjerg Hotel", 1000, 1200, "Esbjerg", kf);
        HotelAftale h2 = Controller.createHotelAftale("Odense Hotel", 700, 1000, "Odense", kf);

        Controller.createTillæg(h1, "WIFI", 250);
        Controller.createTillæg(h2, "Mad", 150);
        Controller.createTillæg(h1,"Svømmekort", 450);
    }
}
