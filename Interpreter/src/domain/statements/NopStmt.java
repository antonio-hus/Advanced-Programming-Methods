////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.expressions.ExpException;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class NopStmt implements IStmt {

    // NOP STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "Nop Statement";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {

        // Return null
        return null;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
