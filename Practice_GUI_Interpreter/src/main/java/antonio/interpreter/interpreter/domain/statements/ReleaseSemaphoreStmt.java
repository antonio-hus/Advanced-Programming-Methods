package antonio.interpreter.interpreter.domain.statements;

import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.datastructures.list.MyListException;
import antonio.interpreter.interpreter.domain.datastructures.stack.MyStackException;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.ISemaphoreTable;
import antonio.interpreter.interpreter.domain.state.ISymTable;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.Type;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.util.Pair;

public class ReleaseSemaphoreStmt implements IStmt {

    // RELEASE SEMAPHORE STRUCTURE
    String variableName;

    // RELEASE SEMAPHORE CONSTRUCTOR
    public ReleaseSemaphoreStmt(String variableName) { this.variableName = variableName; }

    // RELEASE SEMAPHORE METHODS
    // To String Method
    @Override
    public String toString() {
        return "releaseSemaphore("+ variableName + ")";
    }
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException, MyStackException {

        ISymTable symTable = state.getSymbolsTable();
        ISemaphoreTable semaphoreTable = state.getSemaphoreTable();

        if(!symTable.containsKey(variableName))
            throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - Variable not inside symbols table");

        Value result = symTable.get(variableName);
        Type resultType = result.getType();

        if(!resultType.equals(new IntType()))
            throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - Variable must be of type integer");

        IntValue resultInt = (IntValue) result;
        Integer foundIndex = resultInt.getValue();

        if(!semaphoreTable.containsKey(foundIndex))
            throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - Found Index is not inside semaphore table");

        Pair<Integer, MyIList<Integer>> pair = semaphoreTable.get(foundIndex);

        try{
            if (pair.getValue().getContent().contains(state.getProgramID())) {
                int index = pair.getValue().getContent().indexOf(state.getProgramID());
                pair.getValue().remove(index);
            }
        }catch (MyListException exp) {
            throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - Problem removing program id from table");
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ReleaseSemaphoreStmt(variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try {
            // Check the type of the variable
            Type typeVar = typeEnv.get(variableName);

            // The type of the variable must be integer
            if(!typeVar.equals(new IntType())) {
                throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - The type of the variable must be integer");
            }

            // Return the typechecking dictionary
            return typeEnv;

        } catch(MyDictionaryException exp) {
            throw new StmtException("RELEASE SEMAPHORE STATEMENT ERROR - " + exp);
        }
    }
}
