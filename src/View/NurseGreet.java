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

public class NurseGreet {

    private final Stage stage;

    public NurseGreet(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {

        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Nurse Greet");
        title.getStyleClass().add("title");
        title.relocate(510, 130);
        pane.getChildren().add(title);

        // patient first name input
        Text label_first_name = new Text("Patient First Name:");
        label_first_name.relocate(90, 230);
        pane.getChildren().add(label_first_name);

        TextField input_first_name = new TextField();
        input_first_name.setFocusTraversable(false);
        input_first_name.setPrefWidth(270);
        input_first_name.setPrefHeight(40);
        input_first_name.relocate(330, 210);
        pane.getChildren().add(input_first_name);

        // patient last name input
        Text label_last_name = new Text("Patient Last Name:");
        label_last_name.relocate(90, 290);
        pane.getChildren().add(label_last_name);

        TextField input_last_name = new TextField();
        input_last_name.setFocusTraversable(false);
        input_last_name.setPrefWidth(270);
        input_last_name.setPrefHeight(40);
        input_last_name.relocate(330, 270);
        pane.getChildren().add(input_last_name);

        // patient date of birth input
        Text label_birthday = new Text("Patient Date Of Birth:");
        label_birthday.relocate(90, 350);
        pane.getChildren().add(label_birthday);

        DatePicker input_birthday = new DatePicker();
        input_birthday.setFocusTraversable(false);
        input_birthday.setPrefWidth(200);
        input_birthday.setPrefHeight(40);
        input_birthday.relocate(400, 330);
        pane.getChildren().add(input_birthday);

        // patient age check
        CheckBox check_age = new CheckBox("Is the Patient 13 Years Old or Older?");
        check_age.setStyle("-fx-font-size: 18");
        check_age.setIndeterminate(false);
        check_age.setDisable(true);
        check_age.setFocusTraversable(false);
        check_age.relocate(200, 430);
        pane.getChildren().add(check_age);

        input_birthday.valueProperty().addListener((ov, oldValue, newValue) -> {
            check_age.setSelected(controller.checkPatientAge(input_birthday.getValue()));
        });

        // vitals input
        Text label_vitals = new Text("Vitals:");
        label_vitals.setStyle("-fx-font-weight: bold");
        label_vitals.relocate(700, 250);
        pane.getChildren().add(label_vitals);

        // weight
        Text label_weight = new Text("Weight:");
        label_weight.relocate(700, 310);
        pane.getChildren().add(label_weight);

        TextField input_weight = new TextField();
        input_weight.setFocusTraversable(false);
        input_weight.setPrefWidth(150);
        input_weight.setPrefHeight(40);
        input_weight.relocate(800, 290);
        pane.getChildren().add(input_weight);

        Text label_weight_unit = new Text("lbs");
        label_weight_unit.relocate(960, 310);
        pane.getChildren().add(label_weight_unit);

        // height
        Text label_height = new Text("Height:");
        label_height.relocate(700, 370);
        pane.getChildren().add(label_height);

        TextField input_height_ft = new TextField();
        input_height_ft.setFocusTraversable(false);
        input_height_ft.setPrefWidth(75);
        input_height_ft.setPrefHeight(40);
        input_height_ft.relocate(800, 350);
        pane.getChildren().add(input_height_ft);

        Text label_height_unit1 = new Text("ft.");
        label_height_unit1.relocate(885, 370);
        pane.getChildren().add(label_height_unit1);

        TextField input_height_in = new TextField();
        input_height_in.setFocusTraversable(false);
        input_height_in.setPrefWidth(75);
        input_height_in.setPrefHeight(40);
        input_height_in.relocate(920, 350);
        pane.getChildren().add(input_height_in);

        Text label_height_unit2 = new Text("in.");
        label_height_unit2.relocate(1005, 370);
        pane.getChildren().add(label_height_unit2);

        // body temperature
        Text label_temperature = new Text("Body Temperature:");
        label_temperature.relocate(700, 430);
        pane.getChildren().add(label_temperature);

        TextField input_temperature = new TextField();
        input_temperature.setFocusTraversable(false);
        input_temperature.setPrefWidth(100);
        input_temperature.setPrefHeight(40);
        input_temperature.relocate(940, 410);
        pane.getChildren().add(input_temperature);

        Text label_temperature_unit = new Text("°F");
        label_temperature_unit.relocate(1050, 430);
        pane.getChildren().add(label_temperature_unit);

        // blood pressure
        Text label_pressure = new Text("Blood Pressure:");
        label_pressure.relocate(700, 490);
        pane.getChildren().add(label_pressure);

        TextField input_pressure1 = new TextField();
        input_pressure1.setFocusTraversable(false);
        input_pressure1.setPrefWidth(100);
        input_pressure1.setPrefHeight(40);
        input_pressure1.relocate(900, 470);
        pane.getChildren().add(input_pressure1);

        Text label_symbol = new Text("/");
        label_symbol.relocate(1010, 490);
        pane.getChildren().add(label_symbol);

        TextField input_pressure2 = new TextField();
        input_pressure2.setFocusTraversable(false);
        input_pressure2.setPrefWidth(100);
        input_pressure2.setPrefHeight(40);
        input_pressure2.relocate(1030, 470);
        pane.getChildren().add(input_pressure2);

        Text label_pressure_unit = new Text("mmHg");
        label_pressure_unit.relocate(1140, 490);
        pane.getChildren().add(label_pressure_unit);

        // existing patient check
        CheckBox check_existing = new CheckBox("Existing Patient?");
        check_existing.setStyle("-fx-font-size: 18");
        check_existing.setIndeterminate(false);
        check_existing.setFocusTraversable(false);
        check_existing.relocate(200, 480);
        pane.getChildren().add(check_existing);

        check_existing.selectedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                if (controller.checkExisting(input_first_name.getText(), input_last_name.getText(),
                        input_birthday.getValue())) {
                    Map<String, String> patient_info_data;
                    patient_info_data = controller.getPatientInfo(input_first_name.getText(),
                            input_last_name.getText(), input_birthday.getValue());
                    for (Map.Entry<String, String> entry : patient_info_data.entrySet()) {
                        switch (entry.getKey()) {
                            case "Weight":
                                input_weight.setText(entry.getValue());
                                break;
                            case "Height_ft":
                                input_height_ft.setText(entry.getValue());
                                break;
                            case "Height_in":
                                input_height_in.setText(entry.getValue());
                                break;
                            case "Temperature":
                                input_temperature.setText(entry.getValue());
                                break;
                            case "Pressure1":
                                input_pressure1.setText(entry.getValue());
                                break;
                            case "Pressure2":
                                input_pressure2.setText(entry.getValue());
                                break;
                        }
                    }
                }
            } else if (oldVal) {
                System.out.println("unchecked");
                input_weight.setText("");
                input_height_ft.setText("");
                input_height_in.setText("");
                input_temperature.setText("");
                input_pressure1.setText("");
                input_pressure2.setText("");
            }
        });

        // save patient info
        Button save_info = new Button("Save Patient\n Information");
        save_info.setFocusTraversable(false);
        save_info.setPrefWidth(200);
        save_info.setPrefHeight(80);
        save_info.relocate(560, 600);
        pane.getChildren().add(save_info);

        save_info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.savePatientInfo(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue(),
                        input_weight.getText(), input_height_ft.getText(), input_height_in.getText(),
                        input_temperature.getText(), input_pressure1.getText(), input_pressure2.getText());
            }
        });

        // doctor visit button
        Button doctor_visit = new Button("Doctor Visit");
        doctor_visit.setFocusTraversable(false);
        doctor_visit.setPrefWidth(200);
        doctor_visit.setPrefHeight(80);
        doctor_visit.relocate(560, 700);
        pane.getChildren().add(doctor_visit);

        doctor_visit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showDoctorExamination();
            }
        });

        return scene;
    }
}