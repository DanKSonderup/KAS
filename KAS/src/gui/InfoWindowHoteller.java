package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HotelAftale;
import storage.Storage;

public class InfoWindowHoteller extends Stage {
    public InfoWindowHoteller() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        // this.setWidth(300); --> Nødvendig hvis hidden items

        this.setTitle("Vis oversigt over alle hoteller og alle deltagere som har besøgt hotellet");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

        //--------------------------------------------------
        // Data felter

    private TextArea visOversigt = new TextArea();

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        pane.add(visOversigt, 0,0 );
        visOversigt.setText(Controller.visHotelOgDeltagerInfo());
    }
}
