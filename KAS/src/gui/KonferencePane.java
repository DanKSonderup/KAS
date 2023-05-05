package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Konference;

public class KonferencePane extends Application {

    public void start(Stage stage) {
        Image image = new Image("ressources/kas2.png", 100, 100, false, false);
        stage.setTitle("Konference Administration System");
        stage.getIcons().add(image);
        GridPane pane = new GridPane();
        this.initContent(pane);
        pane.setMinHeight(600);
        pane.setMinWidth(800);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    //--------------------------------------------------
    // Data felter

    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final Button btnOpretKonference = new Button("Opret Konference");

    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
        lvwKonferencer.setMinWidth(500);
        pane.add(lvwKonferencer,0,0);
        pane.add(btnOpretKonference,0,2);
    }

}
