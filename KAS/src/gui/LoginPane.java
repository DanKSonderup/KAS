package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class LoginPane extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Konference Administration System");
        GridPane pane = new GridPane();
        this.initContent(pane);
        pane.setMinHeight(600);
        pane.setMinWidth(800);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    //-----------------------------------------------------------
    // Data felter til Gridpane indsættes her

    private final TextField txfBrugernavn = new TextField();
    private final TextField txfKodeord = new TextField();
    private final Button btnLogin = new Button("Login");

    // Vær obs på at dette på ingen måde er en sikker måde at gemme et password på
    // reelt set bør vi kører kodeord igennem en hashing algoritme som f.eks. SHA
    private String correctUsername = "admin";
    private String correctPassword = "admin";

    private void initContent(GridPane pane) {

        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        // Backgrounds styling & Header Label

        pane.setStyle("-fx-background-color: linear-gradient(to right, #4e54c8, #8f94fb);");

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(50);
        pane.add(vbox1,12,2);
        Label lblVelkommen = new Label("Velkommen til KAS");
        lblVelkommen.setFont(new Font("Sans-Serif", 60));
        lblVelkommen.setTextFill(Color.WHITE);
        lblVelkommen.setAlignment(Pos.CENTER);
        vbox1.getChildren().add(lblVelkommen);

        // Login Form
        Label lblLogin = new Label("Login");
        lblLogin.setFont(new Font("Sans-Serif", 20));
        vbox1.getChildren().add(lblLogin);

        VBox vbox2 = new VBox();
        pane.add(vbox2,12,5);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(5);

        Label lblBrugernavn = new Label("Brugernavn");
        vbox2.getChildren().add(lblBrugernavn);
        txfBrugernavn.setMaxWidth(150);
        vbox2.getChildren().add(txfBrugernavn);

        Label lblKodeord = new Label("Kodeord");
        vbox2.getChildren().add(lblKodeord);
        txfKodeord.setMaxWidth(150);
        vbox2.getChildren().add(txfKodeord);
        vbox2.getChildren().add(btnLogin);
        btnLogin.setOnAction(event -> this.loginOnAction());
    }

    public void loginOnAction() {
        String brugernavnInput = txfBrugernavn.getText().trim();
        String passwordInput =txfKodeord.getText().trim();

        if (brugernavnInput.equals(correctUsername) && passwordInput.equals(correctPassword)) {
            KonferencePane dialog = new KonferencePane();
            dialog.start(new Stage());
        }
    }
}
