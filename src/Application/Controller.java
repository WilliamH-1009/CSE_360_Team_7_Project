package Application;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Model.SceneName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Controller {
    private final Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void showLoginPage() {
        stage.setScene(Main.getScenes().get(SceneName.LoginPage));
    }

    public void showIndexPage() {
        stage.setScene(Main.getScenes().get(SceneName.IndexPage));
    }

    public void showNurseGreet() {
        stage.setScene(Main.getScenes().get(SceneName.NurseGreet));
    }

    public void showPatientHealthInfo() {
        stage.setScene(Main.getScenes().get(SceneName.PatientHealthInfo));
    }

    public boolean checkPatientAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return Period.between(birthday, today).getYears() >= 13;
    }

    public boolean checkExisting(String name, LocalDate birthday) {
        if (!name.isEmpty() && birthday != null) {
            File file = new File("patient_information.txt");
            try {
                Scanner fr = new Scanner(file);
                while (fr.hasNextLine()) {
                    String[] read_line = fr.nextLine().split("::");
                    if (read_line[0].equals("Name") && read_line[1].equals(name)) {
                        String[] read_next_line = fr.nextLine().split("::");
                        if (read_next_line[0].equals("Birthday") && read_next_line[1].equals(birthday.toString())) {
                            return true;
                        }
                    }
                }
                fr.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Patient information is not found");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in both Patient Name and Patient Date of Birth");
            alert.show();
            System.out.println("Check Existing Failed");
        }
        return false;
    }

    public void savePatientInfo(String name, LocalDate birthday, String weight, String feet, String inch,
                                String temperature, String pressure1, String pressure2) {
        if (!name.isEmpty() && birthday != null && !weight.isEmpty() && !feet.isEmpty() && !inch.isEmpty() &&
                !temperature.isEmpty() && !pressure1.isEmpty() && !pressure2.isEmpty()) {
            StringBuilder data = new StringBuilder();
            data.append("Name::").append(name).append("\n");
            data.append("Birthday::").append(birthday).append("\n");
            data.append("Weight::").append(weight).append("\n");
            data.append("Height_ft::").append(feet).append("\n");
            data.append("Height_in::").append(inch).append("\n");
            data.append("Temperature::").append(temperature).append("\n");
            data.append("Pressure1::").append(pressure1).append("\n");
            data.append("Pressure2::").append(pressure2).append("\n");
            data.append("===next===\n");

            File file = new File("patient_information.txt");
            try {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data.toString());
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Patient Information Successfully Saved");
                alert.show();
                System.out.println("Patient Information Saved");
                showPatientHealthInfo();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.show();
            System.out.println("Save Patient Info Failed");
        }
    }

    public Map<String, String> getPatientInfo(String name, LocalDate birthday) {
        if (!name.isEmpty() && birthday != null) {
            File file = new File("patient_information.txt");
            try {
                Scanner fr = new Scanner(file);
                while (fr.hasNextLine()) {
                    String[] read_line = fr.nextLine().split("::");
                    if (read_line[0].equals("Name") && read_line[1].equals(name)) {
                        String[] read_next_line = fr.nextLine().split("::");
                        if (read_next_line[0].equals("Birthday") && read_next_line[1].equals(birthday.toString())) {
                            Map<String, String> patient_info_data = new LinkedHashMap<>();
                            patient_info_data.put("Weight", fr.nextLine().split("::")[1]);
                            patient_info_data.put("Height_ft", fr.nextLine().split("::")[1]);
                            patient_info_data.put("Height_in", fr.nextLine().split("::")[1]);
                            patient_info_data.put("Temperature", fr.nextLine().split("::")[1]);
                            patient_info_data.put("Pressure1", fr.nextLine().split("::")[1]);
                            patient_info_data.put("Pressure2", fr.nextLine().split("::")[1]);
                            return patient_info_data;
                        }
                    }
                }
                fr.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in both Patient Name and Patient Date of Birth");
            alert.show();
            System.out.println("Get Patient Info Failed");
        }
        return null;
    }

}
