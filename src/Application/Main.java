package Application;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            Pane login_page_pane = new Pane();
            Scene login_page_scene = new Scene(login_page_pane, 1300, 800);
            Pane new_page_pane = new Pane();
            Scene new_page_scene = new Scene(new_page_pane, 1300, 800);

            // title text
            Text title = new Text("Login Page");
            title.setFont(new Font("Arial", 50));
            title.relocate(530, 100);

            // user id text field
            TextField user_id = new TextField();
            user_id.setFont(new Font("Arial", 25));
            user_id.setPromptText("User ID");
            user_id.setFocusTraversable(false);
            user_id.setPrefWidth(255);
            user_id.setPrefHeight(40);
            user_id.relocate(530, 400);

            // password text field
            TextField password = new TextField();
            password.setFont(new Font("Arial", 25));
            password.setPromptText("Password");
            password.setFocusTraversable(false);
            password.setPrefWidth(255);
            password.setPrefHeight(40);
            password.relocate(530, 480);

            // login button
            Button login = new Button("Log In");
            login.setFont(new Font("Arial", 25));
            login.setPrefWidth(255);
            login.setPrefHeight(40);
            login.relocate(530, 560);
            // login action
            EventHandler<ActionEvent> exec_login = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    File file = new File("user_data.txt");
                    try {
                        boolean data_exists = false;
                        Scanner fr = new Scanner(file);
                        while (fr.hasNextLine()) {
                            String uid = user_id.getText();
                            String pwd = password.getText();
                            if (fr.nextLine().equals(uid + "===" + pwd)) {
                                data_exists = true;
                                System.out.println("Login Successful");
                                stage.setScene(new_page_scene);
                            }
                        }
                        fr.close();
                        if (!data_exists) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Failed to login.\n\nPlease check your user id or password.\n\n");
                            alert.show();
                            System.out.println("Login Failed");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            login.setOnAction(exec_login);

            // create account button
            Button create_account = new Button("Create Account");
            create_account.setFont(new Font("Arial", 25));
            create_account.setPrefWidth(255);
            create_account.setPrefHeight(40);
            create_account.relocate(530, 640);
            // login action
            EventHandler<ActionEvent> exec_create_account = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    StringBuilder str = new StringBuilder();
                    str.append(user_id.getText()).append("===");
                    str.append(password.getText()).append("\n");

                    File file = new File("user_data.txt");
                    try {
                        FileWriter fw = new FileWriter(file, true);
                        fw.write(str.toString());
                        fw.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Account created.\n\n");
                        alert.show();
                        System.out.println("Created account data");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            create_account.setOnAction(exec_create_account);

            //
            Text new_text = new Text("Work In Progress");
            new_text.setFont(new Font("Arial", 30));
            new_text.relocate(530, 100);

            // arrange nodes
            login_page_pane.getChildren().add(title);
            login_page_pane.getChildren().add(user_id);
            login_page_pane.getChildren().add(password);
            login_page_pane.getChildren().add(login);
            login_page_pane.getChildren().add(create_account);

            new_page_pane.getChildren().add(new_text);

            // show scene
            stage.setScene(login_page_scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
