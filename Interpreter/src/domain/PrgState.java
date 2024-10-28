////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain;
import domain.datastructures.dictionary.*;
import domain.datastructures.list.*;
import domain.datastructures.stack.*;
import domain.statements.*;
import domain.values.*;
import java.io.BufferedReader;


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
    // File Table Dictionary
    MyIDictionary<StringValue, BufferedReader> fileTable;
    // We also need to keep track of the original program state
    IStmt originalProgram;

    // PROGRAM STATE CONSTRUCTOR
    public PrgState(MyIStack<IStmt> stack, MyIDictionary<String, Value> symTable, MyIList<Value> outputs, MyIDictionary<StringValue, BufferedReader> fileTable, IStmt prg){

        // Set the Program State Attributes
        this.executionStack = stack;
        this.symbolsTable = symTable;
        this.outputList = outputs;
        this.fileTable = fileTable;

        // Keep track of the original Program State
        // Recreate the entire original Program State
        originalProgram=deepCopy(prg);

        // Add the first statement on the Execution Stack
        executionStack.push(prg);
    }


    // PROGRAM STATE METHODS
    // Private Helper Methods
    private IStmt deepCopy(IStmt prg) {

        return prg.deepCopy();
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
    public MyIDictionary<StringValue, BufferedReader> getFileTable() { return this.fileTable; }

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
    public void setFileTable(MyIDictionary<StringValue, BufferedReader> newFileTable) { this.fileTable = newFileTable; }

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

        // File Table
        state.append("File Table = ");
        state.append(fileTable.toString());
        state.append("\n");

        state.append("====================>");
        state.append("\n");

        // Returning the state as String
        return state.toString();
    }
}
