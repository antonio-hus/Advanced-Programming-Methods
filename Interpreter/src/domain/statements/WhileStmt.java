////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;
import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.expressions.*;
import domain.state.IExeStack;
import domain.state.ISymTable;
import domain.types.BoolType;
import domain.types.Type;
import domain.values.BoolValue;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class WhileStmt implements IStmt {

    // WHILE STATEMENT STRUCTURE
    Exp expression;
    IStmt statement;


    // WHILE STATEMENT CONSTRUCTOR
    public WhileStmt(Exp expression, IStmt statement) { this.expression = expression; this.statement = statement; }


    // WHILE STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "while(" + this.expression.toString() + ") " + statement.toString();
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get the program state
        IExeStack exeStack = state.getExecutionStack();
        ISymTable symTable = state.getSymbolsTable();

        // Evaluate the while expression
        Value val = this.expression.eval(symTable, state.getHeap());

        // Check type of the expression's value
        // Must be BoolType
        if(!(val.getType().equals(new BoolType())))
            throw new StmtException("WHILE STATEMENT ERROR - Expression must evaluate to boolean");

        // Safely cast to BoolValue
        BoolValue boolValue = (BoolValue) val;

        // If the value is true
        // Push While Statement to execution stack
        // Push Statement to execution stack
        if(boolValue.getValue()){
            exeStack.push(new WhileStmt(this.expression, this.statement));
            exeStack.push(this.statement);
        }

        // Return null
        return null;
    }

    // Returns a copy of the statement
    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.expression.deepCopy(), this.statement.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            Type typeExp = expression.typeCheck(typeEnv);
            if(!typeExp.equals(new BoolType())){
                throw new StmtException("WHILE STATEMENT ERROR - Expression must be of Boolean Type");
            }

            statement.typeCheck(typeEnv);

            return typeEnv;
        } catch(ExpException exp) {
            throw new StmtException("WHILE STATEMENT ERROR - " + exp);
        }
    }
}
