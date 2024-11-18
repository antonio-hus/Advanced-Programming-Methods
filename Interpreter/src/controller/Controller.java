////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package controller;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.statements.StmtException;
import domain.values.Value;
import repository.RepositoryException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Controller {

    // CONTROLLER METHODS
    // Add new Program State
    void addPrgState(PrgState newProgramState);
    // Add new Program State at a given index
    void addPrgState(PrgState newProgramState, int index) throws MyListException;
    // Remove a Program State
    void removePrgState(int index) throws MyListException;
    // Gets the currently running program on the given index
    PrgState getCrtPrg(int index) throws MyListException;
    // Gets all programs
    MyIList<PrgState> getPrgStates();

    // Program State Execution Methods
    // Execute one step - one statement - specific program state
    PrgState oneStep(PrgState state) throws ControllerException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException;
    // Execute one step - one statement
    void oneStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException;
    // Execute entire program - all statements
    void allStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException, RepositoryException;

    // Garbage Collector Related Methods
    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap);
    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap);
    public List<Integer> getReachableAddresses(List<Integer> symTableAddr, Map<Integer, Value> heap);
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues);

    // Flags Related Methods
    // Display Mode
    // Set display mode ON
    void setDisplayMode();
    // Set display mode OFF
    void clearDisplayMode();
    // Check status of display mode - ON = True / OFF = False
    boolean isDisplayMode();
}
