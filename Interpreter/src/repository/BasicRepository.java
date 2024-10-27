////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package repository;
import domain.PrgState;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyList;
import domain.datastructures.list.MyListException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BasicRepository implements Repository {

    // BASIC REPOSITORY STRUCTURE
    // The basic repository is based on a list of PrgStates
    MyIList<PrgState> programStates;
    String logFilePath;


    // BASIC REPOSITORY CONSTRUCTOR
    public BasicRepository(String logFilePath) {
        this.programStates = new MyList<>();
        this.logFilePath = logFilePath;
    }


    // BASIC REPOSITORY METHODS
    // Add new Program State
    @Override
    public void addPrgState(PrgState newProgramState) {
        programStates.add(newProgramState);
    }

    // Add new Program State at a given index
    @Override
    public void addPrgState(PrgState newProgramState, int index) throws MyListException {
        programStates.add(index, newProgramState);
    }

    // Remove a Program State
    @Override
    public void removePrgState(int index) throws MyListException {
        programStates.remove(index);
    }

    // Gets the currently running program
    @Override
    public PrgState getCrtPrg(int index) throws MyListException {

        // Gets the last program in the list
        return programStates.get(index);
    }

    // Gets all programs
    @Override
    public MyIList<PrgState> getPrgStates() {
        return programStates;
    }

    // Logs Repository state to a file
    public void logPrgStateExec() throws RepositoryException {

        try {

            // Create output stream
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));

            // Get the currently running program
            PrgState program = this.getCrtPrg(this.getPrgStates().size() - 1);

            // Write the contents of the current program
            logFile.write(program.toString());

            // Close output stream
            logFile.close();

        } catch (IOException e) {
            throw new RepositoryException("There was an error logging data about the currently running program: " + e);
        } catch (MyListException e) {
            throw new RepositoryException("There was an error getting data about the currently running program: " + e);
        }

    }
}
