package Application;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Model.SceneName;

import java.io.*;
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
        stage.setScene(Main.getScenes().get(SceneName.PatientHealthHistory));
    }

    public void showDoctorExamination() {
        stage.setScene(Main.getScenes().get(SceneName.DoctorExamination));
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

    public void savePatientAccount(String firstName, String lastName, LocalDate birthday, String contact, String email, String insurance, String pharmacy) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null && !contact.isEmpty() && !email.isEmpty() && !insurance.isEmpty() && !pharmacy.isEmpty()) {
            StringBuilder data = new StringBuilder();
            data.append("FirstName::").append(firstName).append("\n");
            data.append("LastName::").append(lastName).append("\n");
            data.append("Birthday::").append(birthday).append("\n");
            data.append("Contact::").append(contact).append("\n");
            data.append("Email::").append(email).append("\n");
            data.append("Insurance::").append(insurance).append("\n");
            data.append("Pharmacy::").append(pharmacy).append("\n");
            data.append("===next===\n");

            File file = new File("patient_information.txt");
            try {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data.toString());
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Patient Account Successfully Saved");
                alert.show();
                System.out.println("Patient Account Saved");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.show();
            System.out.println("Save Patient Account Failed");
        }
    }

    public Map<String, String> loadPatientInfo(String firstName, String lastName, LocalDate birthday) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null) {
            File file = new File("patient_information.txt");
            Map<String, String> patientInfo = new HashMap<>();
            try {
                Scanner fr = new Scanner(file);
                while (fr.hasNextLine()) {
                    String[] read_line = fr.nextLine().split("::");
                    if (read_line[0].equals("FirstName") && read_line[1].equals(firstName)) {
                        String[] read_next_line = fr.nextLine().split("::");
                        if (read_next_line[0].equals("LastName") && read_next_line[1].equals(lastName)) {
                            String[] read_birthday_line = fr.nextLine().split("::");
                            if (read_birthday_line[0].equals("Birthday") && read_birthday_line[1].equals(birthday.toString())) {
                                patientInfo.put("Contact", fr.nextLine().split("::")[1]);
                                patientInfo.put("Email", fr.nextLine().split("::")[1]);
                                patientInfo.put("Insurance", fr.nextLine().split("::")[1]);
                                patientInfo.put("Pharmacy", fr.nextLine().split("::")[1]);
                                return patientInfo;
                            }
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
            System.out.println("Load Patient Info Failed");
        }
        return null;
    }

    public void saveContactInfo(String firstName, String lastName, LocalDate birthday, String contact, String email) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null && !contact.isEmpty() && !email.isEmpty()) {
            StringBuilder data = new StringBuilder();
            data.append("FirstName::").append(firstName).append("\n");
            data.append("LastName::").append(lastName).append("\n");
            data.append("Birthday::").append(birthday).append("\n");
            data.append("Contact::").append(contact).append("\n");
            data.append("Email::").append(email).append("\n");
            data.append("===next===\n");

            File file = new File("contact_information.txt");
            try {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data.toString());
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Contact Information Successfully Saved");
                alert.show();
                System.out.println("Contact Information Saved");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.show();
            System.out.println("Save Contact Info Failed");
        }
    }

    public String getVisitSummary(String firstName, String lastName, LocalDate birthday) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null) {
            File file = new File("patient_information.txt");
            StringBuilder summary = new StringBuilder();
            try {
                Scanner fr = new Scanner(file);
                while (fr.hasNextLine()) {
                    String[] read_line = fr.nextLine().split("::");
                    if (read_line[0].equals("FirstName") && read_line[1].equals(firstName)) {
                        String[] read_next_line = fr.nextLine().split("::");
                        if (read_next_line[0].equals("LastName") && read_next_line[1].equals(lastName)) {
                            String[] read_birthday_line = fr.nextLine().split("::");
                            if (read_birthday_line[0].equals("Birthday") && read_birthday_line[1].equals(birthday.toString())) {
                                summary.append("Contact: ").append(fr.nextLine().split("::")[1]).append("\n");
                                summary.append("Email: ").append(fr.nextLine().split("::")[1]).append("\n");
                                summary.append("Insurance: ").append(fr.nextLine().split("::")[1]).append("\n");
                                summary.append("Pharmacy: ").append(fr.nextLine().split("::")[1]).append("\n");
                                return summary.toString();
                            }
                        }
                    }
                }
                fr.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return summary.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in both Patient Name and Patient Date of Birth");
            alert.show();
            System.out.println("Get Visit Summary Failed");
        }
        return null;
    }

    public void sendMessage(String firstName, String lastName, LocalDate birthday, String message) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null && !message.isEmpty()) {
            StringBuilder data = new StringBuilder();
            data.append("FirstName::").append(firstName).append("\n");
            data.append("LastName::").append(lastName).append("\n");
            data.append("Birthday::").append(birthday).append("\n");
            data.append("Message::").append(message).append("\n");
            data.append("===next===\n");

            File file = new File("messages.txt");
            try {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data.toString());
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Message Sent Successfully");
                alert.show();
                System.out.println("Message Sent");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.show();
            System.out.println("Send Message Failed");
        }
    }

    public void saveExaminationInfo(String firstName, String lastName, LocalDate birthday, String findings, String medication) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null && !findings.isEmpty() && !medication.isEmpty()) {
            StringBuilder data = new StringBuilder();
            data.append("FirstName::").append(firstName).append("\n");
            data.append("LastName::").append(lastName).append("\n");
            data.append("Birthday::").append(birthday).append("\n");
            data.append("Findings::").append(findings).append("\n");
            data.append("Medication::").append(medication).append("\n");
            data.append("===next===\n");

            File file = new File("examination_info.txt");
            try {
                FileWriter fw = new FileWriter(file, true);
                fw.write(data.toString());
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Examination Information Successfully Saved");
                alert.show();
                System.out.println("Examination Information Saved");

                sendPrescriptionToPharmacy(firstName, lastName, birthday, medication);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.show();
            System.out.println("Save Examination Info Failed");
        }
    }

    private void sendPrescriptionToPharmacy(String firstName, String lastName, LocalDate birthday, String medication) {
        File file = new File("patient_information.txt");
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                String[] read_line = fr.nextLine().split("::");
                if (read_line[0].equals("FirstName") && read_line[1].equals(firstName)) {
                    String[] read_next_line = fr.nextLine().split("::");
                    if (read_next_line[0].equals("LastName") && read_next_line[1].equals(lastName)) {
                        String[] read_birthday_line = fr.nextLine().split("::");
                        if (read_birthday_line[0].equals("Birthday") && read_birthday_line[1].equals(birthday.toString())) {
                            String pharmacy = fr.nextLine().split("::")[1];
                            // Here you would send the prescription to the pharmacy.
                            // For demonstration, we'll print it to the console.
                            System.out.println("Prescription for " + firstName + " " + lastName + " sent to pharmacy: " + pharmacy);
                            System.out.println("Medication: " + medication);
                        }
                    }
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPatientHistory(String firstName, String lastName, LocalDate birthday) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && birthday != null) {
            StringBuilder history = new StringBuilder();
            history.append(getVisitSummary(firstName, lastName, birthday));
            history.append(getExaminationInfo(firstName, lastName, birthday));
            return history.toString();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in both Patient Name and Patient Date of Birth");
            alert.show();
            System.out.println("Get Patient History Failed");
        }
        return null;
    }

    private String getExaminationInfo(String firstName, String lastName, LocalDate birthday) {
        File file = new File("examination_info.txt");
        StringBuilder info = new StringBuilder();
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                String[] read_line = fr.nextLine().split("::");
                if (read_line[0].equals("FirstName") && read_line[1].equals(firstName)) {
                    String[] read_next_line = fr.nextLine().split("::");
                    if (read_next_line[0].equals("LastName") && read_next_line[1].equals(lastName)) {
                        String[] read_birthday_line = fr.nextLine().split("::");
                        if (read_birthday_line[0].equals("Birthday") && read_birthday_line[1].equals(birthday.toString())) {
                            info.append("Findings: ").append(fr.nextLine().split("::")[1]).append("\n");
                            info.append("Medication: ").append(fr.nextLine().split("::")[1]).append("\n");
                            info.append("\n===next===\n");
                        }
                    }
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }
}