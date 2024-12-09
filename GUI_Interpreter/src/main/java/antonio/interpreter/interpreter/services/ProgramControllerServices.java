////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.services;
import antonio.interpreter.interpreter.domain.expressions.*;
import antonio.interpreter.interpreter.domain.statements.*;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.RefType;
import antonio.interpreter.interpreter.domain.types.StringType;
import antonio.interpreter.interpreter.domain.utils.StmtParsing;
import antonio.interpreter.interpreter.domain.values.BoolValue;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.StringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Objects;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ProgramControllerServices {

    // PROGRAM CONTROLLER SERVICES STRUCTURE
    // Backend Elements
    List<IStmt> stmtList;
    // Frontend Elements
    // List View displays existing programs
    @FXML
    private ListView<String> listView;
    // Text Area for logfile path
    @FXML
    private TextArea textArea;


    // PROGRAM CONTROLLER SERVICES INITIALIZE
    @FXML
    private void initialize() {

        // Initialize ListView with programs
        if (stmtList != null) {

            ObservableList<String> programs = FXCollections.observableArrayList();
            for (IStmt stmt : stmtList) {
                programs.add(StmtParsing.toFString(stmt));
            }
            listView.setItems(programs);
        }

        // Initialize TextArea with default logfile path
        textArea.setText("C:\\\\Users\\\\anton\\\\OneDrive\\\\Documents\\\\GitHub\\\\Advanced-Programming-Methods\\\\GUI_Interpreter\\\\logs\\\\programState.log");
    }


    // PROGRAM CONTROLLER SERVICES HELPER METHODS

    // Backend Variable Setters
    // Set Statement List
    public void setStmtList(List<IStmt> stmtList) {
        this.stmtList = stmtList;
    }

    // Launch Dashboard
    // Launches the dashboard with the selected IStmt
    private void launchDashboard(IStmt stmt) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/antonio/interpreter/interpreter/program-dashboard.fxml"));
            Parent root = loader.load();

            // Pass the selected IStmt to the Dashboard controller
            ProgramDashboardServices controller = loader.getController();
            controller.setProgramStatement(stmt);

            // Apply the CSS stylesheet
            root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/antonio/interpreter/interpreter/styles.css")).toExternalForm());

            // Start the new scene
            Stage stage = new Stage();
            stage.setTitle("Program Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showErrorAlert("Error", "Dashboard Launch Failed", "Could not load the dashboard: " + e.getMessage());
        }
    }


    // Show alert
    // Creates & Launches Error Alert
    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    // PROGRAM CONTROLLER SERVICES HANDLERS
    // Start Button Handler
    public void handleStartButton() {
        // Get the selected index from the ListView
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        // If no item is selected
        if (selectedIndex == -1) {
            showErrorAlert("Error", "No item selected", "Please select an item from the list.");
        } else {
            IStmt selectedStmt = stmtList.get(selectedIndex);
            launchDashboard(selectedStmt);
        }
    }
}
