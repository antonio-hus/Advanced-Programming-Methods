////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.expressions.ExpException;
import domain.types.Type;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IStmt {

    // Executes the statement of the program defined by Program State
    PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException;

    // Returns a copy of the statement
    IStmt deepCopy();

    // Typechecking mechanism
    // Ensure statement can be run
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException;
}
