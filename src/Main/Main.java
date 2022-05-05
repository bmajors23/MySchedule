package Main;

import Helper.AppointmentsQuery;
import Helper.CustomersQuery;
import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        primaryStage.setTitle("MySchedule");
        primaryStage.setScene(new Scene(root, 387, 300));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();


    }
}
