////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.stack.MyIStack;
import domain.expressions.Exp;
import domain.types.Type;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class AssignStmt {

    // ASSIGNMENT STATEMENT STRUCTURE
    // An Assignment Statement is formed of an expression to print
    String id;
    Exp expression;

    // ASSIGNMENT STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return id +"=" + expression.toString();
    }

    // Executes the statement of the program defined by Program State
    PrgState execute(PrgState state) throws StmtException, MyDictionaryException {

        // Get the current stack and symbols table
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        // Check if the variable name is in the symbols table
        if(!symTbl.containsKey(id))
            throw new StmtException("the used variable" +id + " was not declared before");

        // Match the type of the new value to that of the old value for type compatibility
        Value val = expression.eval(symTbl);
        Type typId= (symTbl.get(id)).getType();
        if(!val.getType().equals(typId))
            throw new StmtException("declared type of variable"+id+" and type of the assigned expression do not match");

        // Update the variable in the symbolic table
        symTbl.update(id, val);

        // Return the new state
        return state;
    }
}
