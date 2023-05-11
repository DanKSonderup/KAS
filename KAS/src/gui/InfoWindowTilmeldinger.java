package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;

import java.awt.*;

public class InfoWindowTilmeldinger extends Stage {
    public InfoWindowTilmeldinger(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("New Person");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    Konference konference;


    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblTilmeldingerTil = new Label("Tilmeldinger til " + konference.getNavn());
        pane.add(lblTilmeldingerTil, 0, 0);

        Label lblInfoPåDeltager = new Label();
    }

    public String printInfoPåDeltager() {
        Controller.getAlleTilmeldinger(this.konference);

        return "";
    }
}
