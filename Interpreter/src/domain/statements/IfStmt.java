////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.stack.MyIStack;
import domain.expressions.Exp;
import domain.expressions.ExpException;
import domain.state.IExeStack;
import domain.state.ISymTable;
import domain.types.BoolType;
import domain.types.*;
import domain.values.BoolValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class IfStmt implements IStmt {

    // IF STATEMENT STRUCTURE
    // An If Statement is formed of two possible statements and an expression
    Exp expression;
    IStmt thenStatement;
    IStmt elseStatement;


    // IF STATEMENT CONSTRUCTOR
    public IfStmt(Exp e, IStmt t, IStmt el) {expression=e; thenStatement=t;elseStatement=el;}


    // IF STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException {

        // Get the current stack and symbols table
        IExeStack stk = state.getExecutionStack();
        ISymTable symTbl = state.getSymbolsTable();

        // Evaluate expression to decide what statement to follow
        Value result = expression.eval(symTbl, state.getHeap());
        Type resultType = result.getType();

        // Expression must be of type bool
        if(!resultType.equals(new BoolType())) {
            throw new StmtException("Expression is not of boolean type");
        }

        // Safely cast to boolean type
        BoolValue boolValue = (BoolValue) result;

        // Add the statement based on the expression evaluation result
        if(boolValue.getValue()) {
            stk.push(thenStatement);
        } else {
            stk.push(elseStatement);
        }

        // Return the new state
        return state;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

}
