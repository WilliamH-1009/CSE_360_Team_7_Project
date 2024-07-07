package View;

import Application.Controller;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class DoctorExamination {
    private final Stage stage;

    public DoctorExamination(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Doctor Examination");
        title.getStyleClass().add("title");
        title.relocate(510, 50);
        pane.getChildren().add(title);

        // patient first name input
        Text label_first_name = new Text("First Name:");
        label_first_name.relocate(50, 150);
        pane.getChildren().add(label_first_name);

        TextField input_first_name = new TextField();
        input_first_name.setFocusTraversable(false);
        input_first_name.setPrefWidth(200);
        input_first_name.setPrefHeight(40);
        input_first_name.relocate(200, 130);
        pane.getChildren().add(input_first_name);

        // patient last name input
        Text label_last_name = new Text("Last Name:");
        label_last_name.relocate(450, 150);
        pane.getChildren().add(label_last_name);

        TextField input_last_name = new TextField();
        input_last_name.setFocusTraversable(false);
        input_last_name.setPrefWidth(200);
        input_last_name.setPrefHeight(40);
        input_last_name.relocate(600, 130);
        pane.getChildren().add(input_last_name);

        // patient date of birth input
        Text label_birthday = new Text("Date Of Birth:");
        label_birthday.relocate(50, 250);
        pane.getChildren().add(label_birthday);

        DatePicker input_birthday = new DatePicker();
        input_birthday.setFocusTraversable(false);
        input_birthday.setPrefWidth(200);
        input_birthday.setPrefHeight(40);
        input_birthday.relocate(200, 230);
        pane.getChildren().add(input_birthday);

        // findings input
        Text label_findings = new Text("Physical Findings:");
        label_findings.relocate(50, 350);
        pane.getChildren().add(label_findings);

        TextArea input_findings = new TextArea();
        input_findings.setPrefWidth(500);
        input_findings.setPrefHeight(100);
        input_findings.relocate(200, 340);
        pane.getChildren().add(input_findings);

        // medication input
        Text label_medication = new Text("Prescription Medication:");
        label_medication.relocate(50, 500);
        pane.getChildren().add(label_medication);

        TextField input_medication = new TextField();
        input_medication.setFocusTraversable(false);
        input_medication.setPrefWidth(300);
        input_medication.setPrefHeight(40);
        input_medication.relocate(200, 490);
        pane.getChildren().add(input_medication);

        // save examination info button
        Button save_examination = new Button("Save Examination Info");
        save_examination.setFocusTraversable(false);
        save_examination.setPrefWidth(200);
        save_examination.setPrefHeight(40);
        save_examination.relocate(600, 600);
        pane.getChildren().add(save_examination);

        // save examination info action
        save_examination.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.saveExaminationInfo(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue(),
                        input_findings.getText(), input_medication.getText());
            }
        });

        // load patient history button
        Text label_history = new Text("Patient History:");
        label_history.setStyle("-fx-font-weight: bold");
        label_history.relocate(50, 700);
        pane.getChildren().add(label_history);

        TextArea history_area = new TextArea();
        history_area.setPrefWidth(900);
        history_area.setPrefHeight(200);
        history_area.relocate(200, 700);
        pane.getChildren().add(history_area);

        Button load_history = new Button("Load History");
        load_history.setFocusTraversable(false);
        load_history.setPrefWidth(200);
        load_history.setPrefHeight(40);
        load_history.relocate(1100, 700);
        pane.getChildren().add(load_history);

        // load patient history action
        load_history.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String history = controller.getPatientHistory(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue());
                history_area.setText(history);
            }
        });

        return scene;
    }
}