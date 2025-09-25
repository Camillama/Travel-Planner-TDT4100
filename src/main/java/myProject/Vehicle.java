package myProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Vehicle implements Comparable<Vehicle> {

    private String departure;
    private String destination;
    private int capacity;
    private String vehicleType;

    public Vehicle(String departure, String destination, String vehicleType, int capacity) {
        this.departure = departure;
        this.destination = destination;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        int planeValue = 3;
        int carValue = 1;
        int boatValue = 2;
        int basePrice = 100;

        List<String> expensiveCities = Arrays.asList("London", "New York", "Los Angeles");
        List<String> cheapCities = Arrays.asList("Trondheim", "Oslo", "Bergen");

        switch (this.getVehicleType()) {
            case "Plane":
                basePrice *= planeValue;
                break;
            case "Car":
                basePrice *= carValue;
                break;
            case "Boat":
                basePrice *= boatValue;
                break;
            default:
                break;
        }

        if (expensiveCities.contains(this.getDeparture()) || expensiveCities.contains(this.getDestination()))
            basePrice *= 1.5;

        if (cheapCities.contains(this.getDeparture()) || cheapCities.contains(this.getDestination()))
            basePrice *= 0.8;

        if (this.getCapacity() < 10)
            basePrice *= 0.8;

        return basePrice;
    }

    public boolean isHalfCapacity(Vehicle bookThisVehicle) {
        return bookThisVehicle.getCapacity() <= bookThisVehicle.getCapacity() / 2;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return ((Integer) (this.capacity)).compareTo((Integer) (vehicle.capacity));
    }

    @Override
    public String toString() {
        return this.departure + "," + this.destination + "," + this.vehicleType + "," + this.capacity + "\n";
    }

    @Override
    public boolean equals(Object o) {
        Vehicle vehicle = (Vehicle) o;

        return (this.destination.equals(vehicle.destination) &&
                this.departure.equals(vehicle.departure) &&
                this.vehicleType.equals(vehicle.vehicleType) &&
                this.capacity == vehicle.getCapacity());
    }

    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("Oslo", "Trondheim", "Car", 10);
        Vehicle v2 = new Vehicle("Bergen", "Trondheim", "Car", 10);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(v1);
        vehicles.add(v2);
        System.out.println(vehicles);
        Collections.sort(vehicles);
        System.out.println(vehicles);

    }

}
