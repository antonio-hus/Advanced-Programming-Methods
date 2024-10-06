////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
package Model;


//////////////////////////
// INTERFACE DEFINITION //
//////////////////////////
public interface Vehicle {

    // Generic Vehicle Information Getter Methods
    // Returns the color of the vehicle
    String getColor();
    // Returns the number of wheels of the vehicle
    int getNumberOfWheels();
    // Returns the motorization of the vehicle
    boolean isMotorized();
}
