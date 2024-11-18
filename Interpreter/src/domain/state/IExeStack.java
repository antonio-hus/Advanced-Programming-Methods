////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.state;
import domain.datastructures.stack.MyStackException;
import domain.statements.IStmt;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IExeStack {

    // I EXE STACK METHODS
    // Push
    // Pushes a new element on the top of the stack
    void push(IStmt v);

    // Pop
    // Removes and returns the top of the stack
    // Throws an exception if the stack is empty
    IStmt pop() throws MyStackException;

    // Top
    // Returns the top of the stack
    // Throws an exception if the stack is empty
    IStmt top() throws MyStackException;

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    boolean isEmpty();

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    int size();

    // Deep Copy
    // Returns a deep copy of the structure
    IExeStack deepCopy();

}
