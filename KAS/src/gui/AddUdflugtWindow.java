package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Udflugt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddUdflugtWindow extends Stage {
    private Konference konference;

    public AddUdflugtWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.konference = konference;

        this.setTitle("Opret Udflugt");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private final TextField txfNavn = new TextField();
    private final TextField txfDato = new TextField();
    private final TextField txfLokation = new TextField();
    private final TextField txfPris = new TextField();
    private final CheckBox cbFrokost = new CheckBox();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOk = new Button("Ok");
    private final Label lblError = new Label();
    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        String[] labelStrenge = {"Navn:", "Dato:", "Lokation:", "Pris:"};
        TextField[] tekstFelter = {txfNavn, txfDato, txfLokation, txfPris};

        for (int i = 0; i < labelStrenge.length; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1, 0, i);
            pane.add(tekstFelter[i], 1, i);
        }
        txfDato.setPromptText("DD-MM-YYYY");

        Label lblFrokost = new Label("Frokost:");
        pane.add(lblFrokost, 0, 4);
        pane.add(cbFrokost, 1, 4);
        pane.add(lblError,1,5);
        lblError.setMinHeight(40);

        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOk);
        pane.add(hboxButtons, 3, 5);


        btnOk.setOnAction(event -> okOnAction());
        btnAfbryd.setOnAction(event -> afbrydOnAction());


    }

    private void okOnAction() {
        boolean fortsæt = true;
        while (fortsæt) {
            if (!erDatoValid(txfDato.getText().trim())) {
                lblError.setText("Datoen er skrevet\ni forkert format!");
                lblError.setTextFill(Color.RED);
                fortsæt = false;
            } else if (!erDatoIKonference(txfDato.getText().trim())) {
                lblError.setText("Konferencen foregår\nikke på denne dato!");
                lblError.setTextFill(Color.RED);
                fortsæt = false;
            } else if (!erPrisValid(txfPris.getText().trim())) {
                lblError.setText("Pris er ikke et gyldigt tal!");
                lblError.setTextFill(Color.RED);
                fortsæt = false;
            } else if (txfNavn.getText().trim().length() < 1) {
                lblError.setText("Der er intet navn!");
                lblError.setTextFill(Color.RED);
                fortsæt = false;
            } else if (txfLokation.getText().trim().length() < 1) {
                lblError.setText("Der ingen lokation!");
                lblError.setTextFill(Color.RED);
                fortsæt = false;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                LocalDate udflugtDato = LocalDate.parse(txfDato.getText().trim(), formatter);
                double pris = Double.parseDouble(txfPris.getText().trim());
                Udflugt uf = Controller.createUdflugt(konference,txfNavn.getText().trim(), udflugtDato, pris, txfLokation.getText().trim(), cbFrokost.isSelected());
                this.hide();
                return;
            }
        }


    }

    private void afbrydOnAction() {
        this.hide();
    }

    /**
     * Returnerer om Pris i den valgte udflugt er en gyldig Double
     *
     */
    private boolean erPrisValid(String prisInput) {
        boolean erValid = true;
        try {
        double pris = Double.parseDouble(prisInput);
        } catch (NumberFormatException ex) {
            erValid = false;
        }
        return erValid;
    }

    // TODO
    /**
     * Returnerer om datoen i den valgte udflugt er valid eller ej
     * pre: Datofeltet er ikke tomt  og datoerne for konferencen er sat
     */
    private boolean erDatoValid(String dato) {
        boolean erValid = true;
        LocalDate udflugtDato = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        try {
            udflugtDato = LocalDate.parse(dato, formatter);
        }
        catch (DateTimeParseException e) {
            erValid = false;
        }

        return erValid;
    }

    /**
     * Returnerer om datoen for udflugten er indenfor konferencens datoer
     * pre: datoerne for konferencen er sat og datoen for udflugten er valid
     */

    private boolean erDatoIKonference(String dato) {
        boolean ErIKonference = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        if (erDatoValid(dato)) {
            LocalDate d = LocalDate.parse(dato, formatter);
            if (!d.isBefore(konference.getStartDato()) && !d.isAfter(konference.getSlutDato())) {
                ErIKonference = true;
            }
        }

        return ErIKonference;
    }
}
