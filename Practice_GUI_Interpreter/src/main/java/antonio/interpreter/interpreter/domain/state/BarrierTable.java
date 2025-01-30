package antonio.interpreter.interpreter.domain.state;

import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionary;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.values.Value;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class BarrierTable implements IBarrierTable {

    // BARRIER TABLE STRUCTURE
    MyIDictionary<Integer, Pair<Integer, MyIList<Integer>>> barrierTable;
    // Heap keeps track of the next free location in memory
    int nextFree;

    // BARRIER TABLE CONSTRUCTOR
    public BarrierTable() {
        this.barrierTable = new MyDictionary<>();
        nextFree = 1;
    }


    // HEAP METHODS
    // Set Content
    // Sets the content of the heap to the new one
    @Override
    public void setContent(Map<Integer, Pair<Integer, MyIList<Integer>>> newContent) {
        this.barrierTable.clear();
        this.barrierTable.putAll(newContent);
    }


    // Put
    // Adds a new value to the dictionary in the next free "address"
    public Integer put(Pair<Integer, MyIList<Integer>> value) throws MyDictionaryException {
        this.barrierTable.put(this.nextFree, value);
        this.nextFree += 1;

        return this.nextFree - 1;
    }

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    @Override
    public void put(Integer key, Pair<Integer, MyIList<Integer>> value) throws MyDictionaryException {
        this.barrierTable.put(key, value);
    }

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public Pair<Integer, MyIList<Integer>> remove(Integer key) throws MyDictionaryException {
        return this.barrierTable.remove(key);
    }

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    @Override
    public Pair<Integer, MyIList<Integer>> update(Integer key, Pair<Integer, MyIList<Integer>> newValue) throws MyDictionaryException {
        return this.barrierTable.update(key, newValue);
    }

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    @Override
    public boolean containsKey(Integer key) {
        return this.barrierTable.containsKey(key);
    }

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public Pair<Integer, MyIList<Integer>> get(Integer key) throws MyDictionaryException {
        return this.barrierTable.get(key);
    }

    // Get Content
    // Returns the hash map of all values
    @Override
    public HashMap<Integer, Pair<Integer, MyIList<Integer>>> getContent() {
        return this.barrierTable.getContent();
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public boolean isEmpty() {
        return this.barrierTable.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public int size() {
        return this.barrierTable.size();
    }

    // String Formatting
    @Override
    public String toString() {
        // Creating the String format of the Dictionary
        // Adding Start
        StringBuilder state = new StringBuilder("{");

        // Adding all elements inside the dictionary
        for (Integer key : barrierTable.getContent().keySet()) {
            Pair<Integer, MyIList<Integer>> value = null;
            try {
                value = barrierTable.get(key);
            } catch (MyDictionaryException e) {
                throw new RuntimeException(e);
            }
            state.append(key).append("->").append(value.getKey().toString()).append(value.getValue().toString()).append(", ");
        }

        // Removing the last comma and space if dictionary is not empty
        if (!barrierTable.isEmpty()) {
            state.setLength(state.length() - 2); // Remove the last ", "
        }

        // Adding End
        state.append("}");

        // Returning the State
        return state.toString();
    }
}
