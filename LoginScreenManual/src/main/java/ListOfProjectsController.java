package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ListOfProjectsController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button newProjectButton;

    @FXML
    private Button newUserButton;

    @FXML
    private ListView<String> projectListView;

    @FXML
    private void initialize() {
        // Example projects for display
        projectListView.getItems().addAll(
                "Project 1 - Due: 22-10-24 | 5 tasks",
                "Project 2 - Due: 23-10-24 | 3 tasks",
                "Project 3 - Due: 24-10-24 | 7 tasks"
        );

        // Set click handler for the ListView
        projectListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                String selectedProject = projectListView.getSelectionModel().getSelectedItem();
                if (selectedProject != null) {
                    openProjectScreen(selectedProject);
                }
            }
        });
    }

    private void openProjectScreen(String projectName) {
        try {
            // Load the ProjectScreen FXML
            // Bug testing
            System.out.println("ListOfProjectsController:");
            System.out.println(getClass().getResource("/ProjectScreen.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectScreen.fxml"));
            Parent root = loader.load();

            // Get the controller for ProjectScreen and pass the project data
            ProjectScreenController controller = loader.getController();
            controller.setProjectName(projectName);

            // Show the ProjectScreen in a new window
            Stage stage = new Stage();
            stage.setTitle("Project Screen");
            stage.setScene(new Scene(root));
            stage.show();

//            // close the current screen
//            Stage currentStage = (Stage) projectListView.getScene().getWindow();
//            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked!");
        // Logic to navigate to login screen
    }

    @FXML
    private void handleNewProject() {
        System.out.println("New Project button clicked!");
        // Logic to create a new project
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/NewProjectScreen.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

//            // Close the current login window
//            Stage currentStage = (Stage) newProjectButton.getScene().getWindow();
//            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewUser() {
        System.out.println("New User button clicked!");
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CreateUserScreen.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

//            // Close the current login window
//            Stage currentStage = (Stage) newUserButton.getScene().getWindow();
//            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}