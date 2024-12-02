package main.java;

import javafx.scene.control.Label;
import main.objects.*;

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

import javax.swing.text.LabelView;
import java.io.IOException;

import static main.java.Main.userID;

public class CredentialsController {
    private static final String ListOFProjects = "/ListOfProjectsScreen.fxml";
    private static final String CreateUser = "/CreateUserScreen.fxml";

    public static Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    private TextField usernameField;

    private Label loginTitleLabel;
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

        // tell user to wait.
        loginTitleLabel.setText("Please Wait");

        // Simulate login success with mockUserID
        //showSystemMessage("Please Wait", "Awaiting server response...");

//        if(userID != -1){
//            // let the server load
//            try {
//                wait(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            // open the next screen
            openListOfProjectsScreen();
//        }

    }

    public void openListOfProjectsScreen() {
        try {
            //clientController.sendMSG(1,"projectList",Main.userID);
            clientController.sendMSG(1,"projectList",Main.userID);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListOfProjectsScreen.fxml"));

            Parent root = loader.load();

            // Pass the mockUserID to ListOfProjectsController
            //ListOfProjectsController controller = loader.getController();
            //controller.setUserID(userID);

            Stage stage = new Stage();
            stage.setTitle("List of Projects");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current login screen
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();

            ListOfProjectsController listofController = new ListOfProjectsController();
            listofController.loadMockProjects();
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