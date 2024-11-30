package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CreateCommentController {

    @FXML
    private TextArea bodyTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void handleSend() {
        String body = bodyTextArea.getText().trim();

        if (body.isEmpty()) {
            System.out.println("Error: Comment body cannot be empty!");
            return;
        }

        // Simulate sending the comment
        System.out.println("Comment Sent: " + body);

        closeScreen();
    }

    @FXML
    private void handleCancel() {
        System.out.println("Comment creation canceled.");
        closeScreen();
    }

    private void closeScreen() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
