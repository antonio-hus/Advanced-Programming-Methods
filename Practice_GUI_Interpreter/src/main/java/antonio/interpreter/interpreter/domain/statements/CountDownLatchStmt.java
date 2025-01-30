package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.ILatchTable;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.StringValue;

public class CountDownLatchStmt implements IStmt {

    // COUNT DOWN LATCH STATEMENT STRUCTURE
    String variableName;

    // COUNT DOWN LATCH STATEMENT CONSTRUCTOR
    public CountDownLatchStmt (String variableName) { this.variableName = variableName; }

    // COUNT DOWN LATCH STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "countDownLatch("+ variableName + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        // Get state information
        ISymTable symTable = state.getSymbolsTable();
        ILatchTable latchTable = state.getLatchTable();

        // Variable must be in symTable and of type int
        if(!symTable.containsKey(variableName))
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Variable not inside Symbols Table");

        if(!symTable.get(variableName).getType().equals(new IntType()))
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Variable not of type Integer");

        IntValue index = (IntValue) symTable.get(variableName);
        Integer foundIndex = index.getValue();

        if(!latchTable.containsKey(foundIndex))
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Index not inside LatchTable");

        Integer value = latchTable.get(foundIndex);
        if (value > 0) {
            latchTable.update(foundIndex, value - 1);
        }
        state.getOutputList().add(new StringValue(state.getProgramID().toString()));

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CountDownLatchStmt(variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            Type varType = typeEnv.get(variableName);

            if (!varType.equals(new IntType()))
                throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR -Variable must be of type Integer");

            return typeEnv;
        } catch (Exception exc) {
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - " + exc);
        }
    }
}
