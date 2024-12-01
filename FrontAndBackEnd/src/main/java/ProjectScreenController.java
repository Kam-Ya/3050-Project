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
    public void setProjectDetails(Project project) {
        // Set project details
        System.out.println("setProjectDetails called for project: " + project.getProjectName());

        projectTitleLabel.setText(project.getProjectName());
        projectDueDateLabel.setText(project.getProjectDueDate().toString());
        projectDescriptionArea.setText(project.Desc);

        // Populate the tasks ListView
        taskListView.getItems().clear();
        for (Task task : project.tasks) {
            taskListView.getItems().add(String.format(
                    "%s - Due: %s | Priority: %d",
                    task.getTaskName(),
                    task.getTaskDueDate(),
                    task.getPriority()
            ));
        }
    }
    @FXML
    private void initialize() {
        // Example task items
        taskListView.getItems().addAll(
                "Task 1 - Due: 22-10-24 | 5 notes | John",
                "Task 2 - Due: 23-10-24 | 3 notes | Jain",
                "Task 3 - Due: 24-10-24 | 7 notes | Jack"
        );
        projectDescriptionArea.setEditable(false);
        projectDescriptionArea.setText("This is a project description");
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

    /**
     * Opens the TaskScreen with detailed task information.
     *
     * @param taskDetails A string containing task details in the format:
     *                    "Task Name - Due: Date | Priority | Assigned User"
     */
    private void openTaskScreen(String taskDetails) {
        try {
            // Validate the input format
            if (taskDetails == null || !taskDetails.contains(" - Due: ") || !taskDetails.contains(" | ")) {
                throw new IllegalArgumentException("Invalid task details format: " + taskDetails);
            }

            // Parse task details
            String[] parts = taskDetails.split(" - Due: ");
            String taskName = parts[0].trim(); // Task name
            String[] otherDetails = parts[1].split(" \\| ");

            // Extract details with fallback defaults
            String dueDate = otherDetails.length > 0 ? otherDetails[0].trim() : "Unknown Due Date";
            String notesCount = otherDetails.length > 1 ? otherDetails[1].trim() : "0 notes";
            String assignedUser = otherDetails.length > 2 ? otherDetails[2].trim() : "Unassigned";

            // Load TaskScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskScreen.fxml"));
            Parent root = loader.load();

            // Get the controller for TaskScreen and pass the task details
            TaskScreenController controller = loader.getController();
            String taskDescription = String.format("Task with %s", notesCount); // Example description
            controller.setTaskDetails(taskName, taskDescription, dueDate, assignedUser, "Default Priority");

            // Create a new stage for TaskScreen
            Stage stage = new Stage();
            stage.setTitle("Task Screen - " + taskName);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing task details: " + e.getMessage());
            showSystemMessage("Error", "Invalid task details: " + taskDetails);
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
