////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.expressions.Exp;
import domain.expressions.ExpException;
import domain.state.IHeap;
import domain.state.ISymTable;
import domain.types.RefType;
import domain.values.RefValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class HeapAllocStmt implements IStmt {

    // HEAP ALLOCATION STATEMENT STRUCTURE
    String variableName;
    Exp expression;


    // HEAP ALLOCATION STATEMENT CONSTRUCTOR
    public HeapAllocStmt(String variableName, Exp expression) { this.variableName=variableName; this.expression=expression; }


    // HEAP ALLOCATION STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "new("+ variableName + ", " + this.expression.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get the current program state
        IHeap heap = state.getHeap();
        ISymTable symTbl = state.getSymbolsTable();

        // Check if the variable name is in the symbols table
        if(!symTbl.containsKey(variableName))
            throw new StmtException("HEAP ALLOCATION ERROR - The variable was not declared");

        // Check type of the value
        // Must be RefType
        Value val = symTbl.get(variableName);
        if(!(val.getType() instanceof RefType))
            throw new StmtException("HEAP ALLOCATION ERROR - Variable is not of reference type");

        // Safely cast to RefValue
        RefValue refVal = (RefValue) val;

        // Evaluate new expression
        Value expVal = expression.eval(symTbl, heap);
        if(!expVal.getType().equals(refVal.getLocationType()))
            throw new StmtException("HEAP ALLOCATION ERROR - The expression does not match variable inner type");

        // Appending to the heap
        int address = heap.put(expVal);

        // Updating the symbols table
        symTbl.update(variableName, new RefValue(address, refVal.getLocationType()));
        return state;
    }

    // Returns a copy of the statement
    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(variableName, expression.deepCopy());
    }
}
