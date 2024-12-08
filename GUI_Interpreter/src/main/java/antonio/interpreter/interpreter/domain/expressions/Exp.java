////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.expressions;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.state.IHeap;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.Value;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Exp {

    // Evaluates the given expression given the values in symbolsTable
    Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException;

    // Returns a copy of the expression
    Exp deepCopy();

    // Typechecking mechanism
    // Returns the return type of the expression
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpException;
}
