package gui;

import controller.Controller;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Deltager;
import model.Konference;

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
    private final Button btnOk = new Button("Ok");
    private final Button btnOpretDeltager = new Button("Opret deltager");
    private final Button btnOpretHotelBooking = new Button("Opret hotelbooking");
    private final Button btnOpretLedsager = new Button("Opret ledsager");
    private final Label lblError = new Label();
    private final CheckBox cbxForedagsholder = new CheckBox("Foredragsholder");

    private static int counter = 0;


    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        updateControls();

        Label lblDeltager = new Label("Deltagere");
        pane.add(lblDeltager, 0, 0);
        pane.add(lvwDeltagere, 0, 1, 2, 4);
        lvwDeltagere.setPrefHeight(170);
        lvwDeltagere.setPrefWidth(100);

        Label lblAnkomstDato = new Label("Ankomstdato:");
        pane.add(lblAnkomstDato, 0, 5);
        pane.add(txfAnkomstDato, 1, 5);

        Label lblAfrejseDato = new Label("Afrejsedato:");
        pane.add(lblAfrejseDato, 0, 6);
        pane.add(txfAfrejseDato, 1, 6);
        pane.add(cbxForedagsholder, 1, 7);
        pane.add(btnOpretDeltager, 2, 1);
        pane.add(btnOpretHotelBooking, 2, 2);
        pane.add(btnOpretLedsager, 2, 3);
        btnOpretLedsager.setDisable(true);

        lvwDeltagere.getSelectionModel().selectedItemProperty().addListener(event ->
                this.deltagerSelectionChanged(lvwDeltagere.getSelectionModel().getSelectedItem()));
        /*
        lvwDeltagere.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldDeltager, newDeltager) -> {
                    if (newDeltager != null) {
                        TextAreaInfo.setText("" + newDeltager.getNavn());
                    }
                    });

         */



        pane.add(TextAreaInfo, 4, 1, 2, 4);
        TextAreaInfo.setPrefHeight(170);
        TextAreaInfo.setPrefWidth(100);

        Label lblFirma = new Label("Firma:");
        pane.add(lblFirma, 3, 5);

        Label lblNavn = new Label("Navn:");
        pane.add(lblNavn, 3, 6);
        pane.add(txfNavn, 4, 6);

        Label lblTelefonNummer = new Label("Tlf.nr.");
        pane.add(lblTelefonNummer, 3, 7);
        pane.add(txfTelefonNr, 4, 7);

        pane.add(btnAfbryd, 3, 8);
        pane.add(btnOk, 4, 8);
    }

    public void updateControls() {
        lvwDeltagere.getItems().setAll(Controller.getAlleDeltagerForKonference(konference));
    }

    public void deltagerSelectionChanged(Deltager deltager) {
        StringBuilder sb = new StringBuilder();
        if (deltager != null) {
            sb.append("Navn: " + deltager.getNavn());
        }
        deltager.findTilmeldingTilKonference(konference);
        /*
        if (deltager.findTilmeldingTilKonference(konference) != null) {
            sb.append("Ledsager: " + deltager.findTilmeldingTilKonference(konference).getDeltager());
        }

         */
        TextAreaInfo.setText("" + sb);
    }
}
