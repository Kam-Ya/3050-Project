package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.java.TaskScreenController;

import java.io.IOException;

public class ProjectScreenController {

    public Label descriptionLabel;
    public Label projectDueDateLabel;
    private Project project;
    private User currentUser;
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
    private TextArea projectDescriptionArea;

    // Method to set project details
    public void setProjectDetails(Project project, Integer userID) {
        this.project = project;
        // Set project details
        System.out.println("setProjectDetails called for project: " + project.getProjectName());

        projectTitleLabel.setText(project.getProjectName());
        projectDueDateLabel.setText(project.getProjectDueDate().toString());
        projectDescriptionArea.setText(project.Desc);

        // Populate the tasks ListView
        taskListView.getItems().clear();
        for (Task task : project.tasks) {
            // Check if the currentUser is assigned to the task
            boolean isAssigned = task.getAsignees().contains(userID);
            String taskInfo = String.format(
                    "%s - Due: %s | Priority: %d%s",
                    task.getTaskName(),
                    task.getTaskDueDate(),
                    task.getPriority(),
                    isAssigned ? " *" : "" // Add an asterisk if assigned
            );
            taskListView.getItems().add(taskInfo);
        }
    }
    @FXML
    private void initialize() {
        projectDescriptionArea.setEditable(false);

        // Set double-click handler for the ListView
        taskListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && project != null) { // Ensure project is not null
                    Task selectedTask = project.tasks.get(selectedIndex); // Get Task by index
                    openTaskScreen(selectedTask); // Pass the Task object
                }
            }
        });
    }
    /**
     * Opens the TaskScreen with detailed task information.
     *
     * @param taskDetails A string containing task details in the format:
     *                    "Task Name - Due: Date | Priority | Assigned User"
     */
    private void openTaskScreen(Task selectedTask) {
        try {
            // Load the TaskScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskScreen.fxml"));
            Parent root = loader.load();

            // Pass the selected Task to the TaskScreenController
            TaskScreenController controller = loader.getController();
            controller.setTaskDetails(
                    selectedTask.getTaskName(),
                    selectedTask.getDesc(),
                    selectedTask.getTaskDueDate().toString(),
                    selectedTask.getAsignees(), // Pass the ArrayList of assigned users
                    String.valueOf(selectedTask.getPriority())
            );

            // Create a new stage for the TaskScreen
            Stage stage = new Stage();
            stage.setTitle("Task Screen - " + selectedTask.getTaskName());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setProjectName(String projectName) {
        // Set the project name in the title
        projectTitleLabel.setText(projectName);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewTaskScreen.fxml"));
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

    // Error screen
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
}