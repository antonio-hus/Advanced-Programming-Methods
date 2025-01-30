package antonio.interpreter.interpreter.domain.state;

import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionary;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.values.Value;

import java.util.HashMap;
import java.util.Map;

public class LatchTable implements ILatchTable {

    // LATCH TABLE STRUCTURE
    // Latch Table is based on a <integer, integer> map
    MyIDictionary<Integer, Integer> latchTable;
    // Latch Table keeps track of the next free location in memory
    int nextFree;

    // LATCH TABLE CONSTRUCTOR
    public LatchTable() {
        this.latchTable = new MyDictionary<>();
        nextFree = 1;
    }

    // HEAP METHODS
    // Set Content
    // Sets the content of the heap to the new one
    @Override
    public synchronized void setContent(Map<Integer, Integer> newContent) {
        this.latchTable.clear();
        this.latchTable.putAll(newContent);
    }


    // Put
    // Adds a new value to the dictionary in the next free "address"
    public synchronized Integer put(Integer value) throws MyDictionaryException {
        this.latchTable.put(this.nextFree, value);
        this.nextFree += 1;

        return this.nextFree - 1;
    }

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    @Override
    public synchronized void put(Integer key, Integer value) throws MyDictionaryException {
        this.latchTable.put(key, value);
    }

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public synchronized Integer remove(Integer key) throws MyDictionaryException {
        return this.latchTable.remove(key);
    }

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    @Override
    public synchronized Integer update(Integer key, Integer newValue) throws MyDictionaryException {
        return this.latchTable.update(key, newValue);
    }

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    @Override
    public synchronized boolean containsKey(Integer key) {
        return this.latchTable.containsKey(key);
    }

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public synchronized Integer get(Integer key) throws MyDictionaryException {
        return this.latchTable.get(key);
    }

    // Get Content
    // Returns the hash map of all values
    @Override
    public synchronized HashMap<Integer, Integer> getContent() {
        return this.latchTable.getContent();
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public synchronized boolean isEmpty() {
        return this.latchTable.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public synchronized int size() {
        return this.latchTable.size();
    }

    // String Formatting
    @Override
    public synchronized String toString() {
        return this.latchTable.toString();
    }
}
