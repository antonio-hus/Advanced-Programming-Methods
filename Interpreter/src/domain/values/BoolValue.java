////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.values;
import domain.types.BoolType;
import domain.types.IntType;
import domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class BoolValue implements Value {

    // BOOLEAN VALUE STRUCTURE
    // Based on a boolean
    Boolean value;


    // BOOLEAN VALUE CONSTRUCTORS
    BoolValue(Boolean v) { value = v; }


    // INT VALUE METHODS
    // Returns the boolean value stored
    Boolean getValue() { return value; }

    // Returns a string form of the boolean value stored
    @Override
    public String toString() { return value.toString(); }

    // Returns the type of the value - Boolean
    @Override
    public Type getType() {
        return new BoolType();
    }
}
