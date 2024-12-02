package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;
import main.client.clientController;
import main.objects.Project;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewProjectController {

    @FXML
    private TextField projectTitleField;

    @FXML
    private TextArea projectDescriptionArea;

    @FXML
    private TextField dueDateField;

    @FXML
    private ListView<String> workersListView;

    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    // List of available workers (Mock Data)
    private final ObservableList<String> availableWorkers = FXCollections.observableArrayList(
            "Alice", "Bob", "Charlie", "Diana", "Eve"
    );

    /**
     * Initializes the NewProjectScreen.
     * Populates the workers ListView with multi-selection capabilities.
     */
    @FXML
    public void initialize() {
        workersListView.setItems(availableWorkers);
        workersListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        enforceCharLimit(projectTitleField, 255); // Names: 255 chars
        enforceCharLimit(projectDescriptionArea, 1000); // Description: 1000 chars
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

    /**
     * Handles the action for the Cancel button.
     * Closes the screen without saving any changes.
     */
    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked.");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action for the Create button.
     * Validates the input and processes the creation or update of a project.
     */
    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked.");

        String projectTitle = projectTitleField.getText().trim();
        String projectDescription = projectDescriptionArea.getText().trim();
        String dueDate = dueDateField.getText().trim();
        ObservableList<String> assignedWorkers = workersListView.getSelectionModel().getSelectedItems();

        // Validation logic
        if (projectTitle.isEmpty() || projectDescription.isEmpty() || dueDate.isEmpty() || assignedWorkers.isEmpty()) {
            System.out.println("All fields are required!");
            // Show error message or dialog (not implemented here)
            return;
        }
        // chane the dueDate from String to Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date newDueDate = formatter.parse(dueDate);
            Project project=new Project(projectTitle,newDueDate,projectDescription,-1);
            clientController.sendMSG(project,"createProject",Main.userID);
      } catch (ParseException e) {
         e.printStackTrace();
      }


        // Process the project creation logic
        System.out.println("Creating/Updating project...");
        System.out.println("Title: " + projectTitle);
        System.out.println("Description: " + projectDescription);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Assigned Workers: " + assignedWorkers);

        // Logic to save the project details (e.g., to a database or a file) can be added here

        // Close the screen after creating/updating the project
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action for the Delete button.
     * Deletes the project and closes the screen.
     */
    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked.");
        // Logic to delete the project can be added here
        Stage stage = (Stage) deleteButton.getScene().getWindow();
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
