package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

public class CredentialsController {

    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showSystemMessage("Empty Fields", "Please enter both username and password.");
            return;
        }

        // Attempting to use Kams Login class
        Login login = new Login(username, password);


        System.out.println("Username: " + username + ", Password: " + password);
        // Add actual login logic here

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ListOfProjectsScreen.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
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

    public void handleSignup() {
        System.out.println("Signup Button Pressed");
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CreateUserScreen.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

           // Close the current login window
            Stage currentStage = (Stage) registerButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
