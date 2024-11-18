////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package controller;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyIStack;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.state.IExeStack;
import domain.statements.IStmt;
import domain.statements.StmtException;
import domain.values.RefValue;
import domain.values.Value;
import repository.BasicRepository;
import repository.Repository;
import repository.RepositoryException;

import java.util.*;
import java.util.stream.Collectors;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BasicController implements Controller {

    // BASIC CONTROLLER STRUCTURE
    Repository repository;
    boolean displayFlag;


    // BASIC CONTROLLER CONSTRUCTOR
    public BasicController(int flagConfiguration, String logFilePath) {

        // Set up repository
        repository = new BasicRepository(logFilePath);

        // Set display flag
        switch (flagConfiguration){
            case 0: this.displayFlag = false; break;
            case 1: this.displayFlag = true; break;
        }
    }

    // BASIC CONTROLLER METHODS
    // Add new Program State
    @Override
    public void addPrgState(PrgState newProgramState) {
        this.repository.addPrgState(newProgramState);
    }
    // Add new Program State at a given index
    @Override
    public void addPrgState(PrgState newProgramState, int index) throws MyListException {
        this.repository.addPrgState(newProgramState, index);
    }
    // Remove a Program State
    @Override
    public void removePrgState(int index) throws MyListException {
        this.repository.removePrgState(index);
    }
    // Gets the currently running program on the given index
    @Override
    public PrgState getCrtPrg(int index) throws MyListException {
        return repository.getCrtPrg(index);
    }
    // Gets all programs
    @Override
    public MyIList<PrgState> getPrgStates() {
        return repository.getPrgStates();
    }
    // Execute one step - one statement
    @Override
    public PrgState oneStep(PrgState state) throws ControllerException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException {

        // Get the current program state
        IExeStack stack = state.getExecutionStack();

        // Check if there are statements left to execute
        if(stack.isEmpty()){
            throw new ControllerException("Program State Error - Execution Stack is Empty");
        }

        // Execute one statement
        IStmt currentStatement = stack.pop();
        PrgState newState = currentStatement.execute(state);

        // Display state if in Display Mode
        if(displayFlag)
            System.out.println(newState);

        // Log to file
        this.repository.logPrgStateExec();

        // Run Garbage Collector
        state.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(state.getSymbolsTable().getContent().getContent().values()), state.getHeap().getContent()));

        // Log to file
        this.repository.logPrgStateExec();

        // Return new state
        return newState;
    }
    // Execute one step - one statement
    public void oneStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException {
        // Get the current program state - the one last added into the list
        PrgState program = repository.getCrtPrg(repository.getPrgStates().size()-1);

        // Check if there are statements left to execute
        if(program.getExecutionStack().isEmpty()){
            throw new ControllerException("Program State Error - Execution Stack is Empty");
        }

        // Execute one statement
        IStmt currentStatement = program.getExecutionStack().pop();
        PrgState newState = currentStatement.execute(program);

        // Display state if in Display Mode
        if(displayFlag)
            System.out.println(newState);

        // Log to file
        this.repository.logPrgStateExec();

        // Run Garbage Collector
        newState.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(newState.getSymbolsTable().getContent().getContent().values()), newState.getHeap().getContent()));

        // Log to file
        this.repository.logPrgStateExec();
    }
    // Execute entire program - all statements
    @Override
    public void allStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException {

        // Get the current program state - the one last added into the list
        PrgState program = repository.getCrtPrg(repository.getPrgStates().size()-1);

        // Log to file
        this.repository.clearLogFile();
        this.repository.logPrgStateExec();

        while(!program.getExecutionStack().isEmpty()){
            oneStep(program);
        }
    }

    // Garbage Collector Related Methods
    @Override
    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {

        // Filter the heap based on symbolic table reachable addresses
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {

        // Get all reachable addresses by including those in the heap cells
        List<Integer> reachable = getReachableAddresses(symTableAddr, heap);

        // Filter the heap based on reachable addresses
        return heap.entrySet().stream()
                .filter(e -> reachable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getReachableAddresses(List<Integer> symTableAddr, Map<Integer, Value> heap) {

        // Initialize with symTable addresses
        List<Integer> reachable = new ArrayList<>(symTableAddr);

        // Track visited addresses to avoid loops
        Set<Integer> visited = new HashSet<>(reachable);

        // Traverse all reachable addresses
        for (int i = 0; i < reachable.size(); i++) {
            Integer addr = reachable.get(i);
            Value value = heap.get(addr);

            // Continue exploring as long as the value is a RefValue
            while (value instanceof RefValue) {
                Integer refAddr = ((RefValue) value).getAddress();

                // If this address has not been visited, add it to explore further
                if (visited.add(refAddr)) {
                    reachable.add(refAddr);

                    // Update value to follow the reference
                    value = heap.get(refAddr);
                }

                // If already visited, avoid re-processing
                else {
                    break;
                }
            }
        }

        return reachable;
    }

    // Flags Related Methods
    // Display Mode
    // Set display mode ON
    @Override
    public void setDisplayMode() {
        displayFlag = true;
    }

    // Set display mode OFF
    @Override
    public void clearDisplayMode() {
        displayFlag = false;
    }

    // Check status of display mode - ON = True / OFF = False
    @Override
    public boolean isDisplayMode() {
        return displayFlag;
    }
}
