import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskScreenController {

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField dueDateField;

    @FXML
    private ComboBox<String> assignedToDropdown;

    @FXML
    private ComboBox<String> priorityDropdown;

    @FXML
    private Button completeButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Example priority levels
        priorityDropdown.getItems().addAll("1", "2", "3", "4", "5");

        // Example users
        assignedToDropdown.getItems().addAll("User A", "User B", "User C");


    }

    public void setTaskDetails(String taskDetails) {
        // Parse task details and update fields (example logic)
        String[] details = taskDetails.split(" - Due: ");
        String taskName = details[0];
        String dueDate = details[1].split(" \\|")[0];

        // Set task details
        descriptionField.setText("Details for " + taskName);
        dueDateField.setText(dueDate);

        // Example: Populate dropdowns
        priorityDropdown.getItems().addAll("1", "2", "3", "4", "5");
        assignedToDropdown.getItems().addAll("User A", "User B", "User C");
    }


    @FXML
    private void handleComplete() {
        System.out.println("Complete button clicked!");
        // Logic to mark task as complete
    }

    @FXML
    private void handleNotes() {
        System.out.println("Notes button clicked!");
        // Logic to view or edit task notes
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked!");
        // Logic to delete the task
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked!");
        // Logic to close the task screen
    }
}
