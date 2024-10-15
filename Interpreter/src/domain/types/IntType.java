////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.types;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class IntType implements Type {

    // INT TYPE METHODS
    // Returns True if the other object is of instance IntType
    @Override
    public boolean equals(Object other){
        return other instanceof IntType;
    }

    // Returns a string format for the IntType
    @Override
    public String toString() { return "int";}
}
