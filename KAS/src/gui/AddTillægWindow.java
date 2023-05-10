package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Tillæg;

import java.util.ArrayList;

public class AddTillægWindow extends Stage {
    private String navn;
    private double pris;
    public AddTillægWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setTitle("Opret Tillæg");
        this.initContent(pane);
        this.setScene(scene);
    }

    //-----------------------------------------------------------
    // Data felter til Gridpane indsættes her
    private final TextField txfTillægNavn = new TextField();
    private final TextField txfTillægPris = new TextField();
    private final Button btnOpretTillæg = new Button("Opret tillæg");
    private final Label lblError = new Label("Felt har en ugyldig værdi");

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        Label lblTillægNavn = new Label("Navn:");
        pane.add(lblTillægNavn,0,0);
        pane.add(txfTillægNavn,1,0);

        Label lblTillægPris = new Label("Pris:");
        pane.add(lblTillægPris,0,1);
        pane.add(txfTillægPris,1,1);
        pane.add(btnOpretTillæg,1,2);
        pane.add(lblError,1,4);
        lblError.setVisible(false);

        // Action events
        btnOpretTillæg.setOnAction(event -> this.opretTillægOnAction());
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    private void opretTillægOnAction() {
        String navnInput = txfTillægNavn.getText().trim();
        if (navnInput.length() == 0) {
            lblError.setVisible(true);
            lblError.setText("Navn er ikke udfyldt");
            lblError.setTextFill(Color.RED);
            return;
        }
        double prisInput = -1;
        try {
        prisInput = Double.parseDouble(txfTillægPris.getText().trim()) ;
        } catch (NumberFormatException ex) {
            lblError.setVisible(true);
            lblError.setText("Pris er ikke et tal");
            lblError.setTextFill(Color.RED);
            return;
        }
        if (prisInput < 0) {
            lblError.setVisible(true);
            lblError.setText("Pris må ikke være negativ");
            lblError.setTextFill(Color.RED);
            return;
        }
        navn = navnInput;
        pris = prisInput;
        this.hide();
    }
}
