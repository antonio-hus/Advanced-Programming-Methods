////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.services;
import antonio.interpreter.interpreter.controller.BasicController;
import antonio.interpreter.interpreter.controller.Controller;
import antonio.interpreter.interpreter.controller.ControllerException;
import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyIStack;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStack;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.procedures.IProcedure;
import antonio.interpreter.interpreter.domain.state.*;
import antonio.interpreter.interpreter.domain.statements.IStmt;
import antonio.interpreter.interpreter.domain.values.StringValue;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.util.Map;
import java.util.List;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ProgramDashboardServices {

    // PROGRAM DASHBOARD SERVICES STRUCTURE
    // Backend Elements
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
    @FXML private TableView<Map.Entry<Integer, Pair<Integer, MyIList<Integer>>>> semaphoreTable;
    @FXML private TableColumn<Map.Entry<Integer, Pair<Integer, MyIList<Integer>>>, Integer> semaphoreTableIndex;
    @FXML private TableColumn<Map.Entry<Integer, Pair<Integer, MyIList<Integer>>>, Integer> semaphoreTableAddress;
    @FXML private TableColumn<Map.Entry<Integer, Pair<Integer, MyIList<Integer>>>, MyIList<Integer>> semaphoreTableValues;
    @FXML private TableView<Map.Entry<Integer, Integer>> latchTable;
    @FXML private TableColumn<Map.Entry<Integer, Integer>, Integer> latchTableLocation;
    @FXML private TableColumn<Map.Entry<Integer, Integer>, Integer> latchTableValue;
    @FXML private TableView<Map.Entry<Integer, Integer>> lockTable;
    @FXML private TableColumn<Map.Entry<Integer, Integer>, Integer> lockTableLocation;
    @FXML private TableColumn<Map.Entry<Integer, Integer>, Integer> lockTableValue;
    @FXML private TableView<Map.Entry<String, Pair<MyIList<String>, IStmt>>> procTable;
    @FXML private TableColumn<Map.Entry<String, Pair<MyIList<String>, IStmt>>, String> procTableSignature;
    @FXML private TableColumn<Map.Entry<String, Pair<MyIList<String>, IStmt>>, IStmt> procTableBody;
    @FXML private ListView<IStmt> listExeStack;
    @FXML private Button btnRunOneStep;



    // PROGRAM DASHBOARD SERVICES INITIALIZE
    public void initializeDashboard(IStmt programStatement, List<IProcedure> procedureList, String logFilePath) throws MyStackException, MyDictionaryException {

        // Creating program & controller
        MyIStack<ISymTable> symTableMyIStack = new MyStack<>();
        symTableMyIStack.push(new SymTable());

        IProcTable procTable = new ProcTable();
        for(IProcedure proc : procedureList){
            procTable.put(proc.getName(), new Pair<>(proc.getParameters(), proc.getStmt()));
        }

        PrgState prgState = new PrgState(new ExeStack(), symTableMyIStack, new OutList(), new FileTable(), new Heap(), new SemaphoreTable(), new LatchTable(), new LockTable(), new BarrierTable(), procTable, programStatement);
        this.controller = new BasicController(1, logFilePath, prgState);

        // Update View
        updateDashboard();
        updateProcTable();

        // Event Listeners
        listPrgStateIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateExecStack();
            try {
                updateSymTable();
            } catch (MyStackException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void updateDashboard() throws MyStackException {

        // Updating all elements of the screen
        updateProgramStateCount();
        updateHeapTable();
        updateOutList();
        updateFileList();
        updateIdentifierList();
        updateExecStack();
        updateSymTable();
        updateSemaphoreTable();
        updateLatchTable();
        updateLockTable();
    }


    // PROGRAM DASHBOARD SERVICES HELPER METHODS
    // Update Program State List Count
    private void updateProgramStateCount() {
        txtNumberOfPrgStates.setText(controller.getPrgListCount().toString());
    }

    // Update Proc Table entries
    private void updateProcTable() {
        ObservableList<Map.Entry<String, Pair<MyIList<String>, IStmt>>> procTableData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getProcTable().getContent().entrySet()
        );

        procTableSignature.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey() + data.getValue().getValue().getKey().toString()));
        procTableBody.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue().getValue()));
        procTable.setItems(procTableData);
        procTable.refresh();
    }

    // Update Lock Table entries
    private void updateLockTable() {
        ObservableList<Map.Entry<Integer, Integer>> lockTableData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getLatchTable().getContent().entrySet()
        );

        lockTableLocation.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        lockTableValue.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue()));
        lockTable.setItems(lockTableData);
        lockTable.refresh();
    }

    // Update Latch Table entries
    private void updateLatchTable() {
        ObservableList<Map.Entry<Integer, Integer>> latchTableData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getLatchTable().getContent().entrySet()
        );

        latchTableLocation.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        latchTableValue.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue()));
        latchTable.setItems(latchTableData);
        latchTable.refresh();
    }

    // Update Semaphore Table entries
    private void updateSemaphoreTable() {
        ObservableList<Map.Entry<Integer, Pair<Integer, MyIList<Integer>>>> semaphoreTableData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getSemaphoreTable().getContent().entrySet()
        );

        semaphoreTableIndex.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        semaphoreTableAddress.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue().getKey()));
        semaphoreTableValues.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue().getValue()));
        semaphoreTable.setItems(semaphoreTableData);
        semaphoreTable.refresh();
    }

    // Update Heap Table entries
    private void updateHeapTable() {
        ObservableList<Map.Entry<Integer, Value>> heapData = FXCollections.observableArrayList(
                controller.getPrgList().getFirst().getHeap().getContent().entrySet()
        );

        heapAddressColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKey()));
        heapValueColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValue()));
        heapTable.setItems(heapData);
    }

    // Update Output List entries
    private void updateOutList() {
        ObservableList<Value> outputListData = FXCollections.observableArrayList(controller.getPrgList().getFirst().getOutputList().getContent());

        listOut.setItems(outputListData);
    }

    // Update File List entries
    private void updateFileList() {
        ObservableList<StringValue> fileTableList = FXCollections.observableArrayList(controller.getPrgList().getFirst().getFileTable().keySet());

        listFileTable.setItems(fileTableList);
    }

    // Update Program State Identifier List entries
    private void updateIdentifierList() {
        ObservableList<Integer> identifierList = FXCollections.observableArrayList();
        controller.getPrgList().forEach(prgState -> identifierList.add(prgState.getProgramID()));

        listPrgStateIds.setItems(identifierList);
    }

    // Update Symbols Table entries
    private void updateSymTable() throws MyStackException {
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
        symTable.refresh();
    }

    // Update Execution Stack entries
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

        // Create a new alert
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // Set alert content
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Launch alert
        alert.showAndWait();
    }

    // PROGRAM DASHBOARD SERVICES HANDLERS
    @FXML
    private void handleRunOneStep() {
        try{

            // Run one step for all programs
            this.controller.oneStepForAllPrg();

            // Update dashboard view
            updateDashboard();

        } catch (ControllerException | MyStackException e) {

            // Notify errors
            showErrorAlert("Program One Step Run Failed", e.toString());
        }
    }
}
