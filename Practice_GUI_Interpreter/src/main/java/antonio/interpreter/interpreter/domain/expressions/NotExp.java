////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.expressions;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.state.IHeap;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.BoolValue;
import antonio.interpreter.interpreter.domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class NotExp implements Exp {

    // NOT EXPRESSION STRUCTURE
    // Based on expression operand
    Exp exp;

    // NOT EXPRESSION CONSTRUCTOR
    public NotExp(Exp exp) {
        this.exp = exp;
    }

    // NOT EXPRESSION METHODS
    // String Formatting
    @Override
    public String toString() {
        return "!(" + exp.toString() + ")";
    }

    // Evaluates the given expression given the values in symbolsTable
    @Override
    public Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException {
        // Evaluate the expressions for the operands
        Value v = exp.eval(symbolsTable, heap);

        // Check type of the operands
        // Must be booleans
        if(!v.getType().equals(new BoolType())) {
            throw new LogicException("Operand must be of type Boolean");
        }

        // Safely cast to BoolType
        BoolValue b = (BoolValue) v;

        // Get final integer values
        Boolean n = b.getValue();

        return new BoolValue(!n);
    }

    // Returns a copy of the type
    @Override
    public Exp deepCopy() {
        return new NotExp(exp.deepCopy());
    }

    // Typechecking mechanism
    // Returns the return type of the expression
    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpException {
        // Get the type of the two composing expressions
        Type type = exp.typeCheck(typeEnv);

        // Both operands must be of type Boolean
        if((!type.equals(new BoolType()))) {
            throw new ExpException("NOT EXPRESSION ERROR - Operand must be of boolean type");
        }

        // Logic expressions return a Boolean type
        return new BoolType();
    }
}
