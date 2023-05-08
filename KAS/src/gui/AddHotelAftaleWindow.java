package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Tillæg;

public class AddHotelAftaleWindow extends Stage {

    Konference konference;

    public AddHotelAftaleWindow(Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.konference = konference;

        this.setTitle("Opret Hotel");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final TextField txfNavn = new TextField();
    private final TextField txfLokation = new TextField();
    private final TextField txfPrisPrNatEnkeltVærelse = new TextField();
    private final TextField txfPrisPrNatDobbeltVærelse = new TextField();
    private final ListView<Tillæg> lvwTillæg = new ListView<>();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOk = new Button("Ok");

    public void initContent(GridPane pane) {
        String[] labelStrenge = {"Navn:", "Lokation", "Pris per nat enkeltværelse:", "Pris per nat dobbeltværelse:"};
        TextField[] tekstFelter = {txfNavn, txfLokation, txfPrisPrNatEnkeltVærelse, txfPrisPrNatDobbeltVærelse};

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblInformation = new Label("Information");
        pane.add(lblInformation,0,0);
        for (int i = 0; i < 4; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1,0,i+1);
            pane.add(tekstFelter[i],1,i+1);
        }
        Label lblTillæg = new Label("Tillæg");
        pane.add(lblTillæg,3,0);
        pane.add(lvwTillæg,3,1,1,4);
        lvwTillæg.setMaxHeight(150);
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOk);
        pane.add(hboxButtons,3,7);

        // Tilføjer action events til knapper
        btnAfbryd.setOnAction(event -> this.afbrydOnAction());

    }

    public void afbrydOnAction() {
        this.hide();
    }
}
