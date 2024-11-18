////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.list.MyIList;
import domain.state.IOutList;
import domain.state.ISymTable;
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
        IOutList outputList = state.getOutputList();
        ISymTable symTbl = state.getSymbolsTable();

        // Evaluate the expression and add result to output list
        Value result = expression.eval(symTbl, state.getHeap());
        outputList.add(result);

        // Return null
        return null;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.expression.deepCopy());
    }
}
