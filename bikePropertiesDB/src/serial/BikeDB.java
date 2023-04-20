package serial;

import model.Bike;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Objekt-Katalog. Dient der Persistierung von Objekten.
 */
public class BikeDB {
    private static BikeDB instance;
    private Connection connection;

    public static BikeDB getInstance() {
        if (instance == null) {
            instance = new BikeDB();
        }
        return instance;
    }

    public void open() {
        String url;
        String username;
        String password;

        try (FileInputStream in = new FileInputStream("dbconnect.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            if (url == null || username == null || password == null) {
                throw new Exception("URL, username or password is missing");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }


    /**
     * Speichert ein Objekt im Katalog. Existiert bereits ein gleiches (!) im Catalog, so wird das existierende zuerst
     * entfernt.
     *
     * @param bike zu speicherndes Objekt
     */
    public void save(Bike bike) {
        // In der Liste aller Objekte updaten oder inserten
        try (Statement statement = BikeDB.getInstance().getConnection().createStatement()) {
            statement.executeQuery("DELETE FROM Bike WHERE rahmennr = '" + bike.getRahmennr() + "'");
            statement.execute("INSERT INTO Bike (rahmennr, marke, farbe) values (" + "'" + bike.getRahmennr() + "', " + "'" +
                    bike.getMarkeType() + "'," + "'" + bike.getFarbe() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bike selectBikeByRahmennr(String rahmennr) {
        Bike found = null;

        try (Statement statement = BikeDB.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT rahmennr, marke, farbe FROM Bike " +
                    "WHERE rahmennr = '" + rahmennr + "'");

            while (resultSet.next()) {
                found = new Bike(resultSet.getString("rahmennr"), resultSet.getString("marke"),
                        resultSet.getString("farbe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;
    }
}
