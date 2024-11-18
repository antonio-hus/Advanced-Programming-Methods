////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.state;
import domain.datastructures.list.MyIList;
import domain.datastructures.list.MyList;
import domain.datastructures.list.MyListException;
import domain.values.Value;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class OutList implements IOutList {

    // OUTPUT LIST STRUCTURE
    MyIList<Value> outputList;

    // OUTPUT LIST CONSTRUCTORS
    public OutList(){ this.outputList = new MyList<>();}

    // OUTPUT LIST METHODS
    // Add
    // Adds a new element to the end of the list
    @Override
    public void add(Value object) {
        this.outputList.add(object);
    }

    // Add
    // Adds a new element on the specified index
    // Throws an exception if specified index is invalid
    @Override
    public void add(int index, Value object) throws MyListException {
        this.outputList.add(index, object);
    }

    // Remove
    // Removes the element found at the specified index
    // Throws an exception if specified index is invalid
    @Override
    public Value remove(int index) throws MyListException {
        return this.outputList.remove(index);
    }

    // Set
    // Updates the element found at the specified position
    // Throws an exception if specified index is invalid
    @Override
    public Value set(int index, Value newObject) throws MyListException {
        return this.outputList.set(index, newObject);
    }

    // Get
    // Gets the element found at the specified position
    // Throws an exception if specified index is invalid
    @Override
    public Value get(int index) throws MyListException {
        return this.outputList.get(index);
    }

    // Is Empty
    // Returns True if the structure is empty
    // Returns False otherwise
    @Override
    public boolean isEmpty() {
        return this.outputList.isEmpty();
    }

    // Size
    // Returns the size of the structure ( number of elements in the collection )
    @Override
    public int size() {
        return this.outputList.size();
    }

    // String Formatting
    @Override
    public String toString() {
        return this.outputList.toString();
    }

    // Deep Copy
    // Returns a deep copy of the structure
    @Override
    public IOutList deepCopy() {
        OutList newOutList = new OutList();

        for (int i = 0; i < this.outputList.size(); i++) {
            try {
                Value value = this.outputList.get(i);
                newOutList.add(value.deepCopy());
            } catch (MyListException e) {
                throw new RuntimeException("Error during OutList deep copy: " + e.getMessage());
            }
        }

        return newOutList;
    }
}
