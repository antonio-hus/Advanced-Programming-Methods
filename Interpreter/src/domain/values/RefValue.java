////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.values;
import domain.types.RefType;
import domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class RefValue implements Value {

    // REF VALUE STRUCTURE
    // Based on a heap-address and the type of the location on that address
    Integer address;
    Type locationType;


    // REF VALUE CONSTRUCTOR
    public RefValue(int address, Type locationType) { this.address=address; this.locationType=locationType; }


    // REF VALUE METHODS
    // Getter Methods
    // Returns the address inside the heap memory
    public Integer getAddress() { return this.address; }

    // Returns the type of the location inside the heap memory
    public Type getLocationType() { return new RefType(locationType); }

    // Returns the type of the value - RefType
    @Override
    public Type getType() {
        return null;
    }

    // Returns a copy of the value
    @Override
    public Value deepCopy() {
        return new RefValue(this.address, new RefType(locationType));
    }

}
