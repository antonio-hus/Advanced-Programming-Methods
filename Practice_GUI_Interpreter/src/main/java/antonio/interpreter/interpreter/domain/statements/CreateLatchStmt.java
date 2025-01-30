package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.IHeap;
import antonio.interpreter.interpreter.domain.state.ILatchTable;
import antonio.interpreter.interpreter.domain.state.ISemaphoreTable;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.Value;

public class CreateLatchStmt implements IStmt {

    // CREATE LATCH STATEMENT STRUCTURE
    String variableName;
    Exp exp;

    // CREATE LATCH STATEMENT CONSTRUCTOR
    public CreateLatchStmt(String variableName, Exp exp) {
        this.variableName = variableName;
        this.exp = exp;
    }

    // CREATE LATCH STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "newLatch("+ variableName + ", " + this.exp.toString() + ")";
    }


    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        // Get state information
        ISymTable symTable = state.getSymbolsTable();
        IHeap heap = state.getHeap();
        ILatchTable latchTable = state.getLatchTable();

        // Evaluate expression
        Value number1 = exp.eval(symTable, heap);

        // Must be integer
        if(!number1.getType().equals(new IntType()))
            throw new StmtException("CREATE LATCH STATEMENT - Expression must be of type Integer");

        // Safe cast to IntValue
        IntValue number1Int = (IntValue) number1;

        // Adding to latch table
        Integer newFreeLocation = latchTable.put(number1Int.getValue());

        // Updating value into SymTable
        // Must be inside SymTable and of type integer
        if(!symTable.containsKey(variableName))
            throw new StmtException("CREATE LATCH STATEMENT - Variable not inside SymTable");

        if(!symTable.get(variableName).getType().equals(new IntType()))
            throw new StmtException("CREATE LATCH STATEMENT - Variable not of type Integer");

        symTable.update(variableName, new IntValue(newFreeLocation));

        // Return null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CreateLatchStmt(variableName, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            Type varType = typeEnv.get(variableName);
            Type expType = exp.typeCheck(typeEnv);

            if (!varType.equals(new IntType()) || !expType.equals(new IntType()))
                throw new StmtException("CREATE LATCH STATEMENT ERROR - Expression and variable must be of type Integer");

            return typeEnv;
        } catch (Exception exc) {
            throw new StmtException("CREATE LATCH STATEMENT ERROR - " + exc);
        }
    }
}
