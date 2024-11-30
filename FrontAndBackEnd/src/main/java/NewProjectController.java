package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
}
