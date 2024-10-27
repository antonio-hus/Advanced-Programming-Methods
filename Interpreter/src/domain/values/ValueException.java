////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.values;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ValueException extends Exception {

    // VALUE EXCEPTION METHODS
    // Default Exception Constructor
    ValueException() {
        super();
    }

    // Message Exception Constructor
    ValueException(String message) {
        super(message);
    }
}
