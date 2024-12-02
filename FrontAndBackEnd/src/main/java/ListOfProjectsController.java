package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.objects.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListOfProjectsController {
    private static final String ProjectScreen = "/ProjectScreen.fxml";
    private static final String NewProjectScreen = "/NewProjectScreen.fxml";
    private static final String CreateNewUserScreen = "/CreateNewUserScreen.fxml";

    @FXML
    private Button logoutButton;

    @FXML
    private Button newProjectButton;

    @FXML
    private Button newUserButton;

    @FXML
    private static ListView<String> projectListView;

    private Integer userID;

    /**
     * Simulated projects list for mocking purposes.
     */
    public static ArrayList<Project> listofProjects;

    public void setUserID(Integer userID) {
        this.userID = userID;
        // debugging
        System.out.println("UserID: " + userID);

        loadMockProjects(); // Load mocked projects
    }

    @FXML
    private void initialize() {
        // Set click handler for the ListView
        projectListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                int selectedIndex = projectListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    openMockProjectScreen(listofProjects.get(selectedIndex)); // Use mocked project
                }
            }
        });
    }

    /**
     * Mock method to load projects.
     */
    public static void loadMockProjects() {
        listofProjects = new ArrayList<>();
        // Populate the ListView with mock data
        for (Project project : listofProjects) {
            String projectInfo = String.format(
                    "%s - Due: %s",
                    project.getProjectName(),
                    new java.text.SimpleDateFormat("MMM dd, yyyy").format(project.getProjectDueDate()) // Format Date for display
            );
            projectListView.getItems().add(projectInfo);
        }
    }

    /**
     * Mock method to open the project screen.
     *
     * @param selectedProject The selected project.
     */
    private void openMockProjectScreen(Project selectedProject) {
        try {
            System.out.println("Opening ProjectScreen for project: " + selectedProject.getProjectName());

            // Load the ProjectScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ProjectScreen));
            Parent root = loader.load();

            // Pass the selected project and user ID to the ProjectScreenController
            ProjectScreenController controller = loader.getController();
            controller.setProjectDetails(selectedProject, userID);

            // Show the ProjectScreen in a new window
            Stage stage = new Stage();
            stage.setTitle("Project: " + selectedProject.getProjectName());
            stage.setScene(new Scene(root));
            stage.show();

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(NewProjectScreen));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("New Project");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewUser() {
        System.out.println("New User button clicked!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CreateNewUserScreen));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Create New User");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}