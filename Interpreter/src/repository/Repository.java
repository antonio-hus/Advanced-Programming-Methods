////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package repository;
import domain.PrgState;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyListException;

import java.io.IOException;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Repository {

    // REPOSITORY METHODS
    // Add new Program State
    void addPrgState(PrgState newProgramState);

    // Add new Program State at a given index
    void addPrgState(PrgState newProgramState, int index) throws MyListException;

    // Remove a Program State
    void removePrgState(int index) throws MyListException;

    // Gets the currently running program
    PrgState getCrtPrg(int index) throws MyListException;

    // Gets all programs
    MyIList<PrgState> getPrgStates();

    // Log File Operations
    // Clear log file
    void clearLogFile() throws RepositoryException;

    // Logs Repository State to file
    void logPrgStateExec() throws RepositoryException;
}
