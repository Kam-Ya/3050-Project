package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskScreenController {

    public ListView assignedListView;
    @FXML
    private Label taskTitleLabel;

    @FXML
    private Label assignedUserLabel;

    @FXML
    private Label priorityLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dueDateLabel;

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
    }

    /**
     * Sets the task details to be displayed on the screen.
     *
     * @param taskName      The title of the task.
     * @param description   The description of the task.
     * @param dueDate       The due date of the task.
     * @param assignedUser  The user assigned to the task.
     * @param priority      The priority level of the task.
     */
    public void setTaskDetails(String taskName, String description, String dueDate, ArrayList<Integer> assignedUsers, String priority) {
        taskTitleLabel.setText(taskName);
        descriptionLabel.setText(description);
        dueDateLabel.setText(dueDate);
        priorityLabel.setText(priority);
        // Populate the assignedListView
        assignedListView.getItems().clear();
        for (Integer userId : assignedUsers) {
            // Replace with a method or lookup for user names if available
            assignedListView.getItems().add("User ID: " + userId);
        }
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
            // Example: controller.setTaskName(taskTitleLabel.getText());

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

    // Error screen
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