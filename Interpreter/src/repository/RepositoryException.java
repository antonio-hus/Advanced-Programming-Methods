////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package repository;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class RepositoryException extends Exception {

    // REPOSITORY EXCEPTION METHODS
    // Default Exception Constructor
    RepositoryException() {
        super();
    }

    // Message Exception Constructor
    RepositoryException(String message) {
        super(message);
    }
}