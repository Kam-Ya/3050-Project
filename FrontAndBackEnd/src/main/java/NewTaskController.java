package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class NewTaskController {

    public Label taskTitleLabel;
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

        // enforce char limmits
        enforceCharLimit(descriptionField, 1000);
        enforceCharLimit(dueDateField, 10);
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
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
