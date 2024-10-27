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
    public BoolValue(Boolean v) { value = v; }


    // INT VALUE METHODS
    // Returns the boolean value stored
    public Boolean getValue() { return value; }

    // Returns a string form of the boolean value stored
    @Override
    public String toString() { return value.toString(); }

    // Returns true if the other object's type is BoolValue
    @Override
    public boolean equals(Object other) {

        // Check the type of other object
        if(!(other instanceof BoolValue otherValueType))
            return false;

        // Safely cast to BoolValue
        Boolean otherValue = otherValueType.getValue();

        // Check equality and return
        return otherValue.equals(this.value);
    }

    // Returns the type of the value - Boolean
    @Override
    public Type getType() {
        return new BoolType();
    }
}
