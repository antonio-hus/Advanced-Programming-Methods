package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.*;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.BoolValue;

public class RepeatUntilStmt implements IStmt {

    // REPEAT UNTIL STATEMENT STRUCTURE
    IStmt stmt;
    Exp exp;

    // REPEAT UNTIL STATEMENT CONSTRUCTOR
    public RepeatUntilStmt(IStmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    // REPEAT UNTIL STATEMENT METHODS
    @Override
    public String toString() {
        return "repeat (" + stmt.toString() + ") until (" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Equivalent statement
        IStmt equivalentStmt = new CompStmt(stmt, new WhileStmt(new NotExp(exp), stmt));
        state.getExecutionStack().push(equivalentStmt);

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStmt(stmt.deepCopy(), exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            // Typecheck Statement
            this.stmt.typeCheck(typeEnv);

            // Typecheck Expression
            // Must be of type boolean
            Type expType = this.exp.typeCheck(typeEnv);
            if(!expType.equals(new BoolType()))
                throw new StmtException("REPEAT UNTIL STATEMENT ERROR - Condition must be of BoolType");

            return typeEnv;

        }catch (Exception exc) {
            throw new StmtException("REPEAT UNTIL STATEMENT ERROR - " + exc);
        }
    }
}
