package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.objects.Project;
import main.objects.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewTaskController {

    public Label taskTitleLabel;
    public Button createButton;
    public TextField titleField;
    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField dueDateField;

    @FXML
    private ComboBox<String> assignedToDropdown;

    @FXML
    private ComboBox<String> priorityDropdown;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Example priority levels
        priorityDropdown.getItems().addAll("1", "2", "3", "4", "5");
        // enforce char limits
        enforceCharLimit(descriptionField, 1000);
        enforceCharLimit(dueDateField, 10);
        enforceCharLimit(titleField, 40);

    }

    private void enforceCharLimit(javafx.scene.control.TextInputControl textInputControl, int maxChars) {
        textInputControl.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= maxChars) {
                return change; // Accept the input
            }
            showSystemMessage("Character Limit Exceeded", "Character Limit Exceeded");
            return null; // Reject the input
        }));
    }


    public void setTaskDetails(Project project) {
        // get list of employees from project
        ArrayList<Integer> users = project.employees;

        assignedToDropdown.getItems().addAll(String.valueOf(users));
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked!");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked!");
        String taskTitle = taskTitleLabel.getText().trim();
        String dueDate = dueDateField.getText().trim();
        String description = descriptionField.getText().trim();
        String priority = priorityDropdown.getSelectionModel().getSelectedItem();
        Integer intPriority = Integer.parseInt(priority);
        String assignedTo = assignedToDropdown.getSelectionModel().getSelectedItem();
        // cast String to Integer
        Integer intAssignedTo = Integer.parseInt(assignedTo);
        // chane the dueDate from String to Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date newDueDate = formatter.parse(dueDate);
            Task newTask = new Task(taskTitle, newDueDate, description, intPriority, intAssignedTo);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    // error screen
    private void showSystemMessage(String title, String body) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MessageFromSystemScreen.fxml"));
            Parent root = fxmlLoader.load();

            MessageFromSystemController controller = fxmlLoader.getController();
            controller.setMessage(title, body);

            Stage stage = new Stage();
            stage.setTitle("System Message");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
