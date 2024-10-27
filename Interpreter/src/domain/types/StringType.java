////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.types;


import domain.values.BoolValue;
import domain.values.StringValue;
import domain.values.Value;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class StringType implements Type {

    // STRING TYPE METHODS
    // Returns True if the other object is of instance StringType
    @Override
    public boolean equals(Object other){
        return other instanceof StringType;
    }

    // Returns a string format for the IntType
    @Override
    public String toString() { return "String";}

    // Returns the default value of a string type
    @Override
    public Value defaultValue() {
        return new StringValue("\"\"");
    }
}
