////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.datastructures.list;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface MyIList<T> {

    // MY I LIST METHODS

    // Add
    // Adds a new element to the end of the list
    void add(T object);

    // Add
    // Adds a new element on the specified index
    // Throws an exception if specified index is invalid
    void add(int index, T object) throws MyListException;

    // Remove
    // Removes the element found at the specified index
    // Throws an exception if specified index is invalid
    T remove(int index) throws MyListException;

    // Set
    // Updates the element found at the specified position
    // Throws an exception if specified index is invalid
    T set(int index, T newObject) throws MyListException;

    // Get
    // Gets the element found at the specified position
    // Throws an exception if specified index is invalid
    T get(int index) throws MyListException;

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    boolean isEmpty();

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    int size();
}
