package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MessageFromSystemController {

    public TextField messageTitleField;
    public TextArea messageBodyArea;
    public Button closeButton;
    @FXML
    private Label bodyLabel;

    public void setMessage(String title, String body) {
        messageTitleField.setText(title);
        messageBodyArea.setText(body);
    }

    @FXML
    private void handleClose() {
        closeButton.getScene().getWindow().hide();
    }
}
