////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class VarExp implements Exp {

    // VARIABLE EXPRESSION STRUCTURE
    // Based on a variable name - String
    String variableName;


    // VARIABLE EXPRESSION CONSTRUCTORS
    public VarExp(String variableName) { this.variableName = variableName; }


    // VARIABLE EXPRESSION METHODS
    // String Formatting
    @Override
    public String toString() {
        return variableName;
    }

    // Evaluates the variable given the values in symbolsTable
    @Override
    public Value eval(MyIDictionary<String, Value> symbolsTable) throws ExpException {

        // Gets the value associated to the symbol in the table
        try {
            return symbolsTable.get(variableName);
        } catch (MyDictionaryException exception) {
            throw new ExpException(exception.toString());
        }
    }

    // Returns a copy of the type
    @Override
    public Exp deepCopy() {
        return new VarExp(this.variableName);
    }
}
