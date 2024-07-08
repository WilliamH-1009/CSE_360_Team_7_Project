package View;

import Application.Controller;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
        title.relocate(480, 50);
        pane.getChildren().add(title);

        // patient info label
        Text label_patient_info = new Text("Patient Information");
        label_patient_info.setStyle("-fx-font-weight: bold");
        label_patient_info.relocate(100, 130);
        pane.getChildren().add(label_patient_info);

        // patient first name text
        Text label_first_name = new Text("First Name: ");
        label_first_name.getStyleClass().add("small-text");
        label_first_name.relocate(100, 170);
        pane.getChildren().add(label_first_name);

        // patient last name text
        Text label_last_name = new Text("Last Name: ");
        label_last_name.getStyleClass().add("small-text");
        label_last_name.relocate(100, 210);
        pane.getChildren().add(label_last_name);

        // patient date of birth text
        Text label_birthday = new Text("Date of Birth: ");
        label_birthday.getStyleClass().add("small-text");
        label_birthday.relocate(100, 250);
        pane.getChildren().add(label_birthday);

        // patient weight text
        Text label_weight = new Text("Weight: ");
        label_weight.getStyleClass().add("small-text");
        label_weight.relocate(100, 290);
        pane.getChildren().add(label_weight);

        // patient height text
        Text label_height = new Text("Height: ");
        label_height.getStyleClass().add("small-text");
        label_height.relocate(100, 330);
        pane.getChildren().add(label_height);

        // patient temperature text
        Text label_temperature = new Text("Body Temperature: ");
        label_temperature.getStyleClass().add("small-text");
        label_temperature.relocate(100, 370);
        pane.getChildren().add(label_temperature);

        // patient pressure text
        Text label_pressure = new Text("Blood Pressure: ");
        label_pressure.getStyleClass().add("small-text");
        label_pressure.relocate(100, 410);
        pane.getChildren().add(label_pressure);

        // past prescriptions input
        Text label_past_prescriptions = new Text("Past Prescriptions");
        label_past_prescriptions.relocate(450, 130);
        pane.getChildren().add(label_past_prescriptions);

        TextArea input_past_prescriptions = new TextArea();
        input_past_prescriptions.setFocusTraversable(false);
        input_past_prescriptions.setEditable(false);
        input_past_prescriptions.setPrefWidth(300);
        input_past_prescriptions.setPrefHeight(150);
        input_past_prescriptions.relocate(450, 160);
        pane.getChildren().add(input_past_prescriptions);

        // past immunizations input
        Text label_past_immunizations = new Text("Past Immunizations");
        label_past_immunizations.relocate(450, 350);
        pane.getChildren().add(label_past_immunizations);

        TextArea input_past_immunizations = new TextArea();
        input_past_immunizations.setFocusTraversable(false);
        input_past_immunizations.setEditable(false);
        input_past_immunizations.setPrefWidth(300);
        input_past_immunizations.setPrefHeight(150);
        input_past_immunizations.relocate(450, 380);
        pane.getChildren().add(input_past_immunizations);

        // new prescriptions input
        Text label_new_prescriptions = new Text("New Prescriptions");
        label_new_prescriptions.relocate(900, 130);
        pane.getChildren().add(label_new_prescriptions);

        TextArea input_new_prescriptions = new TextArea();
        input_new_prescriptions.setFocusTraversable(false);
        input_new_prescriptions.setPrefWidth(300);
        input_new_prescriptions.setPrefHeight(150);
        input_new_prescriptions.relocate(900, 160);
        pane.getChildren().add(input_new_prescriptions);

        // diagnoses input
        Text label_diagnoses = new Text("Diagnoses");
        label_diagnoses.relocate(900, 350);
        pane.getChildren().add(label_diagnoses);

        TextArea input_diagnoses = new TextArea();
        input_diagnoses.setFocusTraversable(false);
        input_diagnoses.setPrefWidth(300);
        input_diagnoses.setPrefHeight(150);
        input_diagnoses.relocate(900, 380);
        pane.getChildren().add(input_diagnoses);

        // save examination results button
        Button save_examination = new Button("Save\nExamination Results");
        save_examination.setFocusTraversable(false);
        save_examination.setPrefWidth(300);
        save_examination.setPrefHeight(80);
        save_examination.relocate(900, 600);
        pane.getChildren().add(save_examination);

        // save examination results action
        save_examination.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String prescriptions = "";
                for (String line : input_new_prescriptions.getText().split("\\n")) {
                    prescriptions += line + ",";
                }
                String diagnoses = "";
                for (String line : input_diagnoses.getText().split("\\n")) {
                    diagnoses += line + ",";
                }
                controller.saveDoctorExamResults(prescriptions, diagnoses);
            }
        });

        // set patient info data
        Map<String, String> patient_info_data = controller.getAllPatientInfo();
        for (Map.Entry<String, String> entry : patient_info_data.entrySet()) {
            switch (entry.getKey()) {
                case "FirstName":
                    label_first_name.setText(label_first_name.getText() + entry.getValue());
                    break;
                case "LastName":
                    label_last_name.setText(label_last_name.getText() + entry.getValue());
                    break;
                case "Birthday":
                    label_birthday.setText(label_birthday.getText() + entry.getValue());
                    break;
                case "Weight":
                    label_weight.setText(label_weight.getText() + entry.getValue() + " lbs.");
                    break;
                case "Height_ft":
                    label_height.setText(label_height.getText() + entry.getValue() + "' ");
                    break;
                case "Height_in":
                    label_height.setText(label_height.getText() + entry.getValue() + "\"");
                    break;
                case "Temperature":
                    label_temperature.setText(label_temperature.getText() + entry.getValue() + " °F");
                    break;
                case "Pressure1":
                    label_pressure.setText(label_pressure.getText() + entry.getValue() + " / ");
                    break;
                case "Pressure2":
                    label_pressure.setText(label_pressure.getText() + entry.getValue() + " mmHg");
                    break;
            }
        }

        // set patient health history data
        Map<String, String> health_history_data = controller.getPatientHealthHistory();
        for (Map.Entry<String, String> entry : health_history_data.entrySet()) {
            switch (entry.getKey()) {
                case "Prescriptions":
                    input_past_prescriptions.setText(entry.getValue().replaceAll(",", "\n"));
                    break;
                case "Immunizations":
                    input_past_immunizations.setText(entry.getValue().replaceAll(",", "\n"));
                    break;
            }
        }

        // return to menu
        Button back = new Button("Back");
        back.setFocusTraversable(false);
        back.setPrefWidth(100);
        back.setPrefHeight(30);
        back.relocate(20, 20);
        pane.getChildren().add(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showIndexPage();
            }
        });

        return scene;
    }
}