////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.expressions.ExpException;
import domain.state.ExeStack;


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
}
