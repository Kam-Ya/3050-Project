import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
        // Logic for writing a report
    }

    @FXML
    private void handleReadReport() {
        System.out.println("Read Report button clicked!");
        // Logic for reading a report
    }

    @FXML
    private void handleNewTask() {
        System.out.println("New Task button clicked!");
        // Logic for creating a new task
    }

    @FXML
    private void handleAbout() {
        System.out.println("About button clicked!");
        // Logic for showing about information
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked!");
        // Logic for closing this screen
    }
}
