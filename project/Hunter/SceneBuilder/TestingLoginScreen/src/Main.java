import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the initial scene (e.g., "Login.fxml")
            Parent root = FXMLLoader.load(getClass().getResource("loginScreen2.fxml"));

            // Set the scene with the root node
            Scene scene = new Scene(root);

            // Set the stage properties
            primaryStage.setTitle("Your Application Title");
            primaryStage.setScene(scene);

            // Show the primary stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // This will call the start() method
    }
}