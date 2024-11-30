package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class ListReportsController {

    @FXML
    private ListView<String> reportsListView;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // Populate the ListView with example reports
        List<String> exampleReports = List.of(
                "Do we need this? | Date: 22-10-24 | Hunter",
                "How much will this increase cost? | Date: 22-10-24 | Kam",
                "This seems like a design issue | Date: 22-10-24 | Jamie",
                "Report | Date: 22-10-24 | User",
                "Report | Date: 22-10-24 | User"
        );
        reportsListView.getItems().addAll(exampleReports);

        // Set click handler for the ListView
        reportsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                String selectedReport = reportsListView.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    openReadReportScreen(selectedReport);
                }
            }
        });
    }

    private void openReadReportScreen(String reportDetails) {
        // Logic to load and display the ReadReportScreen with the selected report
        System.out.println("Opening ReadReportScreen for: " + reportDetails);
    }

    @FXML
    private void handleClose() {
        // Close the current screen
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
