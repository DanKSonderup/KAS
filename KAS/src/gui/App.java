package gui;

import controller.Controller;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        LoginPane.launch(LoginPane.class);
    }

    public static void initStorage() {
        Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.parse("2023-10-08"), LocalDate.parse("2023-10-16"),1500);
    }
}
