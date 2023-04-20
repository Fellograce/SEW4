package main;

import serial.BikeDB;

import java.sql.SQLException;
import java.sql.Statement;

public class TheMainDB {
    public static void main(String[] args) {

        try {
            BikeDB.getInstance().open();
            Statement statement = BikeDB.getInstance().getConnection().createStatement();

            statement.execute("DROP TABLE Bike");
            statement.execute("CREATE TABLE Bike(rahmennr varchar(30) primary key, marke varchar(30), farbe varchar(30))");

            statement.close();
            BikeDB.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
