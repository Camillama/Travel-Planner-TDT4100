package myProject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Filehandler {
    private final String PATH = new File("").getAbsolutePath() + "/src/main/resources/myProject/saves/";

    public List<List<String>> loadFile(String filename) {
        File file = new File(PATH + filename);

        try {
            Scanner scanner = new Scanner(file);
            List<List<String>> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(Arrays.asList(line.split(",")));
            }

            scanner.close();

            return lines;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void writeToFile(String filename, List<Vehicle> vehicles) {
        File file = new File(PATH + filename);
        String text = "";

        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);

            for (Vehicle vehicle : vehicles) {
                text = text + vehicle.toString();
            }

            fileWriter.write(text.trim());

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        Filehandler f1 = new Filehandler();
        f1.loadFile("trips.txt");

    }
}
