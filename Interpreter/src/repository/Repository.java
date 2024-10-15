////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package repository;
import domain.PrgState;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyListException;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Repository {

    // REPOSITORY METHODS
    // Add new Program State
    void addPrgState(PrgState newProgramState);

    // Remove a Program State
    void removePrgState(int index);

    // Gets the currently running program
    PrgState getCrtPrg() throws MyListException;

    // Gets all programs
    MyIList<PrgState> getPrgStates();
}
