package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Firma;

public class TilføjFirmaWindow extends Stage {

    private Firma firma;
    public TilføjFirmaWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Tilføj Firma");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------
    // Data felter

    private final TextField txfNavn = new TextField();
    private final TextField txfTelefonnummer = new TextField();
    private final Button btnOpret = new Button("Opret Firma");
    private final Button btnAfbryd = new Button("Afbryd");
    private final Label lblError = new Label();
    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblNavn = new Label("Navn:");
        Label lblTelefonnummer = new Label("Telefonnummer: ");
        pane.add(lblNavn,0,0);
        pane.add(lblTelefonnummer,0,1);

        pane.add(txfNavn,1,0);
        pane.add(txfTelefonnummer,1,1);

        HBox hbox1 = new HBox();
        hbox1.setSpacing(15);
        hbox1.getChildren().add(btnOpret);
        hbox1.getChildren().add(btnAfbryd);

        pane.add(hbox1,1,2);
        pane.add(lblError,1,3);
        lblError.setVisible(false);

        btnAfbryd.setOnAction(event -> this.afbrydOnAction());
        btnOpret.setOnAction(event -> this.opretOnAction());
    }

    private void afbrydOnAction() {
        this.hide();
    }

    private void opretOnAction() {
        String navn = txfNavn.getText().trim();
        String tlf = txfTelefonnummer.getText().trim();
        if (navn.length() < 1) {
            lblError.setText("Dit navn er tomt");
            lblError.setVisible(true);
            return;
        }
        if (tlf.length() < 1) {
            lblError.setText("Dit tlfnummer er tomt");
            lblError.setVisible(true);
            return;
        }
        firma = new Firma(navn, tlf);
        this.hide();
    }

    public Firma getFirma() {
        return firma;
    }
}
