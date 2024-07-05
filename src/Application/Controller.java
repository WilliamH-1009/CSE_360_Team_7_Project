package Application;

import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import Model.SceneName;

public class Controller {
    private final Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void showLoginPage() {
        stage.setScene(Main.getScenes().get(SceneName.LoginPage));
    }

    public void showNurseGreet() {
        stage.setScene(Main.getScenes().get(SceneName.NurseGreet));
    }

}
