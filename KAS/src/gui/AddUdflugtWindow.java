package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        for (int i = 0; i < labelStrenge.length - 1; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1, 0, i);
            pane.add(tekstFelter[i], 1, i);
        }
        txfDato.setPromptText("DD-MM-YYYY");

        Label lblFrokost = new Label("Frokost:");
        pane.add(lblFrokost, 0, 4);
        pane.add(cbFrokost, 1, 4);

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
                lblError.setText("Datoen er skrevet i forkert format!");
                fortsæt = false;
            } else if (!erDatoIKonference(txfDato.getText().trim())) {
                lblError.setText("Konferencen foregår ikke på denne dato!");
                fortsæt = false;
            }
        }

    }

    private void afbrydOnAction() {
        this.hide();
    }

    // TODO
    /**
     * Returnerer om datoen i den valgte udflugt er valid eller ej
     * pre: Datofeltet er ikke tomt  og datoerne for konferencen er sat
     */
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

    /**
     * Returnerer om datoen for udflugten er indenfor konferencens datoer
     * pre: datoerne for konferencen er sat og datoen for udflugten er valid
     */

    private boolean erDatoIKonference(String dato) {
        boolean ErIKonference = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        if (erDatoValid(dato)) {
            LocalDate d = (LocalDate) formatter.parse(dato);
            if (d.isAfter(konference.getStartDato()) && d.isBefore(konference.getSlutDato())) {
                ErIKonference = true;
            }
        }

        return ErIKonference;
    }
}
