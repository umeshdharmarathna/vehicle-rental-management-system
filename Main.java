package vehiclemanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.PrintWriter;


public class Main {
    /** stores all vehicles in the system
     *  and keep track of  total rental income
     */
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static double totalRentalIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load data when the programme start
        loadDataFromFile();

        int option = 0 ;

        // Display menu list
        do {
            System.out.println("\n--- MENU OPTIONS ---\n");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Search Vehicle by ID");
            System.out.println("6. View Total Rental Income");
            System.out.println("7. Filter Vehicles by Type");
            System.out.println("8. Remove Vehicle by ID ");
            System.out.println("9. Exit");
            System.out.println("------------------------");
            System.out.println();
            System.out.print("Enter your option:  ");

            // Handle invalid menu input
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number  within range 1-9.");
                sc.nextLine();
                continue;
            }

            // Menu control using Switch

            switch (option) {
                case 1:
                    addVehicle(sc);
                    saveDataToFile(false);
                    break;
                case 2:
                    viewAllVehicles();
                    break;
                case 3:
                    rentVehicle(sc);
                    saveDataToFile(false);
                    break;
                case 4:
                    returnVehicle(sc);
                    saveDataToFile(false);
                    break;
                case 5:
                    searchVehicleByID(sc);
                    break;
                case 6:
                    System.out.println("Total Rental Income: " + totalRentalIncome);
                    break;
                case 7:
                    filterVehicleByType(sc);
                    break;
                case 8:
                    removeVehicle(sc);
                    saveDataToFile(false);
                    break;
                case 9:
                    saveDataToFile(true);
                    System.out.println("Exiting... \nGoodbye & safe ride !");
                    break;
                default: System.out.println("Invalid option! Choose 1-9.");
            }

        } while (option != 9);

        sc.close();
    }

    // Option 01 : Add a new vehicle to the System
    public static void addVehicle(Scanner sc) {
        System.out.print("Choose Vehicle Type: 1.Car 2.Bike 3.Van : ");

        int type;
        try {
            type = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter 1, 2, or 3.");
            sc.nextLine();
            return;
        }

        System.out.print("Enter Vehicle ID : ");
        String id = sc.nextLine();

        // Prevent Duplicate Vehicle IDs
        if (findVehicleByID(id) != null) {
            System.out.println("Vehicle ID already exists! Can not proceed ");
            return;
        }

        System.out.print("Enter Brand : ");
        String brand = sc.nextLine();
        System.out.print("Enter Model : ");
        String model = sc.nextLine();

        double rate;
        try {
            System.out.print("Enter Base Rate per Day: ");
            rate = sc.nextDouble();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid rate! Rate Must be a number.");
            sc.nextLine(); return;
        }

        boolean isAvailable = true;

        // Create vehicle object based on type
        switch(type) {
            case 1:
                int seats;
                try {
                    System.out.print("Enter number of seats: ");
                    seats = sc.nextInt();
                    sc.nextLine();
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter a numerical value ");
                    sc.nextLine(); return;
                }
                vehicles.add(new Car(id, brand, model, rate, isAvailable, seats));
                break;
            case 2:
                int cc;
                try {
                    System.out.print("Enter Engine CC: ");
                    cc = sc.nextInt();
                    sc.nextLine();
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid Input ! Please enter numerical value only");
                    sc.nextLine();
                    return;
                }
                vehicles.add(new Bike(id, brand, model, rate, isAvailable, cc));
                break;
            case 3:
                double cargo;
                try {
                    System.out.print("Enter Cargo Kg: ");
                    cargo = sc.nextDouble();
                    sc.nextLine(); }
                catch (InputMismatchException e) {
                    System.out.println("Invalid value! Please enter Numerical value only");
                    sc.nextLine(); return;
                }
                vehicles.add(new Van(id, brand, model, rate, isAvailable, cargo));
                break;
            default:
                System.out.println("Invalid vehicle type !");
                return;
        }

        System.out.println("Vehicle added successfully!");
    }

    //  Option 02 : View all vehicle details
    public static void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("--------------------------");
        }
        System.out.println("___ALL DISPLAYED___");
    }

    // Option 03 : Rent Vehicle
    public static void rentVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to rent : ");
        String id = sc.nextLine();
        Vehicle vehicle = findVehicleByID(id);
        if (vehicle == null) {
            System.out.println("Vehicle not found! or vehicle ID do not exist");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle already rented!");
            return;
        }

        int days;
        try {
            System.out.print("Enter number of days: ");
            days = sc.nextInt();
            sc.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid number !Please enter numerical value only .");
            sc.nextLine();
            return;
        }

        if (days <= 0) {
            System.out.println("Days must be greater than 0 .");
            return;
        }

        vehicle.rentVehicle();
        double cost = vehicle.calculateRentalCost(days);
        totalRentalIncome += cost;
        System.out.println("Total cost:  " + cost);
    }

    // Option 04 : Return Vehicle
    public static void returnVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to return : ");
        String id = sc.nextLine();
        Vehicle v = findVehicleByID(id);
        if (v == null) {
            System.out.println("Vehicle not found! Or vehicle ID do not exist ");
            return;
        }
        if (v.isAvailable()) {
            System.out.println("Vehicle was not rented.");
            return; }
        v.returnVehicle();
    }

    // Option 05 : Search Vehicle by ID
    public static void searchVehicleByID(Scanner sc) {
        System.out.print("Enter Vehicle ID to search: ");
        String id = sc.nextLine();
        Vehicle vehicle = findVehicleByID(id);
        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return; }
        vehicle.displayDetails();
    }

    // Option 06 : Filter by Type
    public static void filterVehicleByType(Scanner sc) {
        System.out.print("Filter Type: 1.Car 2.Bike 3.Van : ");
        int type;
        try {
            type = sc.nextInt();
            sc.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input ! Choose only 1 , 2 or 3 only ");
            sc.nextLine();
            return;
        }

        boolean found = false;
        for (Vehicle v : vehicles) {
            if ((type==1 && v instanceof Car) || (type==2 && v instanceof Bike) || (type==3 && v instanceof Van)) {
                v.displayDetails();
                System.out.println("--------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles of this type found.");
        }
    }

    // Option 07 : Find Vehicle by ID
    public static Vehicle findVehicleByID(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleID().equals(id))
                return v;
        }
        return null;
    }

    // Remove vehicle from the system
    public static void removeVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to remove :  ");
        String id = sc.nextLine();

        Vehicle vehicle = findVehicleByID(id);

        if (vehicle == null) {
            System.out.println("Vehicle ID doesn't exist!");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Cannot remove, Vehicle is currently rented. ");
            return;
        }
        vehicles.remove(vehicle);
        System.out.println("Vehicle removed successfully!");
    }

    // task 5.1
    // file Handling------------

    /**
     * load data from the text file
     */
    public static void loadDataFromFile() {
        try {
            File file = new File("vehicles.txt");

            if (!file.exists()) {
                System.out.println("File not found. Starting fresh.");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equals("TotalIncome")) {
                    totalRentalIncome = Double.parseDouble(parts[1]);
                }
                else {
                    String type = parts[0];
                    String id = parts[1];
                    String brand = parts[2];
                    String model = parts[3];
                    double rate = Double.parseDouble(parts[4]);
                    boolean available = Boolean.parseBoolean(parts[5]);

                    if (type.equals("Car")) {
                        int seats = Integer.parseInt(parts[6]);
                        vehicles.add(new Car(id, brand, model, rate, available, seats));
                    }
                    else if (type.equals("Bike")) {
                        int cc = Integer.parseInt(parts[6]);
                        vehicles.add(new Bike(id, brand, model, rate, available, cc));
                    }
                    else if (type.equals("Van")) {
                        double cargo = Double.parseDouble(parts[6]);
                        vehicles.add(new Van(id, brand, model, rate, available, cargo));
                    }
                }
            }

            fileScanner.close();
            System.out.println("Data Loaded Successfully!");

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    /**
     * save vehicle data to file
     */
    public static void saveDataToFile(boolean showMessage) {
        try {
            PrintWriter writer = new PrintWriter("vehicles.txt");

            for (Vehicle vehicle : vehicles) {
                writer.println(vehicle.toSaveData());
            }

            writer.println("TotalIncome," + totalRentalIncome);
            writer.close();

            if (showMessage) {
                System.out.println("Data Saved Successfully !");
            }

        } catch (Exception e) {
            System.out.println("Error saving file.Try again");
        }
    }

}

