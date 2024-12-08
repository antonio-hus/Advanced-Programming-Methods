////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.state;
import antonio.interpreter.interpreter.domain.datastructures.list.MyListException;
import antonio.interpreter.interpreter.domain.values.Value;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IOutList {

    // I OUTPUT LIST METHODS

    // Add
    // Adds a new element to the end of the list
    void add(Value object);

    // Add
    // Adds a new element on the specified index
    // Throws an exception if specified index is invalid
    void add(int index, Value object) throws MyListException;

    // Remove
    // Removes the element found at the specified index
    // Throws an exception if specified index is invalid
    Value remove(int index) throws MyListException;

    // Set
    // Updates the element found at the specified position
    // Throws an exception if specified index is invalid
    Value set(int index, Value newObject) throws MyListException;

    // Get
    // Gets the element found at the specified position
    // Throws an exception if specified index is invalid
    Value get(int index) throws MyListException;

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    boolean isEmpty();

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    int size();

    // Deep Copy
    // Returns a deep copy of the structure
    IOutList deepCopy();
}
