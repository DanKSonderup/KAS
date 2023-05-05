package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Konference;

public class KonferencePane extends Application {


    public void start(Stage stage) {
        Image image = new Image("ressources/kas2.png", 100, 100, false, false);
        stage.setTitle("Konference Administration System");
        stage.getIcons().add(image);
        GridPane pane = new GridPane();
        this.initContent(pane);
        pane.setMinHeight(500);
        pane.setMinWidth(800);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    //--------------------------------------------------
    // Data felter

    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final Button btnOpretKonference = new Button("Opret Konference");
    private final Button btnOpretHotel = new Button("Opret Hotel");
    private final Button btnOpretUdflugt = new Button("Opret Udflugt");

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(25);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblNavn = new Label("Navn");
        Label lblDatoer = new Label("Datoer");
        Label lblSted = new Label("Sted");
        Label lblPrisPerDag = new Label("Pris pr. dag");
        HBox hboxListViewHeader = new HBox();
        hboxListViewHeader.setSpacing(80);
        hboxListViewHeader.getChildren().add(lblNavn);
        hboxListViewHeader.getChildren().add(lblDatoer);
        hboxListViewHeader.getChildren().add(lblSted);
        hboxListViewHeader.getChildren().add(lblPrisPerDag);

        pane.add(hboxListViewHeader,0,0,4,1);

        lvwKonferencer.setMinWidth(500);
        lvwKonferencer.setMaxHeight(200);
        pane.add(lvwKonferencer,0,1,4,2);
        pane.add(btnOpretKonference,1,3);
        pane.add(btnOpretHotel,2,3);
        pane.add(btnOpretUdflugt,5,1);

        // Tilføjer button actions
        btnOpretKonference.setOnAction(event -> this.opretKonferenceOnAction());
    }

    public void opretKonferenceOnAction() {
        AddKonferenceWindow dialog = new AddKonferenceWindow();
        dialog.showAndWait();

        // Wait for the modal dialog to close

        // Koden her mangler nogle metoder før de virker

        // lvwKonferencer.getItems().setAll(Storage.getKonferencer());
        //int index = lvwKonferencer.getItems().size() - 1;
        // lvwKonferencer.getSelectionModel().select(index);
    }

}
