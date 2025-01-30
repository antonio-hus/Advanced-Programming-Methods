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

public class AwaitLatch implements IStmt {

    // AWAIT LATCH STATEMENT STRUCTURE
    String variableName;

    // AWAIT LATCH STATEMENT CONSTRUCTOR
    public AwaitLatch(String variableName) { this.variableName = variableName; }

    // AWAIT LATCH STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "awaitLatch("+ variableName + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        // Get state information
        ISymTable symTable = state.getSymbolsTable();
        ILatchTable latchTable = state.getLatchTable();

        // Variable must be in symTable and of type int
        if(!symTable.containsKey(variableName))
            throw new StmtException("AWAIT LATCH STATEMENT ERROR - Variable not inside Symbols Table");

        if(!symTable.get(variableName).getType().equals(new IntType()))
            throw new StmtException("AWAIT LATCH STATEMENT ERROR - Variable not of type Integer");

        IntValue index = (IntValue) symTable.get(variableName);
        Integer foundIndex = index.getValue();

        if(!latchTable.containsKey(foundIndex))
            throw new StmtException("AWAIT LATCH STATEMENT ERROR - Index not inside LatchTable");

        Integer value = latchTable.get(foundIndex);
        if(value != 0) {
            state.getExecutionStack().push(new AwaitLatch(variableName));
        }

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AwaitLatch(this.variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            Type varType = typeEnv.get(variableName);

            if (!varType.equals(new IntType()))
                throw new StmtException("AWAIT LATCH STATEMENT ERROR -Variable must be of type Integer");

            return typeEnv;
        } catch (Exception exc) {
            throw new StmtException("AWAIT LATCH STATEMENT ERROR - " + exc);
        }
    }
}
