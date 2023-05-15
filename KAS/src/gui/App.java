package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        initStorage();
        LoginPane.launch(LoginPane.class);
    }

    public static void initStorage() {
        Konference kf = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.parse("2023-12-18"), LocalDate.parse("2023-12-20"),1500);
        LocalDate d1 = LocalDate.parse("2023-12-18");
        LocalDate d2 = LocalDate.parse("2023-12-19");
        LocalDate d3 = LocalDate.parse("2023-12-20");

        Deltager deltager1 = new Deltager("Finn Madsen", "Odensevej 22", "11111111", "Aalborg", "Danmark");
        Deltager deltager2 = new Deltager("Niels Petersen", "Gaden", "22222222", "Aarhus", "Danmark");
        Deltager deltager3 = new Deltager("Ulla Hansen", "Tabervej 69", "33333333", "Herning", "Danmark");
        Deltager deltager4 = new Deltager("Peter Sommer", "Vindervej 420", "44444444", "Liverpool", "England");
        Deltager deltager5 = new Deltager("Lone Jensen", "Christiania", "55555555", "København", "Danmark");

        Tilmelding t1 = Controller.createTilmelding(kf, deltager1, d1, d3,false);
        Tilmelding t2 = Controller.createTilmelding(kf, deltager2, d1, d3,false);
        Tilmelding t3 = Controller.createTilmelding(kf, deltager3, d1, d2, false);
        Tilmelding t4 = Controller.createTilmelding(kf, deltager4, d1, d3, false);
        Tilmelding t5 = Controller.createTilmelding(kf, deltager5, d1, d3, true);

        Ledsager l1 = Controller.createLedsager("Hans Hansen", t3);
        Ledsager l2 = Controller.createLedsager("Mie Sommer", t4);
        Ledsager l3 = Controller.createLedsager("Jan Madsen", t5);

        Udflugt u1 = Controller.createUdflugt(kf,"Byrundtur, Odense", d1, 125, "Odense", true);
        Udflugt u2 = Controller.createUdflugt(kf,"Egeskov", d2, 75, "Kværndrup", false);
        Udflugt u3 = Controller.createUdflugt(kf,"Trapholt Museum, Kolding", d3, 200, "Kolding", true);

        HotelAftale h1 = Controller.createHotelAftale("Den Hvide Svane", 1050, 1250, "Odense", kf);
        HotelAftale h2 = Controller.createHotelAftale("Høtel Phønix", 700, 800, "Odense", kf);
        HotelAftale h3 = Controller.createHotelAftale("Esbjerg Hotel", 500, 600, "Odense", kf);

        // Hotel 1 tillæg
        h1.addInkluderetTillæg("Bad");
        Controller.createTillæg(h1, "Wifi", 50);

        // Hotel 2 tillæg
        Controller.createTillæg(h2,"Bad", 200);
        Controller.createTillæg(h2,"WIFI", 75);

        // Hotel 3 tillæg
        Controller.createTillæg(h3,"Morgenmad", 100);


        ArrayList<Udflugt> udflugter1 = new ArrayList<>();
        udflugter1.add(u1);
        ArrayList<Udflugt> udflugter2 = new ArrayList<>();
        udflugter2.add(u2);
        udflugter2.add(u3);
        ArrayList<Udflugt> udflugter3 = new ArrayList<>();
        udflugter3.add(u1);
        udflugter3.add(u2);
        Controller.addUdflugterTilLedsager(l1,udflugter1);
        Controller.addUdflugterTilLedsager(l2,udflugter2);
        Controller.addUdflugterTilLedsager(l3,udflugter3);

        Tillæg tilWIFI = new Tillæg("WIFI", 50);

        ArrayList<Tillæg> tillægPeter = new ArrayList<>();
        tillægPeter.add(tilWIFI);

        ArrayList<Tillæg> tillægLone = new ArrayList<>();
        tillægLone.add(tilWIFI);

        Controller.createHotelBooking(t2, null, h1);
        Controller.createHotelBooking(t4, tillægPeter, h1);
        Controller.createHotelBooking(t5, tillægLone, h1);
    }
}
