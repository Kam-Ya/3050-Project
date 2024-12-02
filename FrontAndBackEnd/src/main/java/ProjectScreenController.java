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
import main.objects.Project;
import main.objects.Task;

import java.io.IOException;

public class ProjectScreenController {

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label projectDueDateLabel;

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

    private Project project;

    /**
     * Set project details and populate the UI.
     */
    public void setProjectDetails(Project project, Integer userID) {
        this.project = project;

        System.out.println("setProjectDetails called for project: " + project.getProjectName());

        // Set project details
        projectTitleLabel.setText(project.getProjectName());
        projectDueDateLabel.setText(project.getProjectDueDate().toString());
        projectDescriptionArea.setText(project.Desc != null ? project.Desc : "No description available.");

        // Populate the tasks ListView
        taskListView.getItems().clear();
        for (Task task : project.tasks) {
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
            if (event.getClickCount() == 2) {
                int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && project != null && selectedIndex < project.tasks.size()) {
                    Task selectedTask = project.tasks.get(selectedIndex);
                    openTaskScreen(selectedTask);
                }
            }
        });
    }

    /**
     * Open TaskScreen with the selected task's details.
     */
    private void openTaskScreen(Task selectedTask) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskScreen.fxml"));
            Parent root = loader.load();

            TaskScreenController controller = loader.getController();
            controller.setTaskDetails(
                    selectedTask.getTaskName(),
                    selectedTask.getDesc() != null ? selectedTask.getDesc() : "No description available.",
                    selectedTask.getTaskDueDate().toString(),
                    selectedTask.getAsignees(),
                    String.valueOf(selectedTask.getPriority())
            );

            Stage stage = new Stage();
            stage.setTitle("Task: " + selectedTask.getTaskName());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleWriteReport() {
        System.out.println("Write Report button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/WriteReportScreen.fxml"));
            Parent root = loader.load();

            WriteReportScreenController controller = loader.getController();
            controller.setProjectName(project.getProjectName());

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListReportsScreen.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("List Reports");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewTask() {
        System.out.println("New Task button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewTaskScreen.fxml"));
            Parent root = loader.load();

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
            aboutController.setProjectDetails(project.getProjectName());

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