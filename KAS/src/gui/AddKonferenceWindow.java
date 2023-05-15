package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddKonferenceWindow extends Stage {

    public AddKonferenceWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret Konference");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final TextField txfNavn = new TextField();
    private final TextField txAdresse = new TextField();
    private final TextField txfStartDato = new TextField();
    private final TextField txfSlutDato = new TextField();
    private final TextField txfPrisPerDag = new TextField();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOpret = new Button("Opret Konference");
    private final Label lblError = new Label("Der opstod en fejl \n prøv igen");

    public void initContent(GridPane pane) {
        String[] labelStrenge = {"Navn:", "Adresse:", "Start Dato:", "Slut Dato:", "Pris pr. dag:"};
        TextField[] tekstFelter = {txfNavn, txAdresse, txfStartDato, txfSlutDato, txfPrisPerDag};

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // Tilføjer Labels og Tekstfelter
        for (int i = 0; i < 5; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1,0,i);
            pane.add(tekstFelter[i],1,i);
        }

        txfStartDato.setPromptText("DD-MM-YYYY");
        txfSlutDato.setPromptText("DD-MM-YYYY");

        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOpret);
        pane.add(hboxButtons,3,5);

        // Vbox til vores Error label
        VBox vboxError = new VBox();
        pane.add(vboxError,3,6);
        vboxError.getChildren().add(lblError);
        lblError.setVisible(false);

        // Tilføjer action events til knapper
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpret.setOnAction(event -> this.opretOnAction());

    }

    public void afbrydOnAction() {
        this.hide();
    }

    public void opretOnAction() {
        String startDatoString = txfStartDato.getText().trim();
        String slutDatoString = txfSlutDato.getText().trim();
        String navn = txfNavn.getText().trim();
        String lokation = txAdresse.getText().trim();

        if (navn.length() == 0) {
            lblError.setText("Navn skal udfyldes \n");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (lokation.length() == 0) {
            lblError.setText("Adresse skal udfyldes \n");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (!erDatoValid(startDatoString)) {
            lblError.setText("Startdato er ikke \nen gyldig Dato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (!erDatoValid(slutDatoString)) {
            lblError.setText("Slutdato er ikke \nen gyldig Dato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        double prisPrDag = -1;
        try {
            prisPrDag = Double.parseDouble(txfPrisPerDag.getText().trim());
        } catch (NumberFormatException ex) {
            lblError.setText("Pris per dag er ikke et tal \n");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (prisPrDag < 0) {
            lblError.setText("Pris per må ikke være negativ\n");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        LocalDate startDate = null;
        LocalDate slutDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        try {
            startDate = LocalDate.parse(startDatoString, formatter);
            slutDate = LocalDate.parse(slutDatoString, formatter);
        }
        catch (DateTimeParseException e) {
            lblError.setText("En af dine datoer \ner ikke en gyldig dato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (slutDate.isBefore(startDate)) {
            lblError.setText("Din slutdato er før \ndin startdato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        Controller.createKonference(navn, lokation, startDate,slutDate,prisPrDag);
        this.hide();

    }

    private boolean erDatoValid(String dato) {
        boolean erValid = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        try {
            formatter.parse(dato);
        } catch (DateTimeParseException e) {
            erValid = false;
        }

        return erValid;
    }
}
