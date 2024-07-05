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
import java.util.Scanner;

public class NurseGreet {

    private final Stage stage;

    public NurseGreet(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);

        // title text
        Text title = new Text("test");
        title.setFont(new Font("Arial", 50));
        title.relocate(530, 100);


        // arrange nodes
        pane.getChildren().add(title);


        return scene;
    }

}
