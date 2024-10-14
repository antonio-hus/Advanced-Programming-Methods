////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class CompStmt implements IStmt {

    // COMPOSED STATEMENT STRUCTURE
    // A Composed Statement is formed of two statements
    IStmt firstIStmt;
    IStmt secondIStmt;


    // COMPOSED STATEMENT METHODS
    @Override
    public String toString() {
        return "(" + this.firstIStmt.toString() + ";" + this.secondIStmt.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException {
        return null;
    }
}
