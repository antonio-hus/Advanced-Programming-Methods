////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ArithException extends ExpException {

    // ARITHMETIC EXPRESSION EXCEPTION METHODS
    // Default Exception Constructor
    ArithException() {
        super();
    }

    // Message Exception Constructor
    ArithException(String message) {
        super(message);
    }
}
