package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HotelAftale;
import model.HotelBooking;
import model.Konference;
import model.Tillæg;

import java.util.ArrayList;

public class OpretHotelBookingWindow extends Stage {
        private HotelAftale hotelAftale;
        private ArrayList<Tillæg> valgteTillæg;
        private Konference konference;

        public OpretHotelBookingWindow(Konference konference) {
            this.initStyle(StageStyle.UTILITY);
            this.initModality(Modality.APPLICATION_MODAL);
            this.setResizable(false);
            this.konference = konference;
            this.setTitle("Opret Hotelbooking");
            GridPane pane = new GridPane();
            this.initContent(pane);

            Scene scene = new Scene(pane);
            this.setScene(scene);
        }

        //--------------------------------------------------
        // Data felter
        private final ListView<HotelAftale> lvwHotelAftaler = new ListView<>();
        private final ListView<Tillæg> lvwHotelAftaleTillæg = new ListView<>();
        private final Button btnOpretHotelBooking = new Button("Opret Booking");
        private final Button btnAfbryd = new Button("Afbryd");
        private final Alert alert = new Alert(Alert.AlertType.INFORMATION);



    public void initContent (GridPane pane) {
            pane.setPadding(new Insets(20));
            // set horizontal gap between components
            pane.setHgap(10);
            // set vertical gap between components
            pane.setVgap(10);

            lvwHotelAftaler.getItems().setAll(konference.getHotelaftaler());



            Label lblHotelAftaler = new Label("Hotelaftaler");
            pane.add(lblHotelAftaler,0,0);
            pane.add(lvwHotelAftaler,0,1);

            Label lblHotelTillæg = new Label("Marker de ønskede tillæg");
            pane.add(lblHotelTillæg,1,0);
            pane.add(lvwHotelAftaleTillæg,1,1);
            lvwHotelAftaleTillæg.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            lvwHotelAftaler.getSelectionModel().selectedItemProperty().addListener(event ->
                    this.hotelAftaleSelectionChanged(lvwHotelAftaler.getSelectionModel().getSelectedItem()));

            pane.add(btnOpretHotelBooking,1,4);
            btnOpretHotelBooking.setOnAction(event -> this.opretHotelBookingOnAction());

            pane.add(btnAfbryd,0,4);
            btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        }

        private void hotelAftaleSelectionChanged (HotelAftale hotelAftale) {
            lvwHotelAftaleTillæg.getItems().setAll(hotelAftale.getTillæg());
        }

        private void opretHotelBookingOnAction() {
            if (lvwHotelAftaler.getSelectionModel().getSelectedItem() != null) {
                hotelAftale = lvwHotelAftaler.getSelectionModel().getSelectedItem();
                valgteTillæg = new ArrayList<>(lvwHotelAftaleTillæg.getSelectionModel().getSelectedItems());
                this.hide();
            } else {
                alert.setTitle("Ingen Hotel");
                alert.setHeaderText("Du skal vælge et Hotel for at oprette en booking");
                alert.show();
            }
        }

    public HotelAftale getHotelAftale() {
        return hotelAftale;
    }

    public void afbrydOnAction() {
        this.hide();
    }

    public ArrayList<Tillæg> getValgteTillæg() {
        return valgteTillæg;
    }
}
