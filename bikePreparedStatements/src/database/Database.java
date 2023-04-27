package database;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Singleton zum Zugriff auf eine JDBC-Datenbank. Verbindungsdetails befinden sich in der Datei dbconnect.properties in
 * den Props
 * <ul>
 *   <li>driver</li>
 *   <li>url</li>
 *   <li>username</li>
 *   <li>password</li>
 * </ul>
 */
public class Database {
    private static Database instance;
    private Connection connection;
    private Statement statement;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement selectStatement;
    private String url;
    private String username;
    private String password;

    /**
     * Privater Konstruktor, da es sich um ein Singleton handelt und die einzige Instanz nur von der Klasse selbst
     * erstellt und verwaltet wird.
     */
    private Database() {
        // DB-Properties laden
        try (FileInputStream in = new FileInputStream("dbconnect.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            // Alles da?
            if (url == null || username == null || password == null) {
                throw new Exception("Fehler! Property File muss url, username, password enthalten!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Verbindung erstellen
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            insertStatement = connection.prepareStatement("INSERT INTO Bike (rahmennr, markeType, text) values (?,?,?)");
            updateStatement = connection.prepareStatement("UPDATE Bike SET markeType = ?, text = ? WHERE rahmennr = ?");
            selectStatement = connection.prepareStatement("SELECT rahmennr, markeType, text FROM Bike WHERE rahmennr = ?");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    /**
     * Liefert die einzige Instanz.
     *
     * @return Instanz
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Öffnen der Datenbank.
     *
     * @throws SQLException
     */
    public static void open() throws SQLException {
        instance = new Database();
    }

    /**
     * Schließen der Datenbank
     */
    public static void close() {
        try {
            getInstance().connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    /**
     * Getter für Connection.
     *
     * @return Connection.
     */
    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getInsertStatement() {
        return insertStatement;
    }

    public PreparedStatement getUpdateStatement() {
        return updateStatement;
    }

    public PreparedStatement getSelectStatement() {
        return selectStatement;
    }

    public Statement getStatement() {
        return statement;
    }
}
