package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private final TextField txfLokation = new TextField();
    private final TextField txfStartDato = new TextField();
    private final TextField txfSlutDato = new TextField();
    private final TextField txfPrisPerDag = new TextField();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOpret = new Button("Opret Konference");
    private final Label lblError = new Label("Forket Data");

    public void initContent(GridPane pane) {
        String[] labelStrenge = {"Navn:", "Lokation:", "Start Dato:", "Slut Dato:", "Pris pr. dag:"};
        TextField[] tekstFelter = {txfNavn, txfLokation, txfStartDato, txfSlutDato, txfPrisPerDag};

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        for (int i = 0; i < 5; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1,0,i);
            pane.add(tekstFelter[i],1,i);
        }
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOpret);
        pane.add(hboxButtons,3,5);
        pane.add(lblError,0,7);
        lblError.setVisible(false);

        // Tilføjer action events til knapper
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpret.setOnAction(event -> this.opretOnAction());

    }

    public void afbrydOnAction() {
        this.hide();
    }

    public void opretOnAction() {
        String startDato = txfStartDato.getText().trim();
        String slutDato = txfSlutDato.getText().trim();
        String navn = txfNavn.getText().trim();
        String lokation = txfLokation.getText().trim();

        if (navn.length() == 0) {
            lblError.setText("Navn skal udfyldes");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (lokation.length() == 0) {
            lblError.setText("Lokation skal udfyldes");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (!erDatoValid(startDato)) {
            lblError.setText("Startdato er ikke en gyldig Dato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        if (!erDatoValid(slutDato)) {
            lblError.setText("Slutdato er ikke en gyldig Dato");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        double prisPrDag = -1;
        try {
            prisPrDag = Double.parseDouble(txfPrisPerDag.getText().trim());
        } catch (NumberFormatException ex) {
            lblError.setText("Pris per dag er ikke et tal");
            lblError.setTextFill(Color.RED);
            lblError.setVisible(true);
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

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
