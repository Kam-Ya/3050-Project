package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CommentScreenController {

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
    private void handleEdit() {
        String selectedComment = commentsListView.getSelectionModel().getSelectedItem();
        if (selectedComment == null) {
            showAlert("Error", "No comment selected for editing!");
            return;
        }

        System.out.println("Editing comment: " + selectedComment);
        // TODO: Open a dialog or screen to edit the selected comment
    }

    @FXML
    private void handleRefresh() {
        System.out.println("Refreshing comments...");
        // TODO: Refresh the comment list (e.g., reload from a database)
    }

    @FXML
    private void handleClose() {
        System.out.println("Close button clicked.");
        // TODO: Close the CommentScreen and return to the previous screen
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
