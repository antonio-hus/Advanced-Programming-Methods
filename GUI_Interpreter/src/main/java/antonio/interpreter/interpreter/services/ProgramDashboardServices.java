package antonio.interpreter.interpreter.services;

import antonio.interpreter.interpreter.domain.statements.IStmt;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProgramDashboardServices {

    private IStmt programStatement;

    @FXML
    private TextField txtNumberOfPrgStates;

    @FXML
    private TableView<?> heapTable;

    @FXML
    private ListView<String> listOut;

    @FXML
    private ListView<String> listFileTable;

    @FXML
    private ListView<Integer> listPrgStateIds;

    @FXML
    private TableView<?> symTable;

    @FXML
    private ListView<String> listExeStack;

    @FXML
    private Button btnRunOneStep;

    public void setProgramStatement(IStmt programStatement) {
        this.programStatement = programStatement;
        initializeDashboard();
    }

    private void initializeDashboard() {
        // Populate UI elements with data from the programStatement
        txtNumberOfPrgStates.setText("1"); // Example, replace with actual logic
        listExeStack.getItems().add(programStatement.toString()); // Example for ExeStack
    }

    @FXML
    private void handleRunOneStep() {
        // Logic to execute one step and refresh UI
        System.out.println("Run one step for: " + programStatement);
    }
}
