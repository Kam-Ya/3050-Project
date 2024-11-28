package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.List;

public class ListReportsController {

    @FXML
    private VBox reportsContainer;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Example: Load reports on initialization (can be replaced with actual data)
        List<String> exampleReports = List.of(
                "Do we need this?|22-10-24|Hunter",
                "How much will this increase cost?|22-10-24|Kam",
                "This seems like a design issue|22-10-24|Jamie",
                "Report|22-10-24|User",
                "Report|22-10-24|User"
        );

        populateReports(exampleReports);
    }

    @FXML
    private void handleClose() {
        // Logic to close the screen (can be modified to suit your application)
        System.out.println("Close button clicked");
        // Example: Close the window
        closeButton.getScene().getWindow().hide();
    }

    private void populateReports(List<String> reports) {
        reportsContainer.getChildren().clear(); // Clear existing items

        for (String report : reports) {
            String[] details = report.split("\\|");
            String content = details[0];
            String date = details[1];
            String user = details[2];

            HBox reportRow = new HBox();
            reportRow.setSpacing(10);

            Label contentLabel = new Label(content);
            Label dateUserLabel = new Label("Date: " + date + " | " + user);
            Region spacer = new Region();
            HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            reportRow.getChildren().addAll(contentLabel, spacer, dateUserLabel);
            reportsContainer.getChildren().add(reportRow);
        }
    }
}