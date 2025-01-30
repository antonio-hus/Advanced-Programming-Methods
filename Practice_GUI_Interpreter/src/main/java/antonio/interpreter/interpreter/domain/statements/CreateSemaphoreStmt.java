package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.datastructures.list.MyList;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.expressions.VarExp;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.util.Pair;

public class CreateSemaphoreStmt implements IStmt{

    // CREATE SEMAPHORE STRUCTURE
    String variableName;
    Exp exp;

    // CREATE SEMAPHORE CONSTRUCTOR
    public CreateSemaphoreStmt(String var, Exp exp) { this.variableName=var; this.exp=exp; }

    // CREATE SEMAPHORE METHODS
    // To String Method
    @Override
    public String toString() {
        return "createSemaphore("+ variableName + ", " + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        // Evaluating expression
        Value result = this.exp.eval(state.getSymbolsTable(), state.getHeap());

        // Type must be integer
        if (!result.getType().equals(new IntType()))
            throw new StmtException("CREATE SEMAPHORE EXCEPTION: Expression must be of type Int");

        // Safely cast to IntValue
        IntValue resultInt = (IntValue) result;

        // Add into semaphore table
        Integer newLocation = state.getSemaphoreTable().put(new Pair<>(resultInt.getValue(), new MyList<>()));

        // Add variable into symbols table
        // Must be inside Symbols Table
        if (!state.getSymbolsTable().containsKey(variableName))
            throw new StmtException("CREATE SEMAPHORE EXCEPTION: Variable must be in SymbolsTable");

        Value var = new VarExp(variableName).eval(state.getSymbolsTable(), state.getHeap());

        // Type must be integer
        if (!var.getType().equals(new IntType()))
            throw new StmtException("CREATE SEMAPHORE EXCEPTION: Variable must be of type Int");

        state.getSymbolsTable().update(variableName, new IntValue(newLocation));

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CreateSemaphoreStmt(this.variableName, this.exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            // Check the type of the variable and of the expression
            Type typeVar = typeEnv.get(variableName);
            Type typeExp = exp.typeCheck(typeEnv);

            // The type of the variable and expression must be integer
            if(!typeVar.equals(new IntType()) || !typeExp.equals(new IntType())) {
                throw new StmtException("CREATE SEMAPHORE STATEMENT ERROR - The type of the expression / variable must be integer");
            }

            // Return the typechecking dictionary
            return typeEnv;

        } catch(MyDictionaryException | ExpException exp) {
            throw new StmtException("CREATE SEMAPHORE STATEMENT ERROR - " + exp);
        }
    }
}
