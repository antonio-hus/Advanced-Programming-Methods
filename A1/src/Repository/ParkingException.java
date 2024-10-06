////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
package Repository;


//////////////////////////////
// EXCEPTION IMPLEMENTATION //
//////////////////////////////
public class ParkingException extends Exception {

    // Default Constructor
    ParkingException() {
        super();
    }

    // Error-Message Constructor
    ParkingException(String message){
        super(message);
    }
}
