////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.state;
import domain.datastructures.dictionary.MyDictionary;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class Heap implements IHeap {

    // HEAP STRUCTURE
    // Heap is based on a <integer, value> map
    MyIDictionary<Integer, Value> heap;
    // Heap keeps track of the next free location in memory
    int nextFree;

    // HEAP CONSTRUCTOR
    public Heap() {
        this.heap = new MyDictionary<>();
        nextFree = 1;
    }


    // HEAP METHODS
    // Put
    // Adds a new value to the dictionary in the next free "address"
    public Integer put(Value value) throws MyDictionaryException {
        this.heap.put(this.nextFree, value);
        this.nextFree += 1;

        return this.nextFree - 1;
    }

    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    @Override
    public void put(Integer key, Value value) throws MyDictionaryException {
        this.heap.put(key, value);
    }

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public Value remove(Integer key) throws MyDictionaryException {
        return this.heap.remove(key);
    }

    // Update
    // Updates a (key, value) pair in the dictionary
    // Throws an exception if the key is not already present inside the dictionary
    @Override
    public Value update(Integer key, Value newValue) throws MyDictionaryException {
        return this.heap.update(key, newValue);
    }

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    @Override
    public boolean containsKey(Integer key) {
        return this.heap.containsKey(key);
    }

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public Value get(Integer key) throws MyDictionaryException {
        return this.heap.get(key);
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public int size() {
        return this.heap.size();
    }

    // String Formatting
    @Override
    public String toString() {
        return this.heap.toString();
    }
}
