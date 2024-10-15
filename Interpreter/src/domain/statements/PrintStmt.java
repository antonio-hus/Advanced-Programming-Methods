////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.list.MyIList;
import domain.values.*;
import domain.expressions.*;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class PrintStmt implements IStmt {

    // PRINT STATEMENT STRUCTURE
    // A Print Statement is formed of an expression to print
    Exp expression;

    public PrintStmt(Exp e) {
        expression = e;
    }

    // PRINT STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {

        // Get the current program state
        MyIList<Value> outputList = state.getOutputList();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();

        // Evaluate the expression and add result to output list
        Value result = expression.eval(symTbl);
        outputList.add(result);

        // Return the new state
        return state;
    }

}
