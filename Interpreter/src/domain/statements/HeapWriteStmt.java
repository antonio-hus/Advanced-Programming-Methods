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

public class HeapWriteStmt implements IStmt {

    // HEAP WRITE STATEMENT STRUCTURE
    String variableName;
    Exp expression;

    // HEAP WRITE STATEMENT CONSTRUCTOR
    public HeapWriteStmt(String variableName, Exp expression) { this.variableName = variableName; this.expression = expression; }

    // HEAP WRITE STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "wH(" + this.expression.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get the current program state
        IHeap heap = state.getHeap();
        ISymTable symTbl = state.getSymbolsTable();

        // Check if the variable name is in the symbols table
        if(!symTbl.containsKey(variableName))
            throw new StmtException("HEAP WRITE ERROR - The variable was not declared");

        // Check type of the value
        // Must be RefType
        Value val = symTbl.get(variableName);
        if(!(val.getType() instanceof RefType))
            throw new StmtException("HEAP WRITE ERROR - Variable is not of reference type");

        // Safely cast to RefValue
        RefValue refVal = (RefValue) val;

        // Check if the heap contains the specified address
        int address = refVal.getAddress();
        if(!heap.containsKey(address))
            throw new StmtException("HEAP WRITE ERROR - Invalid Heap Address");


        // Evaluate new expression
        Value expVal = expression.eval(symTbl, heap);
        if(!expVal.getType().equals(refVal.getLocationType()))
            throw new StmtException("HEAP WRITE ERROR - The expression does not match variable inner type");

        // Updating the heap
        heap.update(address, expVal);
        return state;
    }

    // Returns a copy of the statement
    @Override
    public IStmt deepCopy() {
        return new HeapWriteStmt(variableName, expression.deepCopy());
    }
}
