package Repository;
import Model.*;

public class ParkingLot {
    private final Vehicle[] parkedVehicles;
    int capacity;
    int occupiedLots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new Vehicle[capacity];
        this.occupiedLots = 0;
    }

    public boolean isOccupied(int position) {
        return this.parkedVehicles[position] != null;
    }

    public void addVehicle(Vehicle vehicle, int position) throws ParkingException {

        // Check if position is not already occupied
        if(this.isOccupied(position)) {
            throw new ParkingException("Lot is already occupied");
        }

        // Park in the specified position
        this.parkedVehicles[position] = vehicle;
        this.occupiedLots += 1;
    }

    public void removeVehicle(int position) throws ParkingException {
        // Check if there is any vehicle in the specified position
        if(!this.isOccupied(position)) {
            throw new ParkingException("There is no vehicle in this lot");
        }

        // Remove vehicle from the specified position
        this.parkedVehicles[position] = null;
        this.occupiedLots -= 1;
    }

    public Vehicle[] getParkedVehicles() { return this.parkedVehicles; }
    public int getOccupiedLots() { return this.occupiedLots; };
    public int getCapacity() { return this.capacity; };
}
