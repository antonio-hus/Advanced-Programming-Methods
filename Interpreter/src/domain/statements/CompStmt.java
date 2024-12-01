////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.stack.*;
import domain.state.IExeStack;
import domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class CompStmt implements IStmt {

    // COMPOSED STATEMENT STRUCTURE
    // A Composed Statement is formed of two statements
    IStmt firstIStmt;
    IStmt secondIStmt;


    // COMPOSED STATEMENT CONSTRUCTORS
    public CompStmt(IStmt st1, IStmt st2) {
        firstIStmt = st1;
        secondIStmt = st2;
    }


    // COMPOSED STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "(" + firstIStmt.toString() + ";" + secondIStmt.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        IExeStack stack = state.getExecutionStack();
        stack.push(secondIStmt);
        stack.push(firstIStmt);

        // Return null
        return null;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.firstIStmt.deepCopy(), this.secondIStmt.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {

        // Type Check bot statements
        return secondIStmt.typeCheck(firstIStmt.typeCheck(typeEnv));
    }
}
