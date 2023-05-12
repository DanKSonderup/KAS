package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HotelAftale;
import model.HotelBooking;
import model.Konference;
import model.Tillæg;

public class OpretHotelBookingWindow extends Stage {
        private HotelBooking hotelBooking;
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

            lvwHotelAftaler.getSelectionModel().selectedItemProperty().addListener(event ->
                    this.hotelAftaleSelectionChanged(lvwHotelAftaler.getSelectionModel().getSelectedItem()));

        }

        private void hotelAftaleSelectionChanged (HotelAftale hotelAftale) {
            lvwHotelAftaleTillæg.getItems().setAll(hotelAftale.getTillæg());
        }
}
