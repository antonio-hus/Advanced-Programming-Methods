package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.ILatchTable;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.IntValue;

public class CountDownLatchStmt implements IStmt {

    // COUNT DOWN LATCH STATEMENT STRUCTURE
    String variableName;


    // COUNT DOWN LATCH STATEMENT CONSTRUCTOR
    public CountDownLatchStmt(String variableName) {
        this.variableName = variableName;
    }


    // AWAIT LATCH STATEMENT METHODS
    @Override
    public String toString() {
        return "countDown(" + variableName + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get state information
        ISymTable symTable = state.getSymbolsTable();
        ILatchTable latchTable = state.getLatchTable();

        // Check variable
        if(!symTable.containsKey(variableName) || !symTable.get(variableName).getType().equals(new IntType()))
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Variable must be declared in the SymTable and have type Integer");

        // Get found Index
        Integer foundIndex = ((IntValue) symTable.get(variableName)).getValue();

        // Check in LatchTable
        if(!latchTable.containsKey(foundIndex))
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Index not found inside the LatchTable");

        // Perform count down
        if(latchTable.get(foundIndex) > 0) {
            latchTable.update(foundIndex, latchTable.get(foundIndex) - 1);
        }

        // Write into output list
        state.getOutputList().add(new IntValue(state.getProgramID()));

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AwaitLatchStmt(variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            // Type check variable
            Type varType = typeEnv.get(variableName);

            if(!varType.equals(new IntType()))
                throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - Variable must evaluate to type Integer");

            return typeEnv;
        } catch (Exception exc) {
            throw new StmtException("COUNT DOWN LATCH STATEMENT ERROR - " + exc);
        }
    }
}
