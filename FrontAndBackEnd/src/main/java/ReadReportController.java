package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReadReportController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea bodyTextArea;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Initialize the screen with empty fields or placeholder text
        titleTextField.setText("");
        bodyTextArea.setText("");
    }

    public void setReportData(String title, String body) {
        // Populate the screen with the report data
        titleTextField.setText(title);
        bodyTextArea.setText(body);
    }

    @FXML
    private void handleClose() {
        // Logic to close the screen
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}