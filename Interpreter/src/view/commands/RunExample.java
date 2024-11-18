////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package view.commands;
import controller.Controller;
import controller.ControllerException;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.statements.StmtException;
import repository.RepositoryException;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class RunExample extends Command {

    // COMMAND CLASS STRUCTURE
    // A run example command is based on a controller to execute the example
    private final Controller controller;

    // COMMAND CLASS CONSTRUCTOR
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.controller=ctr;
    }

    // COMMAND CLASS METHODS
    // Executes the command object
    // Executes all steps of the current controller
    @Override
    public void execute() {
        try{
            controller.allStep();
        }
        catch (ControllerException e)  {
            System.out.println("There was an error running the current program: " + e);
        }
    }
}
