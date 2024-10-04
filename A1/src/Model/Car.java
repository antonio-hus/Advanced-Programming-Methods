package Model;

public class Car implements Vehicle {
    private String color;
    private int numberOfWheels;
    private boolean isMotorized;

    public Car(String color) {
        this.color = color;
        this.numberOfWheels = 4;
        this.isMotorized = true;
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
}
