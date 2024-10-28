////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;
import domain.datastructures.dictionary.MyIDictionary;
import domain.statements.AssignStmt;
import domain.statements.IStmt;
import domain.types.IntType;
import domain.values.IntValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ArithExp implements Exp {

    // ARITHMETIC EXPRESSION STRUCTURE
    // Based on two expression operands & operator
    Exp e1, e2;
    // Option can be: Addition (1), Subtraction (2), Multiplication (3), Division (4)
    int option;
    String optionChar;


    // ARITHMETIC EXPRESSION CONSTRUCTOR
    public ArithExp(Exp firstExpression, Exp secondExpression, int op) {
        e1 = firstExpression;
        e2 = secondExpression;
        option = op;

        switch (op){
            case 1: optionChar = "+"; break;
            case 2: optionChar = "-"; break;
            case 3: optionChar = "*"; break;
            case 4: optionChar = "/"; break;
        }
    }

    public ArithExp(Exp firstExpression, Exp secondExpression, String opCh) {
        e1 = firstExpression;
        e2 = secondExpression;
        optionChar = opCh;
        switch (optionChar) {
            case "+": option = 1; break;
            case "-": option = 2; break;
            case "*": option = 3; break;
            case "/": option = 4; break;
        }
    }


    // ARITHMETIC EXPRESSION METHODS
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
            throw new ArithException("Both operands must be of type Integer");
        }

        // Safely cast to IntType
        IntValue i1 = (IntValue) v1;
        IntValue i2 = (IntValue) v2;

        // Get final integer values
        Integer n1 = i1.getValue();
        Integer n2 = i2.getValue();

        // Return the result of the operation
        switch (option) {

            // Common Operations
            case 1: return new IntValue(n1+n2);
            case 2: return new IntValue(n1-n2);
            case 3: return new IntValue(n1*n2);
            case 4:
                if(n2 == 0) {
                    throw new ArithException("Error - Division by Zero");
                }
                else return new IntValue(n1/n2);

            // Operation not found
            default: throw new ArithException("Operation type not specified or not correct");
        }
    }

    // Returns a copy of the type
    @Override
    public Exp deepCopy() {
        return new ArithExp(this.e1.deepCopy(), this.e2.deepCopy(), this.option);
    }
}
