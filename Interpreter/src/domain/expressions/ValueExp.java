////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;
import domain.datastructures.dictionary.MyIDictionary;
import domain.state.IHeap;
import domain.state.ISymTable;
import domain.types.IntType;
import domain.types.Type;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ValueExp implements Exp {

    // VALUE EXPRESSION STRUCTURE
    Value value;


    // VALUE EXPRESSION CONSTRUCTORS
    public ValueExp(Value val) { value = val; }


    // VALUE EXPRESSION METHODS
    // String Formatting
    @Override
    public String toString() {
        return value.toString();
    }

    // Evaluates the given expression as a constant value
    @Override
    public Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException {
        return value;
    }

    // Returns a copy of the type
    @Override
    public Exp deepCopy() {
        return new ValueExp(this.value.deepCopy());
    }

    // Typechecking mechanism
    // Returns the return type of the expression
    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpException {

        // Value expressions return the type of the value
        return value.getType();
    }
}
