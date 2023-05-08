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

public class AddHotelAftaleWindow extends Stage {
    private Konference konference;
    private HotelAftale hotelAftale;
    private final GridPane pane = new GridPane();
    private final GridPane pane2 = new GridPane();
    private final Scene scene = new Scene(pane);
    private final Scene scene2 = new Scene(pane2);

    public AddHotelAftaleWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.konference = konference;

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
    private final Button btnFortsæt = new Button("Fortsæt");
    private final Label lblAlert = new Label();
    private boolean alertShown = false;

    public void initContent(GridPane pane) {
        String[] labelStrenge = {"Navn:", "Lokation", "Pris per nat enkeltværelse:", "Pris per nat dobbeltværelse:"};
        TextField[] tekstFelter = {txfNavn, txfLokation, txfPrisPrNatEnkeltVærelse, txfPrisPrNatDobbeltVærelse};

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblInformation = new Label("Information");
        pane.add(lblInformation,0,0);
        for (int i = 0; i < 4; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1,0,i+1);
            pane.add(tekstFelter[i],1,i+1);
        }
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnFortsæt);
        pane.add(hboxButtons,1,7);
        pane.add(lblAlert,0,5);
        lblAlert.setText("Er du sikker på du vil fortsætte? \nDu kan ikke ændre navn og pris når du fortsætter");
        lblAlert.setVisible(false);

        // Tilføjer action events til knapper
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnFortsæt.setOnAction(event -> this.FortsætOnAction());
    }

    private final ListView<Tillæg> lvwTillæg = new ListView<>();
    private final TextField txfTillægNavn = new TextField();
    private final TextField txfTillægPris = new TextField();
    private final Button btnOpretTillæg = new Button("Opret tillæg");
    private final Button btnOpretAftale = new Button("Opret Hotelaftale");

    public void initScene2(GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        Label lblTillæg = new Label("Tillæg");
        pane.add(lblTillæg,0,0);
        pane.add(lvwTillæg,0,1,2,1);
        lvwTillæg.setMaxHeight(140);

        Label lblTillægNavn = new Label("Navn:");
        pane.add(lblTillægNavn,0,3);
        pane.add(txfTillægNavn,1,3);
        Label lblTillægPris = new Label("Pris:");
        pane.add(lblTillægPris,0,4);
        pane.add(txfTillægPris,1,4);
        pane.add(btnOpretTillæg,0,5);
        pane.add(btnOpretAftale,0,7);

        btnOpretTillæg.setOnAction(event -> this.opretTillægOnAction());
    }

    public void afbrydOnAction() {
        this.hide();
    }

    public void FortsætOnAction() {
        if (!alertShown) {
            lblAlert.setTextFill(Color.RED);
            lblAlert.setVisible(true);
            alertShown = true;
        } else {
            String navn = txfNavn.getText().trim();
            double prisPrNatEnkelt = Double.parseDouble(txfPrisPrNatEnkeltVærelse.getText().trim());
            double prisPrNatDouble = Double.parseDouble(txfPrisPrNatDobbeltVærelse.getText().trim());
            String lokation = txfLokation.getText().trim();
            hotelAftale = new HotelAftale(navn,prisPrNatEnkelt,prisPrNatDouble,lokation,konference);
            this.initScene2(pane2);
            this.setScene(scene2);
        }
    }

    public void opretTillægOnAction() {
        String tillægNavn = txfTillægNavn.getText().trim();
        double tillægPris = Double.parseDouble(txfTillægPris.getText().trim());
        Controller.createTillæg(hotelAftale,tillægNavn,tillægPris);
        lvwTillæg.getItems().setAll(hotelAftale.getTillæg());
    }

    public void opretHotelAftaleOnAction() {
        Controller.createHotelAftale(hotelAftale);
    }
}
