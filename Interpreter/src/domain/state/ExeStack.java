////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.state;
import domain.datastructures.stack.MyIStack;
import domain.datastructures.stack.MyStack;
import domain.datastructures.stack.MyStackException;
import domain.statements.IStmt;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ExeStack implements IExeStack {

    // I EXE STACK STRUCTURE
    MyIStack<IStmt> executionStack;

    // I EXE STACK CONSTRUCTORS
    public ExeStack(){ this.executionStack = new MyStack<>(); }

    // I EXE STACK METHODS
    // Push
    // Pushes a new element on the top of the stack
    @Override
    public void push(IStmt v) { this.executionStack.push(v); }

    // Pop
    // Removes and returns the top of the stack
    // Throws an exception if the stack is empty
    @Override
    public IStmt pop() throws MyStackException { return this.executionStack.pop(); }

    // Top
    // Returns the top of the stack
    // Throws an exception if the stack is empty
    @Override
    public IStmt top() throws MyStackException { return this.executionStack.top(); }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public boolean isEmpty() { return this.executionStack.isEmpty(); }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public int size() { return this.executionStack.size(); }

    // String Formatting
    @Override
    public String toString() { return this.executionStack.toString(); }


    // Deep Copy
    @Override
    public IExeStack deepCopy() {

        IExeStack newStack = new ExeStack();
        MyIStack<IStmt> tempStack = new MyStack<>();

        // Move all elements from the current stack to the temporary stack
        while (!this.executionStack.isEmpty()) {
            try {
                IStmt stmt = this.executionStack.pop();
                tempStack.push(stmt);
            } catch (MyStackException e) {
                throw new RuntimeException("Error during deep copy: " + e.getMessage());
            }
        }

        // Restore the original stack and populate the new stack in the same order
        while (!tempStack.isEmpty()) {
            try {
                IStmt stmt = tempStack.pop();
                this.executionStack.push(stmt);
                newStack.push(stmt.deepCopy());
            } catch (MyStackException e) {
                throw new RuntimeException("Error during deep copy: " + e.getMessage());
            }
        }

        return newStack;
    }
}
