package View;

import Application.Controller;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Objects;

public class PatientHealthInfo {
    private final Stage stage;

    public PatientHealthInfo(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Patient Health Information");
        title.getStyleClass().add("title");
        title.relocate(510, 130);
        pane.getChildren().add(title);

        return scene;
    }
}
