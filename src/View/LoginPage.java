package View;

import Application.Controller;
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

public class LoginPage {

    private final Stage stage;

    public LoginPage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());

        // title text
        Text title = new Text("Login Page");
        title.getStyleClass().add("title");
        title.relocate(520, 130);
        pane.getChildren().add(title);

        // user id text field
        TextField user_id = new TextField();
        user_id.setPromptText("User ID");
        user_id.setFocusTraversable(false);
        user_id.setPrefWidth(255);
        user_id.setPrefHeight(40);
        user_id.relocate(530, 400);
        pane.getChildren().add(user_id);

        // password text field
        TextField password = new TextField();
        password.setPromptText("Password");
        password.setFocusTraversable(false);
        password.setPrefWidth(255);
        password.setPrefHeight(40);
        password.relocate(530, 480);
        pane.getChildren().add(password);

        // login button
        Button login = new Button("Log In");
        login.setPrefWidth(255);
        login.setPrefHeight(40);
        login.relocate(530, 560);
        pane.getChildren().add(login);
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
                            Controller controller = new Controller(stage);
                            // controller.showNurseGreet();
                            controller.showIndexPage();
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
        create_account.setPrefWidth(255);
        create_account.setPrefHeight(40);
        create_account.relocate(530, 640);
        pane.getChildren().add(create_account);
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
        
        return scene;
    }



    /* Example VBox code not needed
       // Create a layout (VBox) to arrange the elements.
        VBox root = new VBox(10);
        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        // Set the title of the window.
        primaryStage.setTitle("Login Form App");

        // Show the window.
        primaryStage.show();
    
    */
}
