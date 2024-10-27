////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;


import domain.datastructures.dictionary.MyIDictionary;
import domain.types.IntType;
import domain.values.BoolValue;
import domain.values.IntValue;
import domain.values.Value;

import java.util.Objects;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class RelationExp implements Exp {

    // RELATIONAL EXPRESSION STRUCTURE
    // Based on two expression operands & operator
    Exp e1, e2;
    // Option can be: Less (1), Less or Equal (2), Equal (3), Not Equal (4), Greater (5), Greater or Equal (6)
    int option;
    String optionChar;


    // ARITHMETIC EXPRESSION CONSTRUCTOR
    public RelationExp(Exp firstExpression, Exp secondExpression, int op) {
        e1 = firstExpression;
        e2 = secondExpression;
        option = op;

        switch (op){
            case 1: optionChar = "<"; break;
            case 2: optionChar = "<="; break;
            case 3: optionChar = "=="; break;
            case 4: optionChar = "!="; break;
            case 5: optionChar = ">"; break;
            case 6: optionChar = ">="; break;
        }
    }

    public RelationExp(Exp firstExpression, Exp secondExpression, String opCh) {
        e1 = firstExpression;
        e2 = secondExpression;
        optionChar = opCh;
        switch (optionChar) {
            case "<": option = 1; break;
            case "<=": option = 2; break;
            case "==": option = 3; break;
            case "!=": option = 4; break;
            case ">": option = 5; break;
            case ">=": option = 6; break;
        }
    }


    // RELATIONAL EXPRESSION METHODS
    // String Formatting
    @Override
    public String toString() {
        return e1.toString() + optionChar + e2.toString();
    }

    // Evaluates the given expression given the values in symbolsTable
    @Override
    public Value eval(MyIDictionary<String, Value> symbolsTable) throws ExpException {

        // Evaluate the expressions for the operands
        Value v1, v2;
        v1 = e1.eval(symbolsTable);
        v2 = e2.eval(symbolsTable);

        // Check type of the operands
        // Must be integers
        if(!v1.getType().equals(new IntType()) || !v2.getType().equals(new IntType())) {
            throw new RelationException("Both operands must be of type Integer");
        }

        // Safely cast to IntType
        IntValue i1 = (IntValue) v1;
        IntValue i2 = (IntValue) v2;

        // Get final integer values
        Integer n1 = i1.getValue();
        Integer n2 = i2.getValue();

        // Return the result of the operation
        return switch (option) {

            // Common Operations
            case 1 -> new BoolValue(n1 < n2);
            case 2 -> new BoolValue(n1 <= n2);
            case 3 -> new BoolValue(Objects.equals(n1, n2));
            case 4 -> new BoolValue(!Objects.equals(n1, n2));
            case 5 -> new BoolValue(n1 > n2);
            case 6 -> new BoolValue(n1 >= n2);

            // Operation not found
            default -> throw new RelationException("Operation type not specified or not correct");
        };
    }
}
