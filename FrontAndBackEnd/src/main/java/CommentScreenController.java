package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CommentScreenController {

    public Button deleteButton;
    public Button writeButton;
    public Button editButton;
    public Button refreshButton;
    public Button closeButton;
    public Label commentTitleLabel;
    @FXML
    private ListView<String> commentsListView;

    @FXML
    private void initialize() {
        // Example comments
        commentsListView.getItems().addAll(
                "Do we need this? | Date: 22-10-24 | Hunter",
                "How much will this increase cost? | Date: 22-10-24 | Kam",
                "This seems like a design issue | Date: 22-10-24 | Jamie"
        );
    }

    /*
    * deletes selected comments
    * */
    @FXML
    private void handleDelete() {
        String selectedComment = commentsListView.getSelectionModel().getSelectedItem();
        if (selectedComment == null) {
            showAlert("Error", "No comment selected for deletion!");
            return;
        }

        // Confirm deletion
        Optional<ButtonType> result = showConfirmation("Delete Comment", "Are you sure you want to delete this comment?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            commentsListView.getItems().remove(selectedComment);
            System.out.println("Comment deleted: " + selectedComment);
        }
    }

    /*
     * Launches CreateCommentScreen
     * */
    @FXML
    private void handleWrite() {
        System.out.println("Write button clicked.");
        try {
            // Load the ListReportsScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateCommentScreen.fxml"));
            Parent root = loader.load();

            // Get the controller for ListReportsScreen (if you need to pass any data)
            CreateCommentController controller = loader.getController();
            // Example: Pass data to the controller if needed
            // controller.setSomeData(someData);

            // Create a new stage for ListReportsScreen
            Stage stage = new Stage();
            stage.setTitle("Leave A Note");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRefresh() {
        System.out.println("Refreshing comments...");
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked.");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // function to handle alert
    private Optional<ButtonType> showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
