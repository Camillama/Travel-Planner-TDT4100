package myProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Trip Booker");
        System.out.println(getClass().getResource("Booking.fxml"));
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Booking.fxml"))));
        primaryStage.show();
    }

}
