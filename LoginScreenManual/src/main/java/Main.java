package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        // Bug testing
        System.out.println("Main:");
        System.out.println(getClass().getResource("/CredentialsScreen.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CredentialsScreen.fxml")));
        primaryStage.setTitle("JavaFX Without Maven");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}