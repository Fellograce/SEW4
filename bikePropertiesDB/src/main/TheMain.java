package main;

import controllerview.BikeC;
import javafx.application.Application;
import javafx.stage.Stage;
import serial.BikeDB;

import java.sql.SQLException;
import java.sql.Statement;

public class TheMain extends Application {
    @Override
    public void init() {
        BikeDB.getInstance().open();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BikeC.show(primaryStage);
    }

    @Override
    public void stop() {
        BikeDB.getInstance().close();
    }
}
