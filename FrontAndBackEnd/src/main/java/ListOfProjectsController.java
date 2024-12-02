package main.java;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.client.clientController;
import main.objects.Project;

import java.io.IOException;
import java.util.ArrayList;

public class ListOfProjectsController {

    private static final String ProjectScreen = "/ProjectScreen.fxml";
    private static final String NewProjectScreen = "/NewProjectScreen.fxml";
    @FXML
    public ListView<String> projectListView2; // Ensure this matches the fx:id in FXML

    @FXML
    private Button logoutButton;

    @FXML
    private Button newProjectButton;

    @FXML
    private Button newUserButton;

    @FXML
    private ListView<String> projectListView;

    private static ArrayList<Project> listofProjects = new ArrayList<>();


    @FXML
    private void initialize() {
        System.out.println("Initializing ListOfProjectsController...");
        System.out.println("projectListView2 is: " + (projectListView2 == null ? "NULL" : "NOT NULL"));
        System.out.println("projectListView2: " + projectListView2); // Check referenc

        // Set click handler for the ListView
        projectListView2.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                int selectedIndex = projectListView2.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    openMockProjectScreen(listofProjects.get(selectedIndex)); // Open project screen
                }
            }
        });
        handleRefresh();
    }

    // Setter to initialize projects
    public static void setListOfProjects(ArrayList<Project> projects) {
        listofProjects = projects;
        System.out.println("in setter");


    }
    public void loadListView(){
        // Populate the ListView
        if (projectListView2 != null) {
            System.out.println("project list is not null in setter");
            projectListView2.getItems().clear();
            Platform.runLater(() -> {
                projectListView2.getItems().clear();
                for (Project project : listofProjects) {
                    projectListView2.getItems().add(project.getProjectName() + " | Due: " + project.getProjectDueDate());
                }
            });
        }
    }


    /**
     * Open the project screen for the selected project.
     */
    private void openMockProjectScreen(Project selectedProject) {
        try {
            System.out.println("Opening ProjectScreen for project: " + selectedProject.getProjectName());

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ProjectScreen));
            Parent root = loader.load();

            // Pass the selected project and user ID to the ProjectScreenController
            ProjectScreenController controller = loader.getController();
            controller.setProjectDetails(selectedProject, Main.userID);

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
        // Close the current screen
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleNewProject() {
        System.out.println("New Project button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NewProjectScreen));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Project");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRefresh() {
        System.out.println("Refresh button clicked!");
        clientController.sendMSG(1,"projectList",Main.userID);
        loadListView();
    }
}