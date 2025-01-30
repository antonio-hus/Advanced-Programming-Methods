package antonio.interpreter.interpreter.domain.state;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionary;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.datastructures.list.MyList;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.util.Pair;

import java.util.*;

public class SemaphoreTable implements ISemaphoreTable {

    // SEMAPHORE TABLE STRUCTURE
    MyIDictionary<Integer, Pair<Integer, MyIList<Integer>>> semaphoreTable;
    // Semaphore Table keeps track of the next free location in memory
    int nextFree;

    // HEAP CONSTRUCTOR
    public SemaphoreTable() {
        this.semaphoreTable = new MyDictionary<>();
        nextFree = 1;
    }


    // HEAP METHODS
    // Set Content
    // Sets the content of the heap to the new one
    @Override
    public synchronized void setContent(MyIDictionary<Integer, Pair<Integer, MyIList<Integer>>> newContent) {
        this.semaphoreTable.clear();
        this.semaphoreTable.putAll(newContent.getContent());
    }

    // Put
    // Adds a new value to the dictionary in the next free "address"
    @Override
    public Integer put(Pair<Integer, MyIList<Integer>> value) throws MyDictionaryException {
        this.semaphoreTable.put(this.nextFree, value);
        this.nextFree += 1;

        return this.nextFree - 1;
    }

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    @Override
    public synchronized void put(Integer key, Pair<Integer, MyIList<Integer>> values) throws MyDictionaryException {
        this.semaphoreTable.put(key, values);
    }

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public synchronized Pair<Integer, MyIList<Integer>> remove(Integer key) throws MyDictionaryException {
        return this.semaphoreTable.remove(key);
    }

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    @Override
    public synchronized Pair<Integer, MyIList<Integer>> update(Integer key, Pair<Integer, MyIList<Integer>> newValues) throws MyDictionaryException {
        return this.semaphoreTable.update(key, newValues);
    }

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    @Override
    public synchronized boolean containsKey(Integer key) {
        return this.semaphoreTable.containsKey(key);
    }

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public synchronized Pair<Integer, MyIList<Integer>> get(Integer key) throws MyDictionaryException {
        return this.semaphoreTable.get(key);
    }

    // Get Content
    // Returns the hash map of all values
    @Override
    public synchronized HashMap<Integer, Pair<Integer, MyIList<Integer>>> getContent() {
        return this.semaphoreTable.getContent();
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public synchronized boolean isEmpty() {
        return this.semaphoreTable.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public synchronized int size() {
        return this.semaphoreTable.size();
    }

    // String Formatting
    @Override
    public synchronized String toString() {
        // Creating the String format of the Dictionary
        // Adding Start
        StringBuilder state = new StringBuilder("{");

        // Adding all elements inside the dictionary
        for (Integer key : semaphoreTable.getContent().keySet()) {
            Pair<Integer, MyIList<Integer>> value = null;
            try {
                value = semaphoreTable.get(key);
            } catch (MyDictionaryException e) {
                throw new RuntimeException(e);
            }
            state.append(key).append("->").append(value.getKey().toString()).append(value.getValue().toString()).append(", ");
        }

        // Removing the last comma and space if dictionary is not empty
        if (!semaphoreTable.isEmpty()) {
            state.setLength(state.length() - 2); // Remove the last ", "
        }

        // Adding End
        state.append("}");

        // Returning the State
        return state.toString();
    }
}
