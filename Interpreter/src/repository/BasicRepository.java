////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package repository;
import domain.PrgState;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyList;
import domain.datastructures.list.MyListException;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BasicRepository implements Repository {

    // BASIC REPOSITORY STRUCTURE
    // The basic repository is based on a list of PrgStates
    MyIList<PrgState> programStates;


    // BASIC REPOSITORY CONSTRUCTOR
    public BasicRepository() {
        programStates = new MyList<>();
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
}
