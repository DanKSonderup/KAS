package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Konference konference;
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
    private final Button btnLuk = new Button("Luk");

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        pane.add(visTilmeldinger, 0, 0, 2, 2);
        visTilmeldinger.setText(konference.printInfoPåDeltager(konference));

        pane.add(btnLuk, 2, 3);
        btnLuk.setOnAction(event -> this.btnLukOnAction());

    }


    private void btnLukOnAction() {
        this.hide();
    }

}
