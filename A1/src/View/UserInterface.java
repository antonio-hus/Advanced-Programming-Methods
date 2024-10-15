////////////////////////////////
// IMPORTS & PACKAGES SECTION //
////////////////////////////////
// Project Packages
package View;
import Model.*;
import Repository.ParkingException;
import Controller.*;
// Java Packages
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


    //////////////////////////
    // CLASS IMPLEMENTATION //
    //////////////////////////
public class UserInterface {

    // User Interface Attributes
    // MVC Design - UserInterface uses a controller to get information about the application
    ParkingController controller;


    // Constructor
    public UserInterface() {

        // Welcome Screen
        this.welcomeScreen();

        // Setup Screen
        this.parkingLotSetup();
    }


    // User Experience Methods - CLI Specific
    // Simulates clearing the console
    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }
    // Simulates proceeding on pressing enter
    public static void proceedEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    // User Interface Views ( Screens )
    // Welcome Screen
    public void welcomeScreen() {
        clearScreen();
        System.out.println("Welcome to the Parking Lot Management Application");
        System.out.println("Made by Antonio Hus, Group 924/1");
        System.out.println();
        System.out.println();
        System.out.println("Press 'Enter' to proceed");
        proceedEnter();
    }

    // Setup Screen
    public void parkingLotSetup() {

        // Get desired parking lot capacity
        Scanner scanner = new Scanner(System.in);
        int capacity = -1;

        while (true) {
            try {
                clearScreen();
                System.out.println("Let's setup the Parking Lot");
                System.out.println("Please enter capacity: ");

                // Get the input and parse it as an integer
                String input = scanner.nextLine();
                capacity = Integer.parseInt(input); // Attempt to convert input to an integer

                if (capacity <= 0) { // Check if capacity is positive
                    throw new NumberFormatException("Capacity should be a positive number. Please try again.");
                }

                break; // Exit the loop if valid integer is entered
            } catch (NumberFormatException exc) { // Handle invalid integer input
                System.out.println("Invalid input. Please enter a valid integer: " + exc);
                proceedEnter();
            }
        }

        // Initialize controller for requested parking lot
        this.controller = new ParkingController(capacity);
    }

    // Parking Lot Visual Interpretation
    public void parkingLotView() {
        clearScreen();

        // Parking Lot Information
        System.out.println("Parking lot status:");
        System.out.printf("%d out of %d lots are occupied", this.controller.getOccupied(), this.controller.getCapacity());
        System.out.println();

        // Parking Lot Visual Interpretation
        // Upper border
        for(int i=0; i<this.controller.getCapacity(); i+=1) { System.out.print("____"); }
        System.out.println("_");

        // Occupied Lots Visualization
        for(int i=0; i<this.controller.getCapacity(); i+=1) {
            if(this.controller.isOccupied(i)) System.out.print("| X ");
            else System.out.print("|   ");
        }
        System.out.println("|");
        System.out.println();
    }

    public int parkingLotOptions() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (true) {
            try {
                // Display User Options
                this.parkingLotView();
                System.out.println("1. Park a new Car");
                System.out.println("2. Park a new Bicycle");
                System.out.println("3. Park a new Motorcycle");
                System.out.println("4. Remove a vehicle");
                System.out.println("5. Get red vehicles");
                System.out.println("0. Exit");
                System.out.println();

                // Get user option
                System.out.print("Please choose an option: ");
                option = scanner.nextInt();

                // Redirect based on the option
                switch (option) {
                    case 1:
                        this.addVehicleView("Car");
                        break;
                    case 2:
                        this.addVehicleView("Bicycle");
                        break;
                    case 3:
                        this.addVehicleView("Motorcycle");
                        break;
                    case 4:
                        this.removeVehicleView();
                        break;
                    case 5:
                        this.getVehiclesByColorView("red");
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return 0; // Exit the menu
                    default:
                        throw new InputMismatchException("Invalid option. Please choose a number between 0 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input by consuming the entire line
                proceedEnter();
            }
        }
    }

    public void addVehicleView(String type) {

        // Display parking lot information
        this.parkingLotView();

        // Get new vehicle details
        Vehicle newVehicle = null;
        System.out.println("Please enter the color of the new vehicle");
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();

        // Create new vehicle of the given type and color
        newVehicle = switch (type) {
            case "Car" -> new Car(color);
            case "Bicycle" -> new Bicycle(color);
            case "Motorcycle" -> new Motorcycle(color);
            default -> newVehicle;
        };

        // Add new vehicle to the parking lot
        System.out.println("Choose where to park the vehicle:");
        int position = scanner.nextInt();
        try {
            this.controller.addVehicle(newVehicle, position);
            System.out.println("Vehicle was added successfully to position: " + position);
        } catch (ParkingException e) {
            System.out.println("There was an error: " + e);
        }

        proceedEnter();
    }

    public void removeVehicleView() {

        // Display parking lot information
        this.parkingLotView();

        // Get position information
        System.out.println("Choose where to remove the vehicle from:");
        Scanner scanner = new Scanner(System.in);
        int position = scanner.nextInt();

        // Remove vehicle from the parking lot
        try {
            this.controller.removeVehicle(position);
            System.out.println("Vehicle was successfully remove from position: " + position);
        } catch (ParkingException e) {
            System.out.println("There was an error: " + e);
        }

        proceedEnter();
    }

    public void getVehiclesByColorView(String color) {

        // Display parking lot information
        this.parkingLotView();

        // Display vehicles information
        System.out.println("The following vehicles are of color " + color + ":");
        for(Vehicle vehicle: this.controller.getVehiclesByColor(color)) {
            if(vehicle != null) {
                System.out.println(vehicle);
            }
        }

        proceedEnter();
    }
}
