package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class aboutProjectController {

    @FXML
    private Label projectDetailsLabel;

    /**
     * Sets the project details to be displayed in the screen.
     * @param details The details about the project.
     */
    public void setProjectDetails(String details) {
        projectDetailsLabel.setText(details);
    }

    /**
     * Closes the about project screen.
     */
    @FXML
    private void handleCloseButton() {
        Stage stage = (Stage) projectDetailsLabel.getScene().getWindow();
        stage.close();
    }
}
