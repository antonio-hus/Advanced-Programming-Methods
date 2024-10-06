////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
// Project Packages
package Controller;
import Model.Vehicle;
import Repository.*;
// Java Packages
import java.util.Objects;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ParkingController {

    // Parking Lot Attributes
    ParkingLot repository;


    // Constructor
    public ParkingController(int capacity) {

        // Initialize a parking lot of the requested capacity
        this.repository = new ParkingLot(capacity);
    }

    // Parking Lot Information Getter Methods
    // Generic Information
    // Returns the capacity of the parking lot
    public int getCapacity() { return this.repository.getCapacity(); }
    // Specific Information
    // Returns the number of empty lots in the parking lot
    public int getEmpty() { return this.repository.getCapacity() - this.repository.getOccupiedLots(); }
    // Returns the number of occupied lots in the parking lot
    public int getOccupied() { return this.repository.getOccupiedLots(); }
    // Returns the list of vehicles parked in the parking lot ( may include null, where the lot is empty )
    public Vehicle[] getParkedVehicles() { return this.repository.getParkedVehicles(); }
    // Returns true if the specified parking lot is occupied, false otherwise
    public boolean isOccupied(int position) { return this.repository.isOccupied(position); }

    // Parking Lot CRUD Operations
    // Add a new vehicle
    // Adds a new vehicle to the specified position
    // Throws ParkingException if lot is already occupied
    public void addVehicle(Vehicle vehicle, int position) throws ParkingException { this.repository.addVehicle(vehicle, position);}

    // Remove a vehicle
    // Removes a vehicle from the specified position
    // Throws ParkingException if the lot is not occupied
    public void removeVehicle(int position) throws ParkingException { this.repository.removeVehicle(position); }


    // Parking Lot Query Operations
    // Get Vehicles by Color
    // Returns a list of vehicles of the specified color
    public Vehicle[] getVehiclesByColor(String color) {

        // Create a list of vehicles
        Vehicle[] vehicles = new Vehicle[this.repository.getOccupiedLots()];
        int i = 0;

        // Append vehicles of color to the list
        for(Vehicle vehicle: this.repository.getParkedVehicles()){
            if(vehicle != null && Objects.equals(vehicle.getColor(), color)){
                vehicles[i++] = vehicle;
            }
        }

        // Return vehicles
        return vehicles;
    }
}
