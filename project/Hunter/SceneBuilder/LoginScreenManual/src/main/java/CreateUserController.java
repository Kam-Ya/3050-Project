package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    public void initialize() {
        // Populate the ComboBox with roles
        roleComboBox.getItems().addAll("Admin", "Manager", "Employee", "Viewer");
    }

    /**
     * Handles the action for the Cancel button.
     * Closes the CreateUserScreen.
     */
    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked.");
        // Close the current stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action for the Create button.
     * Validates the input and processes user creation.
     */
    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked.");
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleComboBox.getValue();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || role == null || role.isEmpty()) {
            System.out.println("All fields are required!");
            // Show error message or dialog (not implemented here)
            return;
        }

        // Process the user creation logic
        System.out.println("Creating user...");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        // Logic to save the user details (e.g., to a database or a file) can be added here

        // Close the screen after user creation
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }
}
