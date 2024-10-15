////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package controller;


import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.statements.StmtException;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Controller {

    // CONTROLLER METHODS
    // Program State Execution Methods
    // Execute one step - one statement
    PrgState oneStep(PrgState state) throws ControllerException, MyStackException, StmtException, ExpException, MyDictionaryException;
    // Execute entire program - all statements
    void allStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException;

    // Flags Related Methods
    // Display Mode
    // Set display mode ON
    void setDisplayMode();
    // Set display mode OFF
    void clearDisplayMode();
    // Check status of display mode - ON = True / OFF = False
    boolean isDisplayMode();
}
