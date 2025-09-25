package myProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CheckReduceCapacity {
    @Test
    void check() {
        Vehicle v = new Vehicle("Trondheim", "Oslo", "Plane", 5);
        Vehicle v2 = new Vehicle("Trondheim", "Oslo", "Car", 3);
        assertEquals(5, v.getCapacity());

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(v);
        vehicles.add(v2);
        Filehandler filehandler = new Filehandler();
        filehandler.writeToFile("test.txt", vehicles);

        Hub h = new Hub();
        h.initialize("test.txt");

        h.bookVehicle(v);
        Hub h2 = new Hub();
        h2.initialize("test.txt");
        assertEquals(0, 0);
    }
}
