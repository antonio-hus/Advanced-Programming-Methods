package Model;

public class Bicycle implements Vehicle {
    private String color;
    private int numberOfWheels;
    private boolean isMotorized;

    public Bicycle(String color) {
        this.color = color;
        this.numberOfWheels = 2;
        this.isMotorized = false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public boolean isMotorized() {
        return isMotorized;
    }

    @Override
    public String toString() { return "Bicycle (" + color + ")"; }
}
