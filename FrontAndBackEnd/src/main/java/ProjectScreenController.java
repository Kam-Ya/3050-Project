package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.java.TaskScreenController;

import java.io.IOException;

public class ProjectScreenController {

    @FXML
    private Button writeReportButton;

    @FXML
    private Button readReportButton;

    @FXML
    private Button newTaskButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label projectTitleLabel;

    @FXML
    private ListView<String> taskListView;


    @FXML
    private void initialize() {
        // Example task items
        taskListView.getItems().addAll(
                "Task 1 - Due: 22-10-24 | 5 notes",
                "Task 2 - Due: 23-10-24 | 3 notes",
                "Task 3 - Due: 24-10-24 | 7 notes"
        );
        // Set click handler for the ListView
        taskListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                String selectedTask = taskListView.getSelectionModel().getSelectedItem();
                if (selectedTask != null) {
                    openTaskScreen(selectedTask);
                }
            }
        });
    }

    private void openTaskScreen(String taskDetails) {
        try {
            // Load TaskScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskScreen.fxml"));
            Parent root = loader.load();

            // Get the controller for TaskScreen and pass the task details
            TaskScreenController controller = loader.getController();
            controller.setTaskDetails(taskDetails);

            // Create a new stage for TaskScreen
            Stage stage = new Stage();
            stage.setTitle("Task Screen");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setProjectName(String projectName) {
        // Set the project name in the title
        projectTitleLabel.setText(projectName);

        // Example: Populate tasks based on the project name
        taskListView.getItems().clear();
        taskListView.getItems().addAll(
                "Task 1 - Due: 22-10-24 | 5 notes",
                "Task 2 - Due: 23-10-24 | 3 notes",
                "Task 3 - Due: 24-10-24 | 7 notes"
        );
    }

    @FXML
    private void handleWriteReport() {
        System.out.println("Write Report button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/WriteReportScreen.fxml"));
            Parent root = loader.load();

            WriteReportScreenController controller = loader.getController();
            controller.setProjectName("Project N"); // Pass project name dynamically

            Stage stage = new Stage();
            stage.setTitle("Write Report");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReadReport() {
        System.out.println("Read Report button clicked!");
        try {
            // Load the ListReportsScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListReportsScreen.fxml"));
            Parent root = loader.load();

            // Get the controller for ListReportsScreen (if you need to pass any data)
            ListReportsController controller = loader.getController();
            // Example: Pass data to the controller if needed
            // controller.setSomeData(someData);

            // Create a new stage for ListReportsScreen
            Stage stage = new Stage();
            stage.setTitle("List Reports");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//TODO: Make a new class for Creating Task
    @FXML
    private void handleNewTask() {
        System.out.println("New Task button clicked!");
        try {
            // Load the ListReportsScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskScreen.fxml"));
            Parent root = loader.load();

            // Create a new stage for ListReportsScreen
            Stage stage = new Stage();
            stage.setTitle("New Task");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAbout() {
        System.out.println("About button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/aboutProjectScreen.fxml"));
            Parent root = loader.load();

            aboutProjectController aboutController = loader.getController();
            aboutController.setProjectDetails("Test");

            Stage stage = new Stage();
            stage.setTitle("About Project");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked!");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
