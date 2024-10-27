////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.stack.MyIStack;
import domain.expressions.ExpException;
import domain.types.*;
import domain.values.BoolValue;
import domain.values.IntValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class VarDeclStmt implements IStmt {

    // VARIABLE DECLARATION STATEMENT
    // A variable is defined by a name and type
    String variableName;
    Type variableType;


    // VARIABLE DECLARATION CONSTRUCTOR
    public VarDeclStmt(String variableName, Type variableType) {
        this.variableName = variableName;
        this.variableType = variableType;
    }


    // VARIABLE DECLARATION METHODS
    // To String Method
    @Override
    public String toString() {
        return variableType.toString() + " " + variableName;
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get the current stack and symbols table
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();

        // Check if the variable name is in the symbols table
        if(symTbl.containsKey(variableName))
            throw new StmtException("The variable was already declared");

        // Add the new variable to the symbols table
        // Use default values for their types
        symTbl.put(variableName, variableType.defaultValue());

        // Return the new state
        return state;
    }
}
