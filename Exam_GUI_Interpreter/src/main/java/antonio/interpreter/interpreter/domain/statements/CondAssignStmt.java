package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.Type;

public class CondAssignStmt implements IStmt {

    // CONDITIONAL ASSIGNMENT STATEMENT STRUCTURE
    String variableName;
    Exp exp1, exp2, exp3;

    // CONDITIONAL ASSIGNMENT STATEMENT CONSTRUCTOR
    public CondAssignStmt(String variableName, Exp exp1, Exp exp2, Exp exp3) {
        this.variableName = variableName;
        this.exp1 = exp1;
        this.exp2= exp2;
        this.exp3 = exp3;
    }

    // CONDITIONAL ASSIGNMENT STATEMENT METHODS
    @Override
    public String toString() {
        return variableName+"="+exp1.toString()+"?"+exp2.toString()+":"+exp3.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        IStmt equivalentStmt = new IfStmt(exp1, new AssignStmt(variableName, exp2), new AssignStmt(variableName, exp3));
        state.getExecutionStack().push(equivalentStmt);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CondAssignStmt(variableName, exp1, exp2, exp3);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try{
            Type type1 = exp1.typeCheck(typeEnv);
            if(!type1.equals(new BoolType())) {
                throw new StmtException("COND ASSIGN STATEMENT ERROR - Condition must be of Boolean type");
            }

            Type typeVar = typeEnv.get(variableName);
            Type type2 = exp2.typeCheck(typeEnv);
            Type type3 = exp3.typeCheck(typeEnv);
            if(!typeVar.equals(type2) || !typeVar.equals(type3)) {
                throw new StmtException("COND ASSIGN STATEMENT ERROR - Assigned expressions must have the same type as the variable");
            }
            return typeEnv;

        } catch(ExpException | MyDictionaryException exp) {
            throw new StmtException("COND ASSIGN STATEMENT ERROR - " + exp);
        }
    }
}
