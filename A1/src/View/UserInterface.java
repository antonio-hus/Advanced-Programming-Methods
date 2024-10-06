package View;
import Controller.*;
import Model.Bicycle;
import Model.Car;
import Model.Motorcycle;
import Model.Vehicle;
import Repository.ParkingException;

import java.util.Scanner;

public class UserInterface {

    ParkingController controller;
    public UserInterface() {
        this.welcomeScreen();
        this.parkingLotSetup();
    }

    public void welcomeScreen() {
        System.out.println("Welcome to the Parking Lot Management Application");
        System.out.println("Made by Antonio Hus, Group 924/1");
        System.out.println();
        System.out.println();
        System.out.println("Press 'Enter' to proceed");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void parkingLotSetup() {
        System.out.println("Let's setup the Parking Lot");
        System.out.println("Please enter capacity: ");

        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        this.controller = new ParkingController(capacity);
    }

    public void parkingLotView() {
        System.out.println("Parking lot status:");
        System.out.printf("%d out of %d lots are occupied", this.controller.getOccupied(), this.controller.getCapacity());
        System.out.println();

        // Draw Parking Lot
        for(int i=0; i<this.controller.getCapacity(); i+=1) { System.out.print("____"); }
        System.out.println("_");

        for(int i=0; i<this.controller.getCapacity(); i+=1) {
            if(this.controller.isOccupied(i)) System.out.print("| X ");
            else System.out.print("|   ");
        }
        System.out.println("|");
        System.out.println();
    }

    public int parkingLotOptions() {

        this.parkingLotView();

        System.out.println("1. Park a new Car");
        System.out.println("2. Park a new Bicycle");
        System.out.println("3. Park a new Motorcycle");
        System.out.println("4. Remove a vehicle");
        System.out.println("5. Get red vehicles");
        System.out.println("0. Exit");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1: this.addVehicleView("Car"); break;
            case 2: this.addVehicleView("Bicycle"); break;
            case 3: this.addVehicleView("Motorcycle"); break;
            case 4: this.removeVehicleView(); break;
            case 5: this.getVehiclesByColorView("red"); break;
            case 0: return 0; // Exit action
        }

        return 1;
    }

    public void addVehicleView(String type) {

        this.parkingLotView();

        Vehicle newVehicle = null;
        System.out.println("Please enter the color of the new vehicle");
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();

        newVehicle = switch (type) {
            case "Car" -> new Car(color);
            case "Bicycle" -> new Bicycle(color);
            case "Motorcycle" -> new Motorcycle(color);
            default -> newVehicle;
        };

        System.out.println("Choose where to park the vehicle:");
        int position = scanner.nextInt();

        try {
            this.controller.addVehicle(newVehicle, position);
        } catch (ParkingException e) {
            System.out.println("This parking lot is already occupied! Vehicle was not added!");
        }
    }

    public void removeVehicleView() {
        this.parkingLotView();

        System.out.println("Choose where to remove the vehicle from:");
        Scanner scanner = new Scanner(System.in);
        int position = scanner.nextInt();

        try {
            this.controller.removeVehicle(position);
        } catch (ParkingException e) {
            System.out.println("This parking lot is not occupied! No vehicle was removed!");
        }
    }

    public void getVehiclesByColorView(String color) {

        this.parkingLotView();

        System.out.println("The following vehicles are of color" + color + ":");
        int i = 0;
        for(Vehicle vehicle: this.controller.getVehiclesByColor(color)) {
            if(vehicle != null) {
                System.out.println(i+". " + vehicle);
                i += 1;
            }
        }

    }
}
