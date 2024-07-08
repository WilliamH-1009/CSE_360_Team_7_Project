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

        doctor_visit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showDoctorExamination();
            }
        });

        Button patient_portal = new Button("Patient Portal");
        patient_portal.setFocusTraversable(false);
        patient_portal.setPrefWidth(200);
        patient_portal.setPrefHeight(80);
        patient_portal.relocate(860, 200);
        pane.getChildren().add(patient_portal);

        patient_portal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showPatientPortal();
            }
        });

        Button logout = new Button("Log Out");
        logout.setFocusTraversable(false);
        logout.setPrefWidth(200);
        logout.setPrefHeight(80);
        logout.relocate(560, 600);
        pane.getChildren().add(logout);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showLoginPage();
            }
        });

        return scene;
    }
}