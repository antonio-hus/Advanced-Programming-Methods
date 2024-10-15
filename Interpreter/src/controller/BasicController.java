////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package controller;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyIStack;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.statements.IStmt;
import domain.statements.StmtException;
import repository.Repository;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BasicController implements Controller {

    // BASIC CONTROLLER STRUCTURE
    Repository repository;
    boolean displayFlag;


    // BASIC CONTROLLER METHODS
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

    @Override
    public void allStep() throws ControllerException, MyListException, MyStackException, StmtException, ExpException, MyDictionaryException {

        // Get the current program state
        PrgState program = repository.getCrtPrg();

        while(!program.getExecutionStack().isEmpty()){
            oneStep(program);
            if(displayFlag)
                System.out.println(program);
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
