package myProject;

import java.util.ArrayList;
import java.util.List;

public class Hub {
    private List<Vehicle> allVehicles = new ArrayList<>();

    public void initialize(String fileName) {
        for (List<String> line : new Filehandler().loadFile(fileName)) {
            allVehicles.add(new Vehicle(line.get(0), line.get(1), line.get(2), Integer.parseInt(line.get(3))));
        }
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> tempAllAirplanes;
        tempAllAirplanes = allVehicles;
        return tempAllAirplanes;
    }

    public List<String> getRelevantVehicles(String departure, String destination, List<String> vehicleType) {
        List<String> relevantRoutes = new ArrayList<>();

        if (departure.equals(destination) && !departure.trim().equals("") && !departure.trim().equals(""))
            throw new IllegalArgumentException(
                    "Departure and destination are equal.\nPlease write two different cities");
        else if (departure.trim().equals("") && destination.trim().equals(""))
            throw new IllegalArgumentException("Please fill in at least one of the fields");

        for (Vehicle vehicle : allVehicles) {
            if (departure.toUpperCase().trim().equals(vehicle.getDeparture().toUpperCase().trim())
                    && destination.toUpperCase().trim().equals(vehicle.getDestination().toUpperCase().trim())
                    && vehicleType.contains(vehicle.getVehicleType())) {
                relevantRoutes.add(vehicle.getDeparture() + " - " + vehicle.getDestination() + " || Vehicle Type: "
                        + vehicle.getVehicleType() + " || Capacity: " + vehicle.getCapacity());
            }

            else if (departure.toUpperCase().trim().equals(vehicle.getDeparture().toUpperCase().trim())
                    && destination.equals("") && vehicleType.contains(vehicle.getVehicleType())) {
                relevantRoutes.add(vehicle.getDeparture() + " - " + vehicle.getDestination() + " || Vehicle Type: "
                        + vehicle.getVehicleType() + " || Capacity: " + vehicle.getCapacity());
            }

            else if (destination.toUpperCase().trim().equals(vehicle.getDestination().toUpperCase().trim())
                    && departure.equals("") && vehicleType.contains(vehicle.getVehicleType())) {
                relevantRoutes.add(vehicle.getDeparture() + " - " + vehicle.getDestination() + " || Vehicle Type: "
                        + vehicle.getVehicleType() + " || Capacity: " + vehicle.getCapacity());
            }
        }

        if (relevantRoutes.isEmpty()) {
            // relevantRoutes.add("No options found");
            throw new IllegalStateException("Trip not found.\nPlease try different cities");
        }

        return relevantRoutes;

    }

    public void bookVehicle(Vehicle bookThisVehicle) {
        if (bookThisVehicle.getCapacity() - 1 <= 0) {
            allVehicles.remove(bookThisVehicle);
        } else {
            allVehicles.set(allVehicles.indexOf(bookThisVehicle),
                    new Vehicle(bookThisVehicle.getDeparture(), bookThisVehicle.getDestination(),
                            bookThisVehicle.getVehicleType(), bookThisVehicle.getCapacity() - 1));
        }
        Filehandler filehandler = new Filehandler();
        filehandler.writeToFile("trips.txt", allVehicles);
        new Hub().initialize("trips.txt");
    }

    public static void main(String[] args) {
        Hub hub = new Hub();
        hub.initialize("trips.txt");
    }

}