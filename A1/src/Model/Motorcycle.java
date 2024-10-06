package Model;

public class Motorcycle implements Vehicle
{
    private String color;
    private int numberOfWheels;
    private boolean isMotorized;

    public Motorcycle(String color) {
        this.color = color;
        this.numberOfWheels = 2;
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

    @Override
    public String toString() { return "Motorcycle (" + color + ")"; }

}
