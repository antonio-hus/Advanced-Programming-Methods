////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package view.commands;
import controller.BasicController;
import controller.Controller;
import controller.ControllerException;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionary;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.list.MyListException;
import domain.datastructures.stack.MyStackException;
import domain.expressions.ExpException;
import domain.state.*;
import domain.statements.IStmt;
import domain.statements.StmtException;
import domain.types.Type;
import repository.RepositoryException;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class RunExample extends Command {

    // COMMAND CLASS STRUCTURE
    // A run example command is based on a controller to execute the example
    private IStmt initialProgram;
    private final String logFilePath;

    // COMMAND CLASS CONSTRUCTOR
    public RunExample(String key, String desc, String logFilePath, IStmt initialProgram){
        super(key, desc);
        this.initialProgram=initialProgram;
        this.logFilePath = logFilePath;
    }

    // COMMAND CLASS METHODS
    // Executes the command object
    // Executes all steps of the current controller
    @Override
    public void execute() {
        try{

            // Type Checking
            this.initialProgram.typeCheck(new MyDictionary<>());

            // Creating program
            PrgState prg = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), this.initialProgram);
            Controller controller = new BasicController(1, logFilePath, prg);

            // Execute the program
            controller.allStep();
        }
        catch (ControllerException e)  {
            System.out.println("There was an error running the current program: " + e);
        }
        catch (StmtException e) {
            System.out.println("There was an error typechecking the current program: " + e);
        }
    }
}
