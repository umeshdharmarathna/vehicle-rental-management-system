package vehiclemanagementsystem;

public abstract class Vehicle {
    private String vehicleID;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    //parameterized constructor
    public Vehicle(String vehicleID,String brand,String model,double baseRatePerDay,boolean isAvailable){
        this.vehicleID = vehicleID;
        this.brand=brand;
        this.model=model;
        this.baseRatePerDay=baseRatePerDay;
        this.isAvailable=isAvailable;
    }

    // Getter method
    public String getVehicleID(){
        return vehicleID;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public double getBaseRatePerDay (){
        return baseRatePerDay;
    }

    public boolean isAvailable(){
        return isAvailable;
    }


    //Setter method
    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setBaseRatePerDay(double baseRatePerDay){
        //Ensure Base rate greater than 0
        if (baseRatePerDay<=0){
            throw new IllegalArgumentException("Base rate per day should be greater than 0");
        }
        this.baseRatePerDay = baseRatePerDay;

    }

    public void setAvailable(boolean available){
        isAvailable = available;
    }

    //Display vehicle details
    public void displayDetails(){
        System.out.println("Vehicle ID: " + vehicleID);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Base rate per day: " + baseRatePerDay);
        System.out.println("Available: " + isAvailable);
    }

    //Rent vehicle if available
    public void rentVehicle(){
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Vehicle Rent Successful");
        }
        else {
            System.out.println("Vehicle is not available at the moment");
        }
    }
    // Return Vehicle and make it available again
    public void returnVehicle() {
        isAvailable = true;
        System.out.println("Vehicle returned successfully.");
    }


    //  Calculates the rental cost based on number of days.
    public abstract double calculateRentalCost(int days);

    // abstract method to Convert vehicle data into string format
    public abstract String toSaveData();
}












