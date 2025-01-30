package antonio.interpreter.interpreter.domain.expressions;

import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.state.IHeap;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.statements.StmtException;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.Value;

public class MULExp implements Exp {

    // MUL EXPRESSION STRUCTURE
    Exp exp1, exp2;

    // MUL EXPRESSION CONSTRUCTOR
    public MULExp(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    // MUL EXPRESSION METHODS
    @Override
    public String toString() {
        return "MUL(" + exp1.toString() + ", " + exp2.toString() + ")";
    }

    @Override
    public Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException {
        return new ArithExp(new ArithExp(exp1, exp2, "*"), new ArithExp(exp1, exp2, "+"), "-").eval(symbolsTable, heap);
    }

    @Override
    public Exp deepCopy() {
        return new MULExp(exp1, exp2);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpException {
        try {
            Type exp1Type = exp1.typeCheck(typeEnv);
            Type exp2Type = exp2.typeCheck(typeEnv);

            if(!exp1Type.equals(new IntType()) || !exp2Type.equals(new IntType()))
                throw new ExpException("MUL EXPRESSION ERROR - Expressions must have type Integer");

            return new IntType();

        } catch (Exception exc) {
            throw new ExpException("MUL EXPRESSION ERROR - " + exc);
        }
    }
}
