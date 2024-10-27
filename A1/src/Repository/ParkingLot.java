////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
package Repository;
import Model.*;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ParkingLot implements IParkingLot {

    // Parking Lot Attributes
    // Generic Information
    private final int capacity;
    private int occupiedLots;
    // Specific Information
    private final Vehicle[] parkedVehicles;


    // Constructors
    public ParkingLot(int capacity) {

        // New parking lots are initially empty
        this.occupiedLots = 0;
        this.capacity = capacity;

        // Allocate space for the vehicles
        this.parkedVehicles = new Vehicle[capacity];
    }

    // Parking Lot Attribute Getters
    // Generic Information
    // Returns the capacity of the parking lot
    public int getCapacity() { return this.capacity; }
    // Specific Information
    // Returns the number of occupied lots in the parking lot
    public int getOccupiedLots() { return this.occupiedLots; }
    // Returns true if the specified parking lot is occupied, false otherwise
    public boolean isOccupied(int position) {
        return this.parkedVehicles[position] != null;
    }
    // Returns the list of vehicles in the parking lot ( may include null elements if lot is empty )
    public Vehicle[] getParkedVehicles() { return this.parkedVehicles; }


    // Parking Lot CRUD Operations
    // Add a new vehicle
    // Adds a new vehicle to the specified position
    // Throws ParkingException if lot is already occupied
    public void addVehicle(Vehicle vehicle, int position) throws ParkingException {
        // Check position index is valid
        if(position < 0 || position >= this.getCapacity()){
            throw new ParkingException("Position is invalid");
        }

        // Check if position is not already occupied
        if(this.isOccupied(position)) {
            throw new ParkingException("Lot is already occupied");
        }

        // Park in the specified position
        this.parkedVehicles[position] = vehicle;
        this.occupiedLots += 1;
    }

    // Remove a vehicle
    // Removes a vehicle from the specified position
    // Throws ParkingException if the lot is not occupied
    public void removeVehicle(int position) throws ParkingException {
        // Check position index is valid
        if(position < 0 || position >= this.getCapacity()){
            throw new ParkingException("Position is invalid");
        }

        // Check if there is any vehicle in the specified position
        if(!this.isOccupied(position)) {
            throw new ParkingException("There is no vehicle in this lot");
        }

        // Remove vehicle from the specified position
        this.parkedVehicles[position] = null;
        this.occupiedLots -= 1;
    }
}
