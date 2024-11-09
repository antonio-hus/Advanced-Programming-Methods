////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.state.IHeap;
import domain.state.ISymTable;
import domain.types.RefType;
import domain.values.RefValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class HeapReadExp implements Exp {

    // HEAP READ EXPRESSION STRUCTURE
    Exp expression;

    // HEAP READ EXPRESSION CONSTRUCTOR
    public HeapReadExp(Exp exp) { this.expression = exp; }

    // HEAP READ EXPRESSION METHODS
    // To String Method
    @Override
    public String toString() {
        return "rH(" + this.expression.toString() + ")";
    }
    @Override
    public Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException {

        // Evaluate the expression
        Value expVal = expression.eval(symbolsTable, heap);
        if(!(expVal.getType() instanceof RefType))
            throw new ExpException("HEAP READ ERROR - The expression is not of reference type");

        // Safely cast to RefValue
        RefValue refVal = (RefValue) expVal;

        // Get the address in the heap of the value
        int address = refVal.getAddress();

        // Check that address is part of the heap
        if(!heap.containsKey(address))
            throw new ExpException("HEAP READ ERROR - The specified address is not allocated in the heap");

        // Return the value associated to the address
        try {
            return heap.get(address);
        }catch (MyDictionaryException exp) {
            throw new ExpException("HEAP READ ERROR - The specified address is not allocated in the heap");
        }
    }

    // Returns a copy of the expression
    @Override
    public Exp deepCopy() {
        return new HeapReadExp(this.expression.deepCopy());
    }
}
