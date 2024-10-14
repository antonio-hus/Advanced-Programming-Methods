////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.stack.MyIStack;
import domain.expressions.Exp;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class PrintStmt implements IStmt {

    // PRINT STATEMENT STRUCTURE
    // A Print Statement is formed of an expression to print
    Exp expression;

    // PRINT STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return state;
    }

}
