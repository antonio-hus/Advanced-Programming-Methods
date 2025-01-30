////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.*;


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
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        // Get the current stack and symbols table
        ISymTable symTbl = state.getSymbolsTable();

        // Check if the variable name is in the symbols table
        if(symTbl.containsKey(variableName))
            throw new StmtException("The variable was already declared");

        // Add the new variable to the symbols table
        // Use default values for their types
        symTbl.put(variableName, variableType.defaultValue());

        // Return null
        return null;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(this.variableName, this.variableType.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            typeEnv.put(variableName, variableType);
            return typeEnv;
        } catch (MyDictionaryException exp) {
            throw new StmtException("VARIABLE DECLARATION STATEMENT ERROR - " + exp);
        }
    }
}
