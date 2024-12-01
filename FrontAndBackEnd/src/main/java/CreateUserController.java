package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;

import java.io.IOException;


public class CreateUserController {
    //TODO: Add name field
    @FXML
    private TextField usernameField;

    @FXML
    private TextField nameField;

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
        enforceCharLimit(usernameField, 255);
        enforceCharLimit(passwordField, 255);
        enforceCharLimit(nameField, 255);

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
        String name = nameField.getText().trim();
        String role = roleComboBox.getValue();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || role == null || role.isEmpty() || name.isEmpty()){
            System.out.println("All fields are required!");
            showSystemMessage("All fields are required!", "All fields are required!");
            // Show error message or dialog (not implemented here)
            return;
        }

        // Process the user creation logic
        System.out.println("Creating user...");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);
        showSystemMessage("Account Created Successfully", "Please login with your new account details.");

        // Close the screen after user creation
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();

        // get user to log back in with their new credentials
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Credential.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage2 = new Stage();
            stage2.setTitle("Login");
            stage2.setScene(new Scene(root));
            stage2.show();

//            // Close the current login window
//            Stage currentStage = (Stage) newProjectButton.getScene().getWindow();
//            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Logic to save the user details (e.g., to a database or a file) can be added here


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
