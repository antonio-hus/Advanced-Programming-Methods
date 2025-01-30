package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.IHeap;
import antonio.interpreter.interpreter.domain.state.ILatchTable;
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
    @Override
    public String toString() {
        return "newLatch("+variableName+", "+exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get state information
        ISymTable symTable = state.getSymbolsTable();
        IHeap heap = state.getHeap();
        ILatchTable latchTable = state.getLatchTable();

        // Evaluate expression
        Value num1 = exp.eval(symTable, heap);
        Type num1Type = num1.getType();

        if(!num1Type.equals(new IntType()))
            throw new StmtException("CREATE LATCH STATEMENT ERROR - Expression must evaluate to Integer");

        // Safely cast to IntValue
        IntValue num1Int = (IntValue) num1;
        Integer num1Value = num1Int.getValue();

        // Add to latch table
        Integer newFreeLocation = latchTable.put(num1Value);

        // Check variable
        if(!symTable.containsKey(variableName) || !symTable.get(variableName).getType().equals(new IntType()))
            throw new StmtException("CREATE LATCH STATEMENT ERROR - Variable must be declared in the SymTable and have type Integer");

        // Update into symbols table
        symTable.update(variableName, new IntValue(newFreeLocation));

        // Return Null
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CreateLatchStmt(variableName, exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {

            // Type check expression and variable
            Type varType = typeEnv.get(variableName);
            Type expType = exp.typeCheck(typeEnv);

            if(!varType.equals(new IntType()) || !expType.equals(new IntType()))
                throw new StmtException("CREATE LATCH STATEMENT ERROR - Both variable and expression must evaluate to type Integer");

            return typeEnv;
        } catch (Exception exc) {
            throw new StmtException("CREATE LATCH STATEMENT ERROR - " + exc);
        }
    }
}
