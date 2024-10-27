////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
package Repository;


import Model.Vehicle;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface IParkingLot {

    public int getCapacity();
    public int getOccupiedLots();
    public boolean isOccupied(int position);
    public Vehicle[] getParkedVehicles();
    public void addVehicle(Vehicle vehicle, int position) throws ParkingException;
    public void removeVehicle(int position) throws ParkingException;
}
