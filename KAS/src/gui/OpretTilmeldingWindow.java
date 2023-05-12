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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import storage.Storage;

import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
    private final TextField txfAnkomstDato = new TextField();
    private final TextField txfAfrejseDato = new TextField();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOk = new Button("Opret tilmelding");
    private final Button btnOpretDeltager = new Button("Opret deltager");
    private final Button btnOpretHotelBooking = new Button("Opret hotelbooking");
    private final Button btnOpretLedsager = new Button("Tilføj ledsager");
    private final Button btnVælgDeltager = new Button("Brug tidligere deltager");
    private final Button btnTilføjFirma = new Button("Tilføj Firma");
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final CheckBox cbxForedagsholder = new CheckBox("Foredragsholder");

    //-------------------------------------------------
    // Data Felter til oprettelse af Tilmelding
    private Deltager deltager;
    private Ledsager ledsager;
    private Firma firma;
    private HotelAftale hotelAftale;
    private ArrayList<Tillæg> valgteTillæg = new ArrayList<>();

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
        btnOpretLedsager.setDisable(true);
        btnOk.setDisable(true);



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
        btnTilføjFirma.setOnAction(event -> this.tilføjFirmaOnAction());
        btnOpretHotelBooking.setOnAction(event -> this.opretHotelBookingOnAction());
        btnOk.setOnAction(event -> this.opretTilmeldingOnAction());
    }

    public void updateControls() {
        lvwDeltagere.getItems().setAll(Controller.getAlleDeltagere());
    }

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
            btnOpretDeltager.setDisable(true);
            btnTilføjFirma.setDisable(false);
            btnOpretHotelBooking.setDisable(false);
            btnOpretLedsager.setDisable(false);
            btnOk.setDisable(false);
        }
    }

    private void vælgDeltagerOnAction() {
        if (lvwDeltagere.getSelectionModel().getSelectedItem() != null) {
            deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
            String temp = TextAreaInfo.getText().trim();
            temp += "\nDeltager: " + deltager;
            TextAreaInfo.setText(temp);
            btnOpretDeltager.setDisable(true);
            btnVælgDeltager.setDisable(true);
            btnTilføjFirma.setDisable(false);
            btnOpretHotelBooking.setDisable(false);
            btnOpretLedsager.setDisable(false);
            btnOk.setDisable(false);
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

        if (dialog.getLedsager() != null) {
            ledsager = dialog.getLedsager();
            String temp = TextAreaInfo.getText().trim();
            temp += "\n\nLedsager: " + ledsager;
            if (ledsager.getUdflugter().size() > 0) {
                temp += "\n" + ledsager + "'s udflugter: " + ledsager.getUdflugter();
            }
            TextAreaInfo.setText(temp);
            btnOpretLedsager.setDisable(true);
        }
    }

    private void tilføjFirmaOnAction() {
        TilføjFirmaWindow dialog = new TilføjFirmaWindow();
        dialog.showAndWait();

        // Wait for the modal dialog to close
        if (dialog.getFirma() != null) {
            firma = dialog.getFirma();
            String temp = TextAreaInfo.getText().trim();
            temp += "\n\nFirma: " + firma;
            TextAreaInfo.setText(temp);
            btnTilføjFirma.setDisable(true);
        }
    }

    private void opretHotelBookingOnAction() {
        OpretHotelBookingWindow dialog = new OpretHotelBookingWindow(konference);
        dialog.showAndWait();

        // Wait for the modal dialog to close

        if (dialog.getHotelAftale() != null) {
            hotelAftale = dialog.getHotelAftale();
            valgteTillæg = dialog.getValgteTillæg();
            String temp = TextAreaInfo.getText().trim();
            temp += "\n\nHotelbooking på: " + hotelAftale.getNavn();
            if (dialog.getValgteTillæg().size() > 0) {
                temp += "\nTillæg:" + valgteTillæg;
            }
            TextAreaInfo.setText(temp);
            btnOpretHotelBooking.setDisable(true);
        }
    }

    public void opretTilmeldingOnAction() {
        String ankomstDatoString = txfAnkomstDato.getText().trim();
        String afrejseDatoString = txfAfrejseDato.getText().trim();
        LocalDate ankomstDato = null;
        LocalDate afrejseDato = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        try {
            ankomstDato = LocalDate.parse(ankomstDatoString, formatter);
            afrejseDato = LocalDate.parse(afrejseDatoString, formatter);
        }
        catch (DateTimeParseException e) {
            alert.setTitle("Ugyldig dato");
            alert.setHeaderText("En af dine datoer er ikke en gyldig dato");
            alert.show();
            return;
        }
        if (afrejseDato.isBefore(ankomstDato)) {
            alert.setTitle("Ugyldig dato");
            alert.setHeaderText("Din slutdato er før din startdato");
            alert.show();
            return;
        }
        if (afrejseDato.isAfter(konference.getSlutDato()) || ankomstDato.isBefore(konference.getStartDato())) {
            alert.setTitle("Ugyldig dato");
            alert.setHeaderText("Dine valgte ankomst eller afrejse dato er udenfor konferencens datoer");
            alert.show();
            return;
        }
        Tilmelding h1 = Controller.createTilmelding(konference,deltager,ankomstDato,afrejseDato, cbxForedagsholder.isSelected());
        Controller.addLedsagerTilTilmelding(h1, ledsager);
        this.hide();
    }

    private void afbrydOnAction() {
        this.hide();
    }
}
