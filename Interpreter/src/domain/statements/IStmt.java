////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.expressions.ExpException;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IStmt {

    // Executes the statement of the program defined by Program State
    PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException;

    // Returns a copy of the statement
    IStmt deepCopy();
}
