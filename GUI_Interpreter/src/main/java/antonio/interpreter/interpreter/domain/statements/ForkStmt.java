////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.statements;
import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.ExeStack;
import antonio.interpreter.interpreter.domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ForkStmt implements IStmt {

    // FORK STATEMENT STRUCTURE
    IStmt innerStmt;

    // FORK STATEMENT CONSTRUCTOR
    public ForkStmt(IStmt innerStmt) {
        this.innerStmt = innerStmt;
    }

    // FORK STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "fork("+ this.innerStmt.toString()+")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {
        return new PrgState(new ExeStack(), state.getSymbolsTable().deepCopy(), state.getOutputList(), state.getFileTable(), state.getHeap(), this.innerStmt);
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new ForkStmt(this.innerStmt.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {

        // Type check the inner statement
        return innerStmt.typeCheck(typeEnv);
    }
}
