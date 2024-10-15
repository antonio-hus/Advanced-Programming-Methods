////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;


import domain.datastructures.dictionary.MyIDictionary;
import domain.types.BoolType;
import domain.types.IntType;
import domain.values.BoolValue;
import domain.values.IntValue;
import domain.values.Value;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class LogicExp implements Exp {

    // LOGIC EXPRESSION STRUCTURE
    // Based on two expression operands & operator
    Exp e1, e2;
    // Option can be: AND (1), OR (2)
    int option;
    String optionChar;

    // LOGIC EXPRESSION CONSTRUCTORS
    LogicExp(Exp firstExpression, Exp secondExpression, int op) {
        e1 = firstExpression;
        e2 = secondExpression;
        option = op;

        switch (op){
            case 1: optionChar="and"; break;
            case 2: optionChar="or"; break;
        }
    }

    // LOGIC EXPRESSION METHODS
    // String Formatting
    @Override
    public String toString() {
        return e1.toString() + " " + optionChar + " " + e2.toString();
    }

    // Evaluates the given expression given the values in symbolsTable
    @Override
    public Value eval(MyIDictionary<String, Value> symbolsTable) throws ExpException {

        // Evaluate the expressions for the operands
        Value v1, v2;
        v1 = e1.eval(symbolsTable);
        v2 = e2.eval(symbolsTable);

        // Check type of the operands
        // Must be booleans
        if(!v1.getType().equals(new BoolType()) || !v2.getType().equals(new BoolType())) {
            throw new LogicException("Both operands must be of type Boolean");
        }

        // Safely cast to BoolType
        BoolValue b1 = (BoolValue) v1;
        BoolValue b2 = (BoolValue) v2;

        // Get final integer values
        Boolean n1 = b1.getValue();
        Boolean n2 = b2.getValue();

        // Return the result of the operation
        switch (option) {

            // Common Operations
            case 1: return new BoolValue(n1 && n2);
            case 2: return new BoolValue(n1 || n2);

            // Operation not found
            default: throw new LogicException("Operation type not specified or not correct");
        }
    }
}
