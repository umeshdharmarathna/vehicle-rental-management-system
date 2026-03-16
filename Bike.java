package vehiclemanagementsystem;

public class Bike extends Vehicle {

    private int engineCapacityCC;

    // Parameterized constructor
    public Bike(String vehicleID, String brand, String model, double baseRatePerDay, boolean isAvailable, int engineCapacityCC) {
        super(vehicleID, brand, model, baseRatePerDay, isAvailable);
        this.engineCapacityCC = engineCapacityCC;
    }

    // Getter method
    public int getEngineCapacityCC() {
        return engineCapacityCC;
    }

    // Setter method
    public void setEngineCapacityCC(int engineCapacityCC) {
        this.engineCapacityCC = engineCapacityCC;
    }

    // Implement abstract method and calculate the rental cost for Bike
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (engineCapacityCC * 0.5 * days);
    }


    // Convert Bike data into a String format for saving to a file
    @Override
    public String toSaveData(){
        return "Bike, " + getVehicleID() +"," +
                getBrand() +"," +
                getModel() + "," +
                getBaseRatePerDay() +"," +
                isAvailable() + "," +
                getEngineCapacityCC();
    }

    // Display details
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Engine Capacity CC: " + engineCapacityCC);
    }

}
