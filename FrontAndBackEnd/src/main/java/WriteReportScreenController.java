package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WriteReportScreenController {

    @FXML
    public Label reportTitleLabel;
    public Button cancelButton;
    public Button submitButton;
    @FXML
    private TextField titleField;

    @FXML
    private TextArea bodyField;

    // Handles the Submit button action
    @FXML
    private void handleSubmit() {
        String title = titleField.getText();
        String body = bodyField.getText();

        if (title.isEmpty() || body.isEmpty()) {
            System.out.println("Both Title and Body must be filled out!");
            return;
        }

        // Save the report (simulate saving)
        System.out.println("Report Submitted:");
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);

    }

    // Handles the Cancel button action
    @FXML
    private void handleCancel() {
        System.out.println("Report writing canceled.");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // Method to set project details dynamically (e.g., project name)
    public void setProjectName(String projectName) {
        // Example: Update the title of the screen dynamically
        reportTitleLabel.setText("WRITE REPORT FOR " + projectName);
    }
}
