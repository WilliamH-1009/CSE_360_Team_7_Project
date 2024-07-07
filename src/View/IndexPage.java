package View;
//placeholder
import Application.Controller;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Objects;

//*** This is a temporary page, only use for switching pages ***//
public class IndexPage {
    private final Stage stage;

    public IndexPage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        Button nurse_greet = new Button("Nurse Greet");
        nurse_greet.setFocusTraversable(false);
        nurse_greet.setPrefWidth(200);
        nurse_greet.setPrefHeight(80);
        nurse_greet.relocate(260, 200);
        pane.getChildren().add(nurse_greet);

        nurse_greet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showNurseGreet();
            }
        });

        Button doctor_visit = new Button("Doctor Visit");
        doctor_visit.setFocusTraversable(false);
        doctor_visit.setPrefWidth(200);
        doctor_visit.setPrefHeight(80);
        doctor_visit.relocate(560, 200);
        pane.getChildren().add(doctor_visit);

        Button patient_portal = new Button("Patient Portal");
        patient_portal.setFocusTraversable(false);
        patient_portal.setPrefWidth(200);
        patient_portal.setPrefHeight(80);
        patient_portal.relocate(860, 200);
        pane.getChildren().add(patient_portal);

        return scene;
    }
}
