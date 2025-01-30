////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.state;


import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.util.Pair;

import java.util.HashMap;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface ISemaphoreTable {

    // SEMAPHORE TABLE METHODS

    // Set Content
    // Sets the content of the semaphoreTable to the new one
    void setContent(MyIDictionary<Integer, Pair<Integer, MyIList<Integer>>> newContent);

    // Put
    // Adds a new value to the dictionary in the next free "address"
    Integer put(Pair<Integer, MyIList<Integer>> value) throws MyDictionaryException;

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    void put(Integer key, Pair<Integer, MyIList<Integer>> values) throws MyDictionaryException;

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    Pair<Integer, MyIList<Integer>> remove(Integer key) throws MyDictionaryException;

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    Pair<Integer, MyIList<Integer>> update(Integer key, Pair<Integer, MyIList<Integer>> newValues) throws MyDictionaryException;

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    boolean containsKey(Integer key);

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    Pair<Integer, MyIList<Integer>> get(Integer key) throws MyDictionaryException;

    // Get Content
    // Returns the hash map of all values
    HashMap<Integer, Pair<Integer, MyIList<Integer>>> getContent();

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    boolean isEmpty();

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    int size();
}
