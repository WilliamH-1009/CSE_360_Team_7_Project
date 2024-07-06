package View;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class NurseGreet {

    private final Stage stage;

    public NurseGreet(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Nurse Greet");
        title.getStyleClass().add("title");
        title.relocate(510, 130);
        pane.getChildren().add(title);

        // patient name input
        Text label_name = new Text("Patient Name:");
        label_name.relocate(150, 250);
        pane.getChildren().add(label_name);

        TextField input_name = new TextField();
        input_name.setFocusTraversable(false);
        input_name.setPrefWidth(270);
        input_name.setPrefHeight(40);
        input_name.relocate(330, 230);
        pane.getChildren().add(input_name);

        // patient date of birth input
        Text label_birthday = new Text("Patient Date Of Birth:");
        label_birthday.relocate(150, 350);
        pane.getChildren().add(label_birthday);

        TextField input_birthday = new TextField();
        input_birthday.setFocusTraversable(false);
        input_birthday.setPrefWidth(190);
        input_birthday.setPrefHeight(40);
        input_birthday.relocate(410, 330);
        pane.getChildren().add(input_birthday);

        // patient age check
        CheckBox check_age = new CheckBox("Is the Patient 13 Years Old or Older?");
        check_age.setStyle("-fx-font-size: 18");
        check_age.setIndeterminate(false);
        check_age.setDisable(true);
        check_age.setFocusTraversable(false);
        check_age.relocate(200, 430);
        pane.getChildren().add(check_age);

        // existing patient check
        CheckBox check_existing = new CheckBox("Existing Patient?");
        check_existing.setStyle("-fx-font-size: 18");
        check_existing.setIndeterminate(false);
        check_existing.setFocusTraversable(false);
        check_existing.relocate(200, 480);
        pane.getChildren().add(check_existing);

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

        // save patient info
        Button save_info = new Button("Save Patient\n Information");
        save_info.setFocusTraversable(false);
        save_info.setPrefWidth(200);
        save_info.setPrefHeight(80);
        save_info.relocate(560, 600);
        pane.getChildren().add(save_info);

        return scene;
    }

}
