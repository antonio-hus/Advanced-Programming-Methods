////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain;

import antonio.interpreter.interpreter.domain.datastructures.dictionary.*;
import antonio.interpreter.interpreter.domain.datastructures.list.*;
import antonio.interpreter.interpreter.domain.datastructures.stack.*;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.state.*;
import antonio.interpreter.interpreter.domain.statements.*;
import antonio.interpreter.interpreter.domain.values.*;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class PrgState {

    // PROGRAM STATE STRUCTURE
    // Static ID for all Program States
    static Integer id = 0;

    // Program State Attributes
    // Program ID
    Integer programID;
    // Execution Stack
    IExeStack executionStack;
    // Symbols Table Dictionary
    MyIStack<ISymTable> symbolsTables;
    // Output List
    IOutList outputList;
    // File Table Dictionary
    IFileTable fileTable;
    // Heap Map
    IHeap heap;
    // Semaphore Table
    ISemaphoreTable semaphoreTable;
    // Latch Table
    ILatchTable latchTable;
    // Lock Table
    ILockTable lockTable;
    // Barrier Table
    IBarrierTable barrierTable;
    // Proc Table
    IProcTable procTable;
    // We also need to keep track of the original program state
    IStmt originalProgram;

    // PROGRAM STATE CONSTRUCTOR
    public PrgState(IExeStack stack, MyIStack<ISymTable> symTables, IOutList outputs, IFileTable fileTable, IHeap heap, ISemaphoreTable semaphoreTable, ILatchTable latchTable, ILockTable lockTable, IBarrierTable barrierTable, IProcTable procTable, IStmt prg){

        // Set the Program State Attributes
        this.executionStack = stack;
        this.symbolsTables = symTables;
        this.outputList = outputs;
        this.fileTable = fileTable;
        this.semaphoreTable = semaphoreTable;
        this.latchTable = latchTable;
        this.lockTable = lockTable;
        this.heap = heap;
        this.barrierTable = barrierTable;
        this.procTable = procTable;
        this.programID = getNextId();

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
    public Integer getProgramID() { return this.programID; }
    public IExeStack getExecutionStack() {
        return this.executionStack;
    }
    public ISymTable getSymbolsTable() throws MyStackException {
        return this.symbolsTables.top();
    }
    public MyIStack<ISymTable> getSymbolsTableStack() {
        return this.symbolsTables;
    }
    public IOutList getOutputList() {return this.outputList; }
    public IFileTable getFileTable() { return this.fileTable; }
    public ISemaphoreTable getSemaphoreTable() { return this.semaphoreTable; }
    public ILatchTable getLatchTable() { return this.latchTable; }
    public ILockTable getLockTable() { return this.lockTable; }
    public IBarrierTable getBarrierTable() { return this.barrierTable; }
    public IProcTable getProcTable() { return this.procTable; }
    public IHeap getHeap() { return this.heap; }

    // Setters
    public void setExecutionStack(IExeStack newExecutionStack) {
        this.executionStack = newExecutionStack;
    }
    public void setSymbolsTable(MyIStack<ISymTable> newSymTables) {
        this.symbolsTables = newSymTables;
    }
    public void setOutputList(IOutList newOutputList) {
        this.outputList = newOutputList;
    }
    public void setFileTable(IFileTable newFileTable) { this.fileTable = newFileTable; }
    public void setSemaphoreTable(ISemaphoreTable newSemaphoreTable) { this.semaphoreTable = newSemaphoreTable; }
    public void setLatchTable(ILatchTable newLatchTable) { this.latchTable = newLatchTable; }
    public void setLockTable(ILockTable newLockTable) { this.lockTable = newLockTable; }
    public void setBarrierTable(IBarrierTable newBarrierTable) { this.barrierTable = newBarrierTable; }
    public void setProcTable(IProcTable procTable) { this.procTable = procTable; }
    public void setHeap(IHeap heap) { this.heap = heap; }

    // State Information Getters
    public Boolean isNotCompleted() { return !executionStack.isEmpty(); }

    // State Information Setters
    public static synchronized int getNextId() {
        return ++id;
    }

    // State Execution
    public PrgState oneStep() throws StmtException, ExpException, MyDictionaryException, PrgStateException, MyStackException, MyListException {

        // Check if there are statements left to execute
        if(this.getExecutionStack().isEmpty()){
            throw new PrgStateException("Program State Error - Execution Stack is Empty");
        }

        // Execute one statement
        IStmt currentStatement = this.getExecutionStack().pop();
        return currentStatement.execute(this);
    }

    // String Formatting
    @Override
    public String toString() {

        // Creating the string format of the state
        StringBuilder state = new StringBuilder();

        // Id
        state.append("ID = ");
        state.append(programID.toString());
        state.append("\n");

        // Execution Stack
        state.append("Execution Stack = ");
        state.append(executionStack.toString());
        state.append("\n");

        // Symbols Table
        state.append("Symbols Table Stack = ");
        state.append(symbolsTables.toString());
        state.append("\n");

        // Output List
        state.append("Output List = ");
        state.append(outputList.toString());
        state.append("\n");

        // File Table
        state.append("File Table = ");
        state.append(fileTable.toString());
        state.append("\n");

        // Heap
        state.append("Heap = ");
        state.append(heap.toString());
        state.append("\n");

        // Semaphore Table
        state.append("Semaphore Table = ");
        state.append(semaphoreTable.toString());
        state.append("\n");

        // Latch Table
        state.append("Latch Table = ");
        state.append(latchTable.toString());
        state.append("\n");

        // Lock Table
        state.append("Lock Table = ");
        state.append(lockTable.toString());
        state.append("\n");

        // Barrier Table
        state.append("Barrier Table = ");
        state.append(barrierTable.toString());
        state.append("\n");

        // Proc Table
        state.append("Proc Table = ");
        state.append(procTable.toString());
        state.append("\n");

        state.append("====================>");
        state.append("\n");

        // Returning the state as String
        return state.toString();
    }

    // String Logging Formatting
    public String toLogString() {
        // Creating the string format of the state
        StringBuilder state = new StringBuilder();

        // Id
        state.append("ID = ");
        state.append(programID.toString());
        state.append("\n");

        // Execution Stack
        state.append("Execution Stack = \n");
        state.append(executionStack.toFString());
        state.append("\n");

        // Symbols Table
        state.append("Symbols Table Stack = ");
        state.append(symbolsTables.toString());
        state.append("\n");

        // Output List
        state.append("Output List = ");
        state.append(outputList.toString());
        state.append("\n");

        // File Table
        state.append("File Table = ");
        state.append(fileTable.toString());
        state.append("\n");

        // Heap
        state.append("Heap = ");
        state.append(heap.toString());
        state.append("\n");

        // Semaphore Table
        state.append("Semaphore Table = ");
        state.append(semaphoreTable.toString());
        state.append("\n");

        // Latch Table
        state.append("Latch Table = ");
        state.append(latchTable.toString());
        state.append("\n");

        // Lock Table
        state.append("Lock Table = ");
        state.append(lockTable.toString());
        state.append("\n");

        // Barrier Table
        state.append("Barrier Table = ");
        state.append(barrierTable.toString());
        state.append("\n");

        // Proc Table
        state.append("Proc Table = ");
        state.append(procTable.toString());
        state.append("\n");

        state.append("====================>");
        state.append("\n\n\n");

        // Returning the state as String
        return state.toString();
    }
}
