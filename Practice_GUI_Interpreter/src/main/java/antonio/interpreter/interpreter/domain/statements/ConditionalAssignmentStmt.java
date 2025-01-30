package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.Type;

public class ConditionalAssignmentStmt implements IStmt {

    // CONDITIONAL ASSIGNMENT STRUCTURE
    String variableName;
    Exp exp1, exp2, exp3;

    // CONDITIONAL ASSIGNMENT CONSTRUCTOR
    public ConditionalAssignmentStmt(String variableName, Exp exp1, Exp exp2, Exp exp3) {
        this.variableName = variableName;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }


    // CONDITIONAL ASSIGNMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return variableName + "= (" + exp1.toString() + ") ? " + exp2.toString() + " : " + exp3.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {
        IStmt equivalentStmt = new IfStmt(exp1, new AssignStmt(variableName, exp2), new AssignStmt(variableName, exp3));
        state.getExecutionStack().push(equivalentStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ConditionalAssignmentStmt(variableName, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            Type exp1Type = exp1.typeCheck(typeEnv);
            Type exp2Type = exp2.typeCheck(typeEnv);
            Type exp3Type = exp3.typeCheck(typeEnv);
            Type varType = typeEnv.get(variableName);

            if(!exp1Type.equals(new BoolType()))
                throw new StmtException("CONDITIONAL ASSIGNMENT EXCEPTION - Condition must be boolean");

            if(!varType.equals(exp2Type) || !varType.equals(exp3Type))
                throw new StmtException("CONDITIONAL ASSIGNMENT EXCEPTION - Assigned expressions must have the same type as the variable");

            return typeEnv;
        } catch (Exception exp) {
            throw new StmtException("CONDITIONAL ASSIGNMENT EXCEPTION - " + exp);
        }
    }
}
