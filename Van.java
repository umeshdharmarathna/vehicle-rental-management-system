package vehiclemanagementsystem;

public class Van extends Vehicle {
    private double cargoCapacityKg;

    // parameterized constructor
    public Van (String vehicleID,String brand,String model,double baseRatePerDay,boolean isAvailable,double cargoCapacityKg) {
        super(vehicleID, brand, model, baseRatePerDay, isAvailable);
        this.cargoCapacityKg = cargoCapacityKg;
    }

    // getter method
    public double getCargoCapacityKg() {
        return cargoCapacityKg;
    }

    // setter method
    public void setCargoCapacityKg(double cargoCapacityKg) {
        this.cargoCapacityKg = cargoCapacityKg;
    }

    // Implement abstract method and calculate the rental cost for Van
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (cargoCapacityKg * 0.2 * days);
    }

    // Convert Van data into a String format for saving to a file
    @Override
    public String toSaveData(){
        return "Van, " + getVehicleID() +","+
                getBrand()+","+
                getModel()+","+
                getBaseRatePerDay()+","+
                isAvailable()+","+
                getCargoCapacityKg();
    }

    // Display details
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Cargo Capacity Kg: " + cargoCapacityKg);
    }


}
