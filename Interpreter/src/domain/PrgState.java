////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain;
import domain.datastructures.dictionary.*;
import domain.datastructures.list.*;
import domain.datastructures.stack.*;
import domain.statements.*;
import domain.values.*;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class PrgState {

    // PROGRAM STATE STRUCTURE
    // Program State is based on:
    // Execution Stack
    MyIStack<IStmt> executionStack;
    // Symbols Table Dictionary
    MyIDictionary<String, Value> symbolsTable;
    // Output List
    MyIList<Value> outputList;
    // We also need to keep track of the original program state
    IStmt originalProgram;

    // PROGRAM STATE CONSTRUCTOR
    public PrgState(MyIStack<IStmt> stack, MyIDictionary<String, Value> symTable, MyIList<Value> outputs, IStmt prg){

        // Set the Program State Attributes
        executionStack = stack;
        symbolsTable = symTable;
        outputList = outputs;

        // Keep track of the original Program State
        // Recreate the entire original Program State
        originalProgram=deepCopy(prg);

        // Add the first statement on the Execution Stack
        executionStack.push(prg);
    }


    // PROGRAM STATE METHODS
    // Private Helper Methods
    private IStmt deepCopy(IStmt prg) {
        return prg;
    }

    // Public Methods
    // Getters
    public MyIStack<IStmt> getExecutionStack() {
        return this.executionStack;
    }
    public MyIDictionary<String, Value> getSymbolsTable() {
        return this.symbolsTable;
    }
    public MyIList<Value> getOutputList() {return this.outputList; }

    // Setters
    public void setExecutionStack(MyIStack<IStmt> newExecutionStack) {
        this.executionStack = newExecutionStack;
    }
    public void setSymbolsTable(MyIDictionary<String, Value> newSymTable) {
        this.symbolsTable = newSymTable;
    }
    public void setOutputList(MyIList<Value> newOutputList) {
        this.outputList = newOutputList;
    }

    // String Formatting
    @Override
    public String toString() {

        // Creating the string format of the state
        StringBuilder state = new StringBuilder();

        // Execution Stack
        state.append("Execution Stack = ");
        state.append(executionStack.toString());
        state.append("\n");

        // Symbols Table
        state.append("Symbols Table = ");
        state.append(symbolsTable.toString());
        state.append("\n");

        // Output List
        state.append("Output List = ");
        state.append(outputList.toString());
        state.append("\n");

        state.append("====================>");
        state.append("\n");

        // Returning the state as String
        return state.toString();
    }
}
