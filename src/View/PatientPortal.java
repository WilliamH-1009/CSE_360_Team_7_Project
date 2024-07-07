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

public class PatientPortal {
    private final Stage stage;

    public PatientPortal(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        Controller controller = new Controller(stage);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Patient Portal");
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

        // contact info
        Text label_contact = new Text("Contact Info:");
        label_contact.setStyle("-fx-font-weight: bold");
        label_contact.relocate(50, 350);
        pane.getChildren().add(label_contact);

        TextField input_contact = new TextField();
        input_contact.setPromptText("Phone Number");
        input_contact.setFocusTraversable(false);
        input_contact.setPrefWidth(200);
        input_contact.setPrefHeight(40);
        input_contact.relocate(200, 340);
        pane.getChildren().add(input_contact);

        TextField input_email = new TextField();
        input_email.setPromptText("Email Address");
        input_email.setFocusTraversable(false);
        input_email.setPrefWidth(300);
        input_email.setPrefHeight(40);
        input_email.relocate(450, 340);
        pane.getChildren().add(input_email);

        // insurance info
        Text label_insurance = new Text("Insurance Info:");
        label_insurance.setStyle("-fx-font-weight: bold");
        label_insurance.relocate(50, 450);
        pane.getChildren().add(label_insurance);

        TextField input_insurance = new TextField();
        input_insurance.setPromptText("Insurance Details");
        input_insurance.setFocusTraversable(false);
        input_insurance.setPrefWidth(300);
        input_insurance.setPrefHeight(40);
        input_insurance.relocate(200, 440);
        pane.getChildren().add(input_insurance);

        // pharmacy info
        Text label_pharmacy = new Text("Pharmacy Info:");
        label_pharmacy.setStyle("-fx-font-weight: bold");
        label_pharmacy.relocate(50, 550);
        pane.getChildren().add(label_pharmacy);

        TextField input_pharmacy = new TextField();
        input_pharmacy.setPromptText("Pharmacy Details");
        input_pharmacy.setFocusTraversable(false);
        input_pharmacy.setPrefWidth(300);
        input_pharmacy.setPrefHeight(40);
        input_pharmacy.relocate(200, 540);
        pane.getChildren().add(input_pharmacy);

        // save account info button
        Button save_account = new Button("Save Account Info");
        save_account.setFocusTraversable(false);
        save_account.setPrefWidth(200);
        save_account.setPrefHeight(40);
        save_account.relocate(600, 600);
        pane.getChildren().add(save_account);

        // save account info action
        save_account.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.savePatientAccount(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue(),
                        input_contact.getText(), input_email.getText(), input_insurance.getText(), input_pharmacy.getText());
            }
        });

        // load patient info button
        Button load_info = new Button("Load Patient Info");
        load_info.setFocusTraversable(false);
        load_info.setPrefWidth(200);
        load_info.setPrefHeight(40);
        load_info.relocate(600, 650);
        pane.getChildren().add(load_info);

        // load patient info action
        load_info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map<String, String> patientInfo = controller.loadPatientInfo(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue());
                if (patientInfo != null) {
                    input_contact.setText(patientInfo.get("Contact"));
                    input_email.setText(patientInfo.get("Email"));
                    input_insurance.setText(patientInfo.get("Insurance"));
                    input_pharmacy.setText(patientInfo.get("Pharmacy"));
                }
            }
        });

        // patient visit summary
        Text label_summary = new Text("Visit Summary:");
        label_summary.setStyle("-fx-font-weight: bold");
        label_summary.relocate(50, 700);
        pane.getChildren().add(label_summary);

        TextArea visit_summary = new TextArea();
        visit_summary.setPrefWidth(900);
        visit_summary.setPrefHeight(200);
        visit_summary.relocate(200, 700);
        pane.getChildren().add(visit_summary);

        // load visit summary button
        Button load_summary = new Button("Load Summary");
        load_summary.setFocusTraversable(false);
        load_summary.setPrefWidth(200);
        load_summary.setPrefHeight(40);
        load_summary.relocate(1100, 700);
        pane.getChildren().add(load_summary);

        // load visit summary action
        load_summary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String summary = controller.getVisitSummary(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue());
                visit_summary.setText(summary);
            }
        });

        // messaging
        Text label_message = new Text("Messages:");
        label_message.setStyle("-fx-font-weight: bold");
        label_message.relocate(50, 900);
        pane.getChildren().add(label_message);

        TextArea message_area = new TextArea();
        message_area.setPromptText("Enter your message here...");
        message_area.setPrefWidth(900);
        message_area.setPrefHeight(100);
        message_area.relocate(200, 900);
        pane.getChildren().add(message_area);

        // send message button
        Button send_message = new Button("Send Message");
        send_message.setFocusTraversable(false);
        send_message.setPrefWidth(200);
        send_message.setPrefHeight(40);
        send_message.relocate(1100, 900);
        pane.getChildren().add(send_message);

        // send message action
        send_message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.sendMessage(input_first_name.getText(), input_last_name.getText(), input_birthday.getValue(), message_area.getText());
            }
        });

        return scene;
    }
}
