package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Udflugt;

public class OpretLedsagerWindow extends Stage {

    Konference konference;
    public OpretLedsagerWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        // this.setWidth(300); --> Nødvendig hvis hidden items

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

    
    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        lvwUdflugter.getItems().setAll(konference.getUdflugter());

        Label lblOpretLedsager = new Label("Opret Ledsager");
        pane.add(lblOpretLedsager,1,0);
        Label lblNavn = new Label("Navn: ");

        pane.add(lblNavn,0,1);
        pane.add(txfNavn,1,1);
        Label lblUdflugter = new Label("Udflugter");
        pane.add(lblUdflugter,1,2);
        pane.add(lvwUdflugter,1,3);
        lvwUdflugter.setPrefHeight(150);
        lvwUdflugter.setPrefWidth(300);
    }


}
