////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;
import domain.datastructures.dictionary.MyIDictionary;
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
    public Value eval(MyIDictionary<String, Value> symbolsTable) throws ExpException {
        return value;
    }
}
