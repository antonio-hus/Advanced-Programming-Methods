////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IStmt {

    // Executes the statement of the program defined by Program State
    PrgState execute(PrgState state) throws StmtException;
}
