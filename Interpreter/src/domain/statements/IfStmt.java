////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyIDictionary;
import domain.datastructures.stack.MyIStack;
import domain.expressions.Exp;
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
    IfStmt(Exp e, IStmt t, IStmt el) {expression=e; thenStatement=t;elseStatement=el;}


    // IF STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException {

        // Get the current stack and symbols table
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        return state;
    }

}
