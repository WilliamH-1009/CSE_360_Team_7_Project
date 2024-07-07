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

public class PatientHealthHistory {
    private final Stage stage;

    public PatientHealthHistory(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Patient Health History");
        title.getStyleClass().add("title");
        title.relocate(360, 80);
        pane.getChildren().add(title);

        // allergies input
        Text label_allergies = new Text("Allergies");
        label_allergies.relocate(150, 150);
        pane.getChildren().add(label_allergies);

        TextArea input_allergies = new TextArea();
        input_allergies.getStyleClass().add("text-area");
        input_allergies.setFocusTraversable(false);
        input_allergies.setEditable(false);
        input_allergies.setPrefWidth(300);
        input_allergies.setPrefHeight(150);
        input_allergies.relocate(150, 180);
        pane.getChildren().add(input_allergies);

        // conditions input
        Text label_conditions = new Text("Health Conditions");
        label_conditions.relocate(150, 400);
        pane.getChildren().add(label_conditions);

        TextArea input_conditions = new TextArea();
        input_conditions.getStyleClass().add("text-area");
        input_conditions.setFocusTraversable(false);
        input_conditions.setEditable(false);
        input_conditions.setPrefWidth(300);
        input_conditions.setPrefHeight(150);
        input_conditions.relocate(150, 430);
        pane.getChildren().add(input_conditions);

        // new allergy input
        Text label_new_allergy = new Text("Add New Allergy");
        label_new_allergy.relocate(520, 180);
        pane.getChildren().add(label_new_allergy);

        TextField input_new_allergy = new TextField();
        input_new_allergy.setFocusTraversable(false);
        input_new_allergy.setPrefWidth(250);
        input_new_allergy.setPrefHeight(40);
        input_new_allergy.relocate(520, 210);
        pane.getChildren().add(input_new_allergy);

        // add new allergy button
        Button add_allergy = new Button("Add");
        add_allergy.setFocusTraversable(false);
        add_allergy.setPrefWidth(130);
        add_allergy.setPrefHeight(40);
        add_allergy.relocate(520, 280);
        pane.getChildren().add(add_allergy);

        add_allergy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input_allergies.appendText(input_new_allergy.getText() + "\n");
                input_new_allergy.setText("");
            }
        });

        // new condition input
        Text label_new_condition = new Text("Add New Condition");
        label_new_condition.relocate(520, 440);
        pane.getChildren().add(label_new_condition);

        TextField input_new_condition = new TextField();
        input_new_condition.setFocusTraversable(false);
        input_new_condition.setPrefWidth(250);
        input_new_condition.setPrefHeight(40);
        input_new_condition.relocate(520, 470);
        pane.getChildren().add(input_new_condition);

        // add new condition button
        Button add_condition = new Button("Add");
        add_condition.setFocusTraversable(false);
        add_condition.setPrefWidth(130);
        add_condition.setPrefHeight(40);
        add_condition.relocate(520, 540);
        pane.getChildren().add(add_condition);

        add_condition.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                input_conditions.appendText(input_new_condition.getText() + "\n");
                input_new_condition.setText("");
            }
        });

        // prescriptions input
        Text label_prescriptions = new Text("Previous Prescriptions");
        label_prescriptions.relocate(900, 150);
        pane.getChildren().add(label_prescriptions);

        TextArea input_prescriptions = new TextArea();
        input_prescriptions.getStyleClass().add("text-area");
        input_prescriptions.setFocusTraversable(false);
        input_prescriptions.setEditable(false);
        input_prescriptions.setPrefWidth(300);
        input_prescriptions.setPrefHeight(150);
        input_prescriptions.relocate(900, 180);
        pane.getChildren().add(input_prescriptions);

        // immunizations input
        Text label_immunizations = new Text("Previous Immunizations");
        label_immunizations.relocate(900, 400);
        pane.getChildren().add(label_immunizations);

        TextArea input_immunizations = new TextArea();
        input_immunizations.getStyleClass().add("text-area");
        input_immunizations.setFocusTraversable(false);
        input_immunizations.setEditable(false);
        input_immunizations.setPrefWidth(300);
        input_immunizations.setPrefHeight(150);
        input_immunizations.relocate(900, 430);
        pane.getChildren().add(input_immunizations);

        // proceed button
        Button proceed = new Button(" Proceed to\nExamination");
        proceed.setFocusTraversable(false);
        proceed.setPrefWidth(250);
        proceed.setPrefHeight(80);
        proceed.relocate(520, 670);
        pane.getChildren().add(proceed);

        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String allergies = "";
                for (String line : input_allergies.getText().split("\\n")) {
                    allergies += line + ",";
                }
                String conditions = "";
                for (String line : input_conditions.getText().split("\\n")) {
                    conditions += line + ",";
                }
                controller.savePatientHealthHistory(allergies, conditions);
            }
        });

        // set patient health history data
        Map<String, String> health_history_data = controller.getPatientHealthHistory();
        for (Map.Entry<String, String> entry : health_history_data.entrySet()) {
            switch (entry.getKey()) {
                case "Prescriptions":
                    input_prescriptions.setText(entry.getValue().replaceAll(",", "\n"));
                case "Immunizations":
                    input_immunizations.setText(entry.getValue().replaceAll(",", "\n"));
                case "Allergies":
                    input_allergies.setText(entry.getValue().replaceAll(",", "\n"));
                case "Conditions":
                    input_conditions.setText(entry.getValue().replaceAll(",", "\n"));
            }
        }


        return scene;
    }
}
