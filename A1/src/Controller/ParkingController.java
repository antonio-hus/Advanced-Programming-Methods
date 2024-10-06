package Controller;
import Model.Vehicle;
import Repository.*;

import java.util.Objects;

public class ParkingController {

    ParkingLot repository;
    public ParkingController(int capacity) { this.repository = new ParkingLot(capacity); }

    public int getEmpty() { return this.repository.getCapacity() - this.repository.getOccupiedLots(); };
    public int getOccupied() { return this.repository.getOccupiedLots(); };
    public int getCapacity() { return this.repository.getCapacity(); }

    public Vehicle[] getParkedVehicles() { return this.repository.getParkedVehicles(); }

    public boolean isOccupied(int position) { return this.repository.isOccupied(position); }

    public void addVehicle(Vehicle vehicle, int position) throws ParkingException { this.repository.addVehicle(vehicle, position);}
    public void removeVehicle(int position) throws ParkingException { this.repository.removeVehicle(position); }

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
