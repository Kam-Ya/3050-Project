package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private static final String CREDENTIALS_SCREEN = "/CredentialsScreen.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(CREDENTIALS_SCREEN)));
            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
            e.printStackTrace();
        }
        primaryStage.show();
        primaryStage.setTitle("JavaFX Without Maven");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
