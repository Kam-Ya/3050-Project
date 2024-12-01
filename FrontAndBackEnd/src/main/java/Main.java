package main.java;

import main.client.CTMClient; // Import your OCSF client
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private static final String CREDENTIALS_SCREEN = "/CredentialsScreen.fxml";
    private static CTMClient client; // Static client instance
    private static CredentialsController credentialsController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the OCSF client
        try {
            client = new CTMClient("localhost", 12345); // Adjust host and port as necessary
            client.openConnection(); // Establish connection to the server
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            e.printStackTrace();
        }

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(CREDENTIALS_SCREEN)));
            Parent root = loader.load();

            // Get the controller instance and store it
            credentialsController = loader.getController();

            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
            e.printStackTrace();
        }
        primaryStage.show();
        primaryStage.setTitle("JavaFX With OCSF");
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Provide a way for controllers to access the client
    public static CTMClient getClient() {
        return client;
    }

    // Provide access to the CredentialsController
    public static CredentialsController getCredentialsController() {
        return credentialsController;
    }
}