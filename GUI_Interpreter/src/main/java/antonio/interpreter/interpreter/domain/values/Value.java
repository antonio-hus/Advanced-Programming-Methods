////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.values;
import antonio.interpreter.interpreter.domain.types.Type;


///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Value {

    // Returns the type of the value
    Type getType();

    // Returns a copy of the value
    Value deepCopy();
}
