package main.java;

import javafx.application.Platform;
import javafx.scene.control.*;
import main.objects.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.client.CTMClient;
import main.client.clientController;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.util.Optional;

import static main.java.Main.userID;

public class CredentialsController {
    private static final String ListOFProjects = "/ListOfProjectsScreen.fxml";
    private static final String CreateUser = "/CreateUserScreen.fxml";

    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    private TextField usernameField;
    @FXML
    private Label loginTitleLabel;
    @FXML
    private PasswordField passwordField;

    private final Integer mockUserID = 123; // Mock userID for testing
//    @FXML
//    public void initialize() {
//        loginTitleLabel.setText("Login");
//    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Empty Fields", "Please enter both username and password.");
            return;
        }

        System.out.println("Username: " + username + ", Password: " + password);



        Login login = new Login(username, password);
        clientController.sendMSG(login, "loginRequest", -1);

        // Update the label to indicate waiting
        loginTitleLabel.setText("Please Wait...");

        // Create a new Thread to wait for the userID
        new Thread(() -> {
            try {
                // Loop until userID is returned
                while (userID <= 0) {
                    Thread.sleep(100); // Avoid busy waiting
                }

                // Once userID is available, update the UI on the JavaFX Application Thread
                Platform.runLater(() -> {
                    loginTitleLabel.setText("Login Successful");
                    openListOfProjectsScreen();
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> showSystemMessage("Error", "Failed to log in. Please try again."));
            }
        }).start();
    }

    public void openListOfProjectsScreen() {
        try {
            System.out.println("Before loading FXML...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListOfProjectsScreen.fxml"));
            Parent root = loader.load();
            System.out.println("FXML Loaded successfully...");

            ListOfProjectsController controller = loader.getController();
            System.out.println("Controller instance: " + controller);
            System.out.println("Injected ListView: " + controller.projectListView2);


            Stage stage = new Stage();
            stage.setTitle("List of Projects");
            stage.setScene(new Scene(root));
            stage.show();

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

    private Optional<ButtonType> showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public void handleSignup() {
        System.out.println("Signup Button Pressed");
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CreateUser));
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