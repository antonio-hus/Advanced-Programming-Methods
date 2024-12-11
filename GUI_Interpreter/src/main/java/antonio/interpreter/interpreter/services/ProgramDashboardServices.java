////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.services;
import antonio.interpreter.interpreter.controller.BasicController;
import antonio.interpreter.interpreter.controller.Controller;
import antonio.interpreter.interpreter.controller.ControllerException;
import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.state.*;
import antonio.interpreter.interpreter.domain.statements.IStmt;
import antonio.interpreter.interpreter.domain.values.StringValue;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Map;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ProgramDashboardServices {

    // PROGRAM DASHBOARD SERVICES STRUCTURE
    // Backend Elements
    private PrgState prgState;
    private Controller controller;

    // Frontend Elements
    @FXML private TextField txtNumberOfPrgStates;
    @FXML private TableView<Map.Entry<Integer, Value>> heapTable;
    @FXML private TableColumn<Map.Entry<Integer, Value>, Integer> heapAddressColumn;
    @FXML private TableColumn<Map.Entry<Integer, Value>, Value> heapValueColumn;
    @FXML private ListView<Value> listOut;
    @FXML private ListView<StringValue> listFileTable;
    @FXML private ListView<Integer> listPrgStateIds;
    @FXML private TableView<Map.Entry<String, Value>> symTable;
    @FXML private TableColumn<Map.Entry<String, Value>, String> symTableVariableNameColumn;
    @FXML private TableColumn<Map.Entry<String, Value>, Value> symTableValueColumn;
    @FXML private ListView<IStmt> listExeStack;
    @FXML private Button btnRunOneStep;



    // PROGRAM DASHBOARD SERVICES INITIALIZE
    public void initializeDashboard(IStmt programStatement, String logFilePath) {

        // Creating program
        this.prgState = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), programStatement);
        this.controller = new BasicController(1, logFilePath, this.prgState);

        // Update View
        updateDashboard();

        // Event Listeners
        listPrgStateIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateExecStack();
            updateSymTable();
        });
    }

    private void updateDashboard() {
        updateProgramStateCount();
        updateHeapTable();
        updateOutList();
        updateFileList();
        updateIdentifierList();
        updateExecStack();
        updateSymTable();
    }


    // PROGRAM DASHBOARD SERVICES HELPER METHODS
    private void updateProgramStateCount() {
        txtNumberOfPrgStates.setText(controller.getPrgListCount().toString());
    }

    private void updateHeapTable() {
        ObservableList<Map.Entry<Integer, Value>> heapData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getHeap().getContent().entrySet()
        );

        heapAddressColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        heapValueColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue()));
        heapTable.setItems(heapData);
    }

    private void updateOutList() {
        ObservableList<Value> outputListData = FXCollections.observableArrayList(controller.getPrgList().getFirst().getOutputList().getContent());

        listOut.setItems(outputListData);
    }

    private void updateFileList() {
        ObservableList<StringValue> fileTableList = FXCollections.observableArrayList(controller.getPrgList().getFirst().getFileTable().keySet());

        listFileTable.setItems(fileTableList);
    }

    private void updateIdentifierList() {
        ObservableList<Integer> identifierList = FXCollections.observableArrayList();
        controller.getPrgList().forEach(prgState -> identifierList.add(prgState.getProgramID()));

        listPrgStateIds.setItems(identifierList);
    }

    private void updateSymTable() {
        int selectedIndex = listPrgStateIds.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex > this.controller.getPrgListCount()) {
            return;
        }

        ObservableList<Map.Entry<String, Value>> symTableData = FXCollections.observableArrayList(
                controller.getPrgList().get(selectedIndex).getSymbolsTable().getContent().getContent().entrySet()
        );

        symTableVariableNameColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        symTableValueColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue()));
        symTable.setItems(symTableData);
    }

    private void updateExecStack() {
        int selectedIndex = listPrgStateIds.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex > this.controller.getPrgListCount()) {
            return;
        }

        ObservableList<IStmt> execStackList = FXCollections.observableArrayList();
        execStackList.addAll(controller.getPrgList().get(selectedIndex).getExecutionStack().toList());

        listExeStack.setItems(execStackList);
    }


    // Show alert
    // Creates & Launches Error Alert
    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // PROGRAM DASHBOARD SERVICES HANDLERS
    @FXML
    private void handleRunOneStep() {
        try{
            this.controller.oneStepForAllPrg(this.controller.getPrgList());
            updateDashboard();
        } catch (ControllerException e) {
            showErrorAlert("Program One Step Run Failed", e.toString());
        }
    }
}
