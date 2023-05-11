package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Deltager;
import model.Konference;
import javafx.scene.control.*;

public class OpretDeltagerWindow extends Stage {

    private Deltager deltager;
    public OpretDeltagerWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Opret Deltager");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter
    private final TextField txfNavn = new TextField();
    private final TextField txfAdresse = new TextField();
    private final TextField txfTelefonNummer = new TextField();
    private final TextField txfBy = new TextField();
    private final TextField txfLand = new TextField();

    private final Button btnOpretDeltager = new Button("Opret deltager");
    private final Button btnAfbryd = new Button("Afbryd");
    private String[] labelStrings = {"Navn:", "Adresse:", "Telefonnummer:", "By:", "Land:"};
    private TextField[] tekstFelter = {txfNavn, txfAdresse, txfTelefonNummer, txfBy, txfLand};
    private Label lblError = new Label();

    public void initContent (GridPane pane) {

        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        for (int i = 0; i < 5; i++) {
            Label lb1 = new Label(labelStrings[i]);
            pane.add(lb1,0,i);
            pane.add(tekstFelter[i],1,i);
        }

        HBox hbox1 = new HBox();
        hbox1.setSpacing(15);
        pane.add(hbox1,2,6);
        hbox1.getChildren().add(btnAfbryd);
        hbox1.getChildren().add(btnOpretDeltager);

        pane.add(lblError,1,6);

        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpretDeltager.setOnAction(event -> this.opretDeltagerOnAction());

    }

    private void afbrydOnAction() {
        this.hide();
    }

    private void opretDeltagerOnAction() {
        int i = 0;

        while (i < 5) {
            if (tekstFelter[i].getText().trim().length() < 1) {
                lblError.setText("" + labelStrings[i] + " er tom");
                lblError.setTextFill(Color.RED);
                return;
            }
            i++;
        }
        String navn = txfNavn.getText().trim();
        String adresse = txfAdresse.getText().trim();
        String telefonnummer = txfTelefonNummer.getText().trim();
        String by = txfBy.getText().trim();
        String land = txfLand.getText().trim();
        deltager = new Deltager(navn,adresse,telefonnummer,by,land);
        this.hide();
    }

    public Deltager getDeltager() {
        return deltager;
    }
}
