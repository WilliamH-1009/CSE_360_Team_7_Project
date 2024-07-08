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
        scene.getStylesheets()
                .add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Patient Portal");
        title.getStyleClass().add("title");
        title.relocate(510, 50);
        pane.getChildren().add(title);

        // patient first name text
        Text label_first_name = new Text("First Name: ");
        label_first_name.relocate(100, 130);
        pane.getChildren().add(label_first_name);

        // patient last name text
        Text label_last_name = new Text("Last Name: ");
        label_last_name.relocate(100, 180);
        pane.getChildren().add(label_last_name);

        // patient date of birth text
        Text label_birthday = new Text("Date of Birth: ");
        label_birthday.relocate(100, 230);
        pane.getChildren().add(label_birthday);

        // contact info label
        Text label_contact = new Text("Contact Information");
        label_contact.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        label_contact.relocate(100, 300);
        pane.getChildren().add(label_contact);

        // phone number text
        Text label_phone_number = new Text("Phone Number: ");
        label_phone_number.getStyleClass().add("text-area");
        label_phone_number.relocate(100, 340);
        pane.getChildren().add(label_phone_number);

        // email text
        Text label_email = new Text("Email: ");
        label_email.getStyleClass().add("text-area");
        label_email.relocate(100, 380);
        pane.getChildren().add(label_email);

        // message sending input
        Text label_sending = new Text("Contact Your Doctor");
        label_sending.relocate(150, 480);
        pane.getChildren().add(label_sending);

        TextArea input_sending = new TextArea();
        input_sending.getStyleClass().add("text-area");
        input_sending.setFocusTraversable(false);
        input_sending.setPrefWidth(300);
        input_sending.setPrefHeight(150);
        input_sending.relocate(150, 510);
        pane.getChildren().add(input_sending);

        // send message button
        Button send = new Button("Send Message");
        send.setFocusTraversable(false);
        send.setPrefWidth(250);
        send.setPrefHeight(40);
        send.relocate(150, 680);
        pane.getChildren().add(send);

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.savePatientSendingMessage(input_sending.getText());
            }
        });

        // message receiving input
        Text label_receiving = new Text("Incoming Messages");
        label_receiving.relocate(500, 480);
        pane.getChildren().add(label_receiving);

        TextArea input_receiving = new TextArea();
        input_receiving.getStyleClass().add("text-area");
        input_receiving.setFocusTraversable(false);
        input_receiving.setEditable(false);
        input_receiving.setPrefWidth(300);
        input_receiving.setPrefHeight(150);
        input_receiving.relocate(500, 510);
        pane.getChildren().add(input_receiving);

        // visit summary input
        Text label_visit_summary = new Text("Visit Summary");
        label_visit_summary.relocate(900, 150);
        pane.getChildren().add(label_visit_summary);

        TextArea input_visit_summary = new TextArea();
        input_visit_summary.getStyleClass().add("text-area");
        input_visit_summary.setFocusTraversable(false);
        input_visit_summary.setEditable(false);
        input_visit_summary.setPrefWidth(300);
        input_visit_summary.setPrefHeight(450);
        input_visit_summary.relocate(900, 180);
        pane.getChildren().add(input_visit_summary);


        // set patient contact info data
        Map<String, String> contact_info_data = controller.getPatientContactInfo();
        for (Map.Entry<String, String> entry : contact_info_data.entrySet()) {
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
                case "PhoneNumber":
                    label_phone_number.setText(label_phone_number.getText() + entry.getValue());
                    break;
                case "Email":
                    label_email.setText(label_email.getText() + entry.getValue());
                    break;
            }
        }

        // set incoming messages
        input_receiving.setText(controller.getPatientIncomingMessages());

        // set visit summary
        input_visit_summary.setText(controller.getPatientVisitSummary());

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
