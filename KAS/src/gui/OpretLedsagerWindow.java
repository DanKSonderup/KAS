package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Ledsager;
import model.Udflugt;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretLedsagerWindow extends Stage {
    private Konference konference;
    private String navn;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;

    public OpretLedsagerWindow(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.konference = konference;
        this.setTitle("New Person");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final TextField txfNavn = new TextField();
    private final ListView<Udflugt> lvwUdflugter = new ListView<>();
    private final Button btnOpret = new Button("Tilføj Ledsager");
    private final Button btnAfbryd = new Button("Afbryd");
    private final Label lblError = new Label();

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        lvwUdflugter.getItems().setAll(Controller.getUdflugterForKonferenceMellem(konference, ankomstDato, afrejseDato));
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label lblOpretLedsager = new Label("Opret Ledsager");
        pane.add(lblOpretLedsager,1,0);
        Label lblNavn = new Label("Navn: ");

        pane.add(lblNavn,0,1);
        pane.add(txfNavn,1,1);
        Label lblUdflugter = new Label("Vælg de ønskede udflugter");
        pane.add(lblUdflugter,1,2);
        pane.add(lvwUdflugter,1,3);
        lvwUdflugter.setPrefHeight(150);
        lvwUdflugter.setPrefWidth(300);

        HBox hboxKnapper = new HBox();
        hboxKnapper.setSpacing(15);
        hboxKnapper.getChildren().add(btnOpret);
        hboxKnapper.getChildren().add(btnAfbryd);

        pane.add(hboxKnapper,1,4);
        pane.add(lblError,1,5);
        lblError.setVisible(false);

        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpret.setOnAction(event -> this.opretLedsagerOnAction());
    }


    private void afbrydOnAction() {
        this.hide();
    }

    private void opretLedsagerOnAction() {
        if (txfNavn.getText().trim().length() < 1) {
            lblError.setText("Dit navn er tomt");
            lblError.setVisible(true);
            return;
        } else {
            navn = txfNavn.getText().trim();
            if (lvwUdflugter.getSelectionModel().getSelectedItems() != null) {
                ArrayList<Udflugt> udflugter = new ArrayList<>(lvwUdflugter.getSelectionModel().getSelectedItems());
                for (Udflugt udflugt: udflugter) {
                    this.udflugter.add(udflugt);
                }
            }
        }
        this.hide();
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }
}
