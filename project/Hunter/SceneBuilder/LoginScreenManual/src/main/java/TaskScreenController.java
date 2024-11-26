package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskScreenController {

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField dueDateField;

    @FXML
    private ComboBox<String> assignedToDropdown;

    @FXML
    private ComboBox<String> priorityDropdown;

    @FXML
    private Button completeButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Example priority levels
        priorityDropdown.getItems().addAll("1", "2", "3", "4", "5");

        // Example users
        assignedToDropdown.getItems().addAll("User A", "User B", "User C");


    }

    public void setTaskDetails(String taskDetails) {
        // Parse task details and update fields (example logic)
        String[] details = taskDetails.split(" - Due: ");
        String taskName = details[0];
        String dueDate = details[1].split(" \\|")[0];

        // Set task details
        descriptionField.setText("Details for " + taskName);
        dueDateField.setText(dueDate);

        // Example: Populate dropdowns
        priorityDropdown.getItems().addAll("1", "2", "3", "4", "5");
        assignedToDropdown.getItems().addAll("User A", "User B", "User C");
    }


    @FXML
    private void handleComplete() {
        System.out.println("Complete button clicked!");
        // Logic to mark task as complete
    }

    @FXML
    private void handleNotes() {
        System.out.println("Notes button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CommentScreen.fxml"));
            Parent root = loader.load();

            // Pass task details dynamically if needed
            CommentScreenController controller = loader.getController();
            // Example: controller.setTaskName("Task N");

            Stage stage = new Stage();
            stage.setTitle("Task Comments");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked!");
        // Logic to delete the task
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked!");
        // Logic to close the task screen
    }
}
