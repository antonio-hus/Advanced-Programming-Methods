////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.state;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.values.Value;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IHeap {

    // HEAP METHODS

    // Put
    // Adds a new value to the dictionary in the next free "address"
    Integer put(Value value) throws MyDictionaryException;

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    void put(Integer key, Value value) throws MyDictionaryException;

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    Value remove(Integer key) throws MyDictionaryException;

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    Value update(Integer key, Value newValue) throws MyDictionaryException;

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    boolean containsKey(Integer key);

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    Value get(Integer key) throws MyDictionaryException;

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    boolean isEmpty();

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    int size();
}
