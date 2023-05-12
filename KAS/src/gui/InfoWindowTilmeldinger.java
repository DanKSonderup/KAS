package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Tilmelding;

import java.awt.*;

public class InfoWindowTilmeldinger extends Stage {
    Konference konference;
    public InfoWindowTilmeldinger(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.konference = konference;
        this.setResizable(false);
        this.setTitle("Tilmeldinger til " + konference.getNavn());
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter

    private final TextArea visTilmeldinger = new TextArea();
    private Tilmelding tilmelding;

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        pane.add(visTilmeldinger, 0, 0);
        visTilmeldinger.setText(printInfoPåDeltager());
    }
    public String printInfoPåDeltager() {
        String s = "";
        Controller.getAlleTilmeldinger(this.konference);
        for (Tilmelding tilmelding : Controller.getAlleTilmeldinger(this.konference)) {
            s += tilmelding.toString();
        }
        return s;
    }

//    public String printInfoPåDeltager() {
//        Controller.getAlleTilmeldinger(this.konference);
//
//        return "";
//    }
}
