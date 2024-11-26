import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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
    }

    @FXML
    private void handleNewUser() {
        System.out.println("New User button clicked!");
        // Logic to create a new user
    }
}
