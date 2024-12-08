////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.controller;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.values.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Controller {

    // CONTROLLER METHODS
    // Management Methods
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);

    // Execution Methods
    // Execute one step for all program states
    void oneStepForAllPrg(List<PrgState> prgStateList) throws ControllerException;
    // Execute entire program - all statements
    void allStep() throws ControllerException;

    // Garbage Collector Related Methods
    void conservativeGarbageCollector(List<PrgState> prgList);
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
