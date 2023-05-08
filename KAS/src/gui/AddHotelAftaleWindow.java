package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HotelAftale;
import model.Konference;
import model.Tillæg;
import storage.Storage;

import java.util.ArrayList;

public class AddHotelAftaleWindow extends Stage {
    private Konference konference;

    private ArrayList<Tillæg> tillægListe = new ArrayList<>();

    public AddHotelAftaleWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        GridPane pane = new GridPane();
        this.konference = konference;
        Scene scene = new Scene(pane);
        this.setTitle("Opret Hotelaftale");
        this.initContent(pane);

        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final TextField txfNavn = new TextField();
    private final TextField txfLokation = new TextField();
    private final TextField txfPrisPrNatEnkeltVærelse = new TextField();
    private final TextField txfPrisPrNatDobbeltVærelse = new TextField();
    private final Button btnAfbryd = new Button("Afbryd");
    private final ListView<Tillæg> lvwTillæg = new ListView<>();
    private final Button btnOpretTillæg = new Button("Opret tillæg");
    private final Button btnOpretAftale = new Button("Opret Hotel Aftale");

    public void initContent(GridPane pane) {
        String[] labelStrenge = {"Navn:", "Lokation", "Pris per nat enkeltværelse:", "Pris per nat dobbeltværelse:"};
        TextField[] tekstFelter = {txfNavn, txfLokation, txfPrisPrNatEnkeltVærelse, txfPrisPrNatDobbeltVærelse};

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        for (int i = 0; i < 4; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1,0,i);
            pane.add(tekstFelter[i],1,i);
        }
        Label lblTillæg = new Label("Tillæg:");
        pane.add(lblTillæg,0,5);
        pane.add(lvwTillæg,1,5,1,3);
        lvwTillæg.setMaxHeight(120);
        pane.add(btnOpretTillæg,0,6);
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOpretAftale);
        pane.add(hboxButtons,1,9);


        // Tilføjer action events til knapper
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpretAftale.setOnAction(event -> this.OpretAftaleOnAction());
        btnOpretTillæg.setOnAction(event -> this.opretTillægOnAction());
    }

    public void afbrydOnAction() {
        this.hide();
    }

    public void OpretAftaleOnAction() {
            double prisPrNatEnkelt = 0;
            double prisPrNatDobbelt = 0;
            try {
                prisPrNatEnkelt = Double.parseDouble(txfPrisPrNatEnkeltVærelse.getText().trim());
                prisPrNatDobbelt = Double.parseDouble(txfPrisPrNatDobbeltVærelse.getText().trim());
            } catch (NumberFormatException ex) {

            }
            String navn = txfNavn.getText().trim();
            String lokation = txfLokation.getText().trim();
            Controller.createHotelAftale(navn,prisPrNatEnkelt,prisPrNatDobbelt, lokation, tillægListe, konference);
            System.out.println(Controller.getAllHotelAftaler());
            this.hide();
    }

    public void opretTillægOnAction() {
        AddTillægWindow dialog = new AddTillægWindow();

        dialog.showAndWait();
        // ----->

        tillægListe.add(dialog.getTillæg());
        lvwTillæg.getItems().setAll(tillægListe);
    }

    public void opretHotelAftaleOnAction() {
        // Controller.createHotelAftale(hotelAftale);
    }
}
