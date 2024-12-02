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
import main.client.CTMClient;
import main.client.clientController;

import java.io.IOException;

public class CredentialsController {
    private static final String ListOFProjects = "/ListOfProjectsScreen.fxml";
    private static final String CreateUser = "/CreateUserScreen.fxml";

    public static Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final Integer mockUserID = 123; // Mock userID for testing

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showSystemMessage("Empty Fields", "Please enter both username and password.");
            return;
        }

        System.out.println("Username: " + username + ", Password: " + password);



        Login login = new Login(username, password);
        clientController.sendMSG(login, "loginRequest", -1);

        // Simulate login success with mockUserID
        showSystemMessage("Please Wait", "Simulating server response...");
    }


    public static void openListOfProjectsScreen(Integer userID) {
        try {
            FXMLLoader loader = new FXMLLoader(CredentialsController.class.getClassLoader().getResource(ListOFProjects));
            Parent root = loader.load();

            // Pass the mockUserID to ListOfProjectsController
            ListOfProjectsController controller = loader.getController();
            controller.setUserID(userID);

            Stage stage = new Stage();
            stage.setTitle("List of Projects");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current login screen
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();

            ListOfProjectsController listofController = new ListOfProjectsController()
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