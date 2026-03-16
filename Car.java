package vehiclemanagementsystem;

public class Car extends Vehicle {

    private int numberOfSeats;

    // Parameterized constructor
    public Car(String vehicleId, String brand, String model,
               double baseRatePerDay, boolean isAvailable,
               int numberOfSeats) {

        super(vehicleId, brand, model, baseRatePerDay, isAvailable);
        this.numberOfSeats = numberOfSeats;
    }

    // Getter
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // Setter
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    // Implement abstract method and calculate the rental cost for car
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (numberOfSeats * 200 * days);
    }

    // Convert Car data into a String format for saving to a file
    @Override
    public String toSaveData(){
        return "Car," + getVehicleID() + "," +
                getBrand() + "," +
                getModel() + "," +
                getBaseRatePerDay() + "," +
                isAvailable() +  "," +
                getNumberOfSeats();
    }

    //  display Details
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of Seats: " + numberOfSeats);
    }

}




