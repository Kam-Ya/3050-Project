import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ListOfProjectsController {

    @FXML
    private VBox projectList;

    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked!");
        // Add navigation to login screen
    }

    @FXML
    private void handleNewProject() {
        System.out.println("New Project button clicked!");
        // Add functionality to create a new project
    }

    @FXML
    private void handleNewUser() {
        System.out.println("New User button clicked!");
        // Add functionality to create a new user
    }

    @FXML
    public void initialize() {
        // Sample dynamic content for the project list
        for (int i = 1; i <= 6; i++) {
            Label projectLabel = new Label("Project " + i + " - Due: 22-10-24 | 5 tasks");
            projectLabel.setStyle("-fx-padding: 10; -fx-border-color: #CCCCCC; -fx-border-width: 1;");
            projectList.getChildren().add(projectLabel);
        }
    }
}
