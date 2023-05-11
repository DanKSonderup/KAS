package gui;

import controller.Controller;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Deltager;
import model.Konference;
import storage.Storage;

import javax.swing.event.ChangeListener;

public class OpretTilmeldingWindow extends Stage {

    private Konference konference;
    public OpretTilmeldingWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        // this.setWidth(300); --> Nødvendig hvis hidden items
        this.konference = konference;
        this.setTitle("Opret Tilmelding");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final ListView<Deltager> lvwDeltagere = new ListView<Deltager>();
    // Skal listview være Tilmelding eller bare en String?
    private final TextArea TextAreaInfo = new TextArea();
    private final TextField txfNavn = new TextField();
    private final TextField txfTelefonNr = new TextField();
    private final TextField txfAnkomstDato = new TextField();
    private final TextField txfAfrejseDato = new TextField();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOk = new Button("Opret tilmelding");
    private final Button btnOpretDeltager = new Button("Opret deltager");
    private final Button btnOpretHotelBooking = new Button("Opret hotelbooking");
    private final Button btnOpretLedsager = new Button("Opret/ny ledsager");
    private final Button btnVælgDeltager = new Button("Brug tidligere deltager");
    private final Button btnTilføjFirma = new Button("Tilføj Firma");
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final CheckBox cbxForedagsholder = new CheckBox("Foredragsholder");

    //-------------------------------------------------
    // Data Felter til oprettelse af Tilmelding
    private Deltager deltager;


    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        TextAreaInfo.setText("Konference: " + konference + "\n");
        updateControls();

        Label lblDeltager = new Label("Marker en deltager for at vælge en tidligere deltager");
        pane.add(lblDeltager, 0, 0);
        pane.add(lvwDeltagere, 0, 1, 2, 3);
        lvwDeltagere.setPrefHeight(170);
        lvwDeltagere.setPrefWidth(100);


        VBox vboxKnapperTop = new VBox();
        vboxKnapperTop.setSpacing(15);
        pane.add(vboxKnapperTop,3,1);
        vboxKnapperTop.getChildren().add(btnOpretDeltager);
        vboxKnapperTop.getChildren().add(btnVælgDeltager);
        vboxKnapperTop.getChildren().add(btnOpretLedsager);


        Label lblDinTilmelding = new Label("Din tilmelding: ");
        pane.add(lblDinTilmelding,0,5);
        VBox vboxKnapperBund = new VBox();
        vboxKnapperBund.setSpacing(15);
        vboxKnapperBund.getChildren().add(btnOpretHotelBooking);
        vboxKnapperBund.getChildren().add(btnTilføjFirma);
        pane.add(vboxKnapperBund,3,6);

        Label lblAnkomstDato = new Label("Ankomstdato:");
        pane.add(lblAnkomstDato, 0, 10);
        pane.add(txfAnkomstDato, 1, 10);

        Label lblAfrejseDato = new Label("Afrejsedato:");
        pane.add(lblAfrejseDato, 0, 11);
        pane.add(txfAfrejseDato, 1, 11);
        pane.add(cbxForedagsholder, 1, 12);

        btnOpretHotelBooking.setDisable(true);
        btnTilføjFirma.setDisable(true);



        /*
        lvwDeltagere.getSelectionModel().selectedItemProperty().addListener(event ->
                this.deltagerSelectionChanged(lvwDeltagere.getSelectionModel().getSelectedItem()));

         */


        pane.add(TextAreaInfo, 0, 6, 2, 3);
        TextAreaInfo.setPrefHeight(170);
        TextAreaInfo.setPrefWidth(100);


        HBox hboxVindueKnapper = new HBox();
        hboxVindueKnapper.setSpacing(15);
        hboxVindueKnapper.getChildren().add(btnAfbryd);
        hboxVindueKnapper.getChildren().add(btnOk);
        pane.add(hboxVindueKnapper,0,13);

        btnOpretDeltager.setOnAction(event -> this.opretDeltagerOnAction());
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnVælgDeltager.setOnAction(event -> this.vælgDeltagerOnAction());
        btnOpretLedsager.setOnAction(event -> this.opretLedsagerOnAction());
    }

    public void updateControls() {
        lvwDeltagere.getItems().setAll(Controller.getAlleDeltagere());
/*
        if (lvwDeltagere.getSelectionModel().getSelectedItem() != null) {
            deltagerSelectionChanged(lvwDeltagere.getSelectionModel().getSelectedItem());
        }

 */
    }

    /*
    public void deltagerSelectionChanged(Deltager deltager) {
        String temp = "";
        if (!lvwDeltagere.isDisabled()) {
            temp = TextAreaInfo.getText().trim();
            if (deltager != null) {
                temp += "\nDeltager: " + deltager;
            }
            if (deltager.findTilmeldingTilKonference(konference).getLedsager() != null) {
                temp += "\nLedsager: " + deltager.findTilmeldingTilKonference(konference).getLedsager();
            }
        }
        TextAreaInfo.setText(temp);
    }

     */

    private void opretDeltagerOnAction() {
        OpretDeltagerWindow dialog = new OpretDeltagerWindow();
        dialog.showAndWait();

        // Wait for the modal dialog to close
        if (dialog.getDeltager() != null) {
            deltager = dialog.getDeltager();
            lvwDeltagere.setDisable(true);
            String temp = TextAreaInfo.getText().trim();
            temp += "\nDeltager: " + dialog.getDeltager();
            TextAreaInfo.setText(temp);
            btnVælgDeltager.setDisable(true);
            btnTilføjFirma.setDisable(false);
            btnOpretHotelBooking.setDisable(false);
            btnOpretDeltager.setDisable(true);
        }
    }

    private void vælgDeltagerOnAction() {
        if (lvwDeltagere.getSelectionModel().getSelectedItem() != null) {
            Deltager dTemp = lvwDeltagere.getSelectionModel().getSelectedItem();
            deltager = dTemp;
            String temp = TextAreaInfo.getText().trim();
            temp += "\nDeltager: " + dTemp;
            TextAreaInfo.setText(temp);
            btnOpretDeltager.setDisable(true);
            btnVælgDeltager.setDisable(true);
            btnTilføjFirma.setDisable(false);
            btnOpretHotelBooking.setDisable(false);
        } else {
            alert.setTitle("Ingen Deltager");
            alert.setHeaderText("Du skal vælge en deltager for at tildele en tidligere deltager");
            alert.show();
        }
    }

    private void opretLedsagerOnAction() {
        OpretLedsagerWindow dialog = new OpretLedsagerWindow(konference);
        dialog.showAndWait();

        // Wait for the modal dialog to close
    }

    private void afbrydOnAction() {
        this.hide();
    }
}
