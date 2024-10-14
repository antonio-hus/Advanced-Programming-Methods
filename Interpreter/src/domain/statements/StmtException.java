////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class StmtException extends Exception{

    // STATEMENT EXCEPTION METHODS
    // Default Exception Constructor
    StmtException() {
        super();
    }

    // Message Exception Constructor
    StmtException(String message) {
        super(message);
    }
}
