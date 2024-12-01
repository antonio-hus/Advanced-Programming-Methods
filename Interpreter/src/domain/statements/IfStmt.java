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

        // Return null
        return null;
    }

    // Returns a copy of the statement
    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try{
            Type type = expression.typeCheck(typeEnv);
            if(!type.equals(new BoolType())) {
                throw new StmtException("IF STATEMENT ERROR - Condition must be of Boolean type");
            }

            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;

        } catch(ExpException exp) {
            throw new StmtException("IF STATEMENT ERROR - " + exp);
        }
    }
}
