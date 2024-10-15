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
    VarExp(String variableName) { this.variableName = variableName; }


    // VARIABLE EXPRESSION METHODS
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
}
