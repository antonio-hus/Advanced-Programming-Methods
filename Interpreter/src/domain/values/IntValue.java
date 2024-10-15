////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.values;
import domain.types.IntType;
import domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class IntValue implements Value {

    // INT VALUE STRUCTURE
    // Based on an integer
    Integer value;


    // INT VALUE CONSTRUCTORS
    public IntValue(Integer v) { value = v; }


    // INT VALUE METHODS
    // Returns the integer value stored
    public Integer getValue() { return value; }

    // Returns a string form of the integer value stored
    @Override
    public String toString() { return value.toString(); }

    // Returns the type of the value - Integer
    @Override
    public Type getType() {
        return new IntType();
    }
}
