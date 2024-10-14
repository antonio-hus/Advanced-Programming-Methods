////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.datastructures.dictionary;


import java.util.HashMap;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    // MY DICTIONARY STRUCTURE
    // Dictionary - based on hash map
    HashMap<K, V> dictionary;


    // MY DICTIONARY METHODS
    // Put
    // Adds a new (key, value) pair to the dictionary
    // Throws an exception if the key is already present inside the dictionary
    @Override
    public void put(K key, V value) throws MyDictionaryException {
        // Check if the key is present inside the dictionary
        if(this.containsKey(key))
            throw new MyDictionaryException("Key was already found inside the Dictionary");

        // Adds the new (key, value) pair to the dictionary
        dictionary.put(key, value);
    }

    // Remove
    // Removes the (key, value) pair from the dictionary, given by its key
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public V remove(K key) throws MyDictionaryException {

        // Check if the key is present inside the dictionary
        if(!this.containsKey(key))
            throw new MyDictionaryException("Key was not found inside the Dictionary");

        // Removes the (key, value) pair associated with the given key from the dictionary
        // Returns the previous value
        return dictionary.remove(key);
    }

    // Contains Key
    // Return True if the key is present inside the dictionary
    // Returns False otherwise
    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }

    // Get
    // Gets the value associated to the given key from the dictionary
    // Throws an exception if the key is not present inside the dictionary
    @Override
    public V get(K key) throws MyDictionaryException {

        // Check if the key is present inside the dictionary
        if(!this.containsKey(key))
            throw new MyDictionaryException("Key was not found inside the Dictionary");

        // Returns the value associated to the given key
        return dictionary.get(key);
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public int size() {
        return dictionary.size();
    }
}
