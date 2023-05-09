package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;

public class AddUdflugtWindow extends Stage {

    public AddUdflugtWindow() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret Udflugt");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private final TextField txfNavn = new TextField();
    private final TextField txfDato = new TextField();
    private final TextField txfLokation = new TextField();
    private final TextField txfPris = new TextField();
    private final CheckBox cbFrokost = new CheckBox();
    private final Button btnAfbryd = new Button("Afbryd");
    private final Button btnOk = new Button("Ok");
    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        String[] labelStrenge = {"Navn:", "Dato:", "Lokation:", "Pris:"};
        TextField[] tekstFelter = {txfNavn, txfDato, txfLokation, txfPris};

        for (int i = 0; i < labelStrenge.length - 1; i++) {
            Label lbl1 = new Label(labelStrenge[i]);
            pane.add(lbl1, 0, i);
            pane.add(tekstFelter[i], 1, i);
        }
        Label lblFrokost = new Label("Frokost:");
        pane.add(lblFrokost, 0, 4);
        pane.add(cbFrokost, 1, 4);

        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(20);
        hboxButtons.getChildren().add(btnAfbryd);
        hboxButtons.getChildren().add(btnOk);
        pane.add(hboxButtons, 3, 5);


    }
}
