package Application;

import Model.SceneName;
import java.util.HashMap;
import java.util.Map;

import View.IndexPage;
import View.LoginPage;
import View.NurseGreet;
import View.PatientHealthInfo;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    private static final Map<SceneName, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) {
        try {
            scenes.put(SceneName.LoginPage, new LoginPage(stage).getScene());
            scenes.put(SceneName.IndexPage, new IndexPage(stage).getScene());
            scenes.put(SceneName.NurseGreet, new NurseGreet(stage).getScene());
            scenes.put(SceneName.PatientHealthInfo, new PatientHealthInfo(stage).getScene());
            scenes.put(SceneName.PatientPortal, new PatientPortal(stage).getScene());

            stage.setScene(scenes.get(SceneName.LoginPage));
            stage.setTitle("Pediatric Auto System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }
}
