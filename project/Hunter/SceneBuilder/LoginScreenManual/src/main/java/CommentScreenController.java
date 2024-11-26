package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

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
        // TODO: Open a dialog or screen for writing a new comment
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
