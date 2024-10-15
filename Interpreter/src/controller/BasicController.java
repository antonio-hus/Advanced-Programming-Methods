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
import domain.statements.IStmt;
import domain.statements.StmtException;
import repository.BasicRepository;
import repository.Repository;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BasicController implements Controller {

    // BASIC CONTROLLER STRUCTURE
    Repository repository;
    boolean displayFlag;


    // BASIC CONTROLLER CONSTRUCTOR
    public BasicController(int flagConfiguration) {

        // Set up repository
        repository = new BasicRepository();

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
    public PrgState oneStep(PrgState state) throws ControllerException, MyStackException, StmtException, ExpException, MyDictionaryException {

        // Get the current program state
        MyIStack<IStmt> stack = state.getExecutionStack();

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

        // Return new state
        return newState;
    }
    // Execute one step - one statement
    public void oneStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException {
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
    }
    // Execute entire program - all statements
    @Override
    public void allStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException {

        // Get the current program state - the one last added into the list
        PrgState program = repository.getCrtPrg(repository.getPrgStates().size()-1);

        while(!program.getExecutionStack().isEmpty()){
            oneStep(program);
        }
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
