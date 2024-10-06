////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
package Model;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class Car implements Vehicle {

    // Generic & Specific Attributes
    private final String color;
    private final int numberOfWheels;
    private final boolean isMotorized;


    // Constructors
    public Car(String color) {
        this.color = color;
        this.numberOfWheels = 4;
        this.isMotorized = true;
    }


    // Vehicle Overriden Methods
    // Returns the color of the vehicle
    @Override
    public String getColor() {
        return color;
    }
    // Returns the number of wheels of the vehicle
    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    // Returns the motorization of the vehicle
    @Override
    public boolean isMotorized() {
        return isMotorized;
    }


    // Object Overriden Methods
    // String Format
    @Override
    public String toString() { return "Car (" + color + ")"; }
}
