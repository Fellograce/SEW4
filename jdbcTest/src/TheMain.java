import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TheMain {
    public static void main(String[] args) {
        String driver;
        String url;
        String username;
        String password;

        try (FileInputStream in = new FileInputStream("dbconnect.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();) {
            //Create table
            String create = "CREATE TABLE IF NOT EXISTS Person(id int identity primary key, name varchar(50))";
            statement.executeUpdate(create);

            //Insert data into DB
            String insert = "INSERT INTO Person(name) VALUES ('Jeff', 'Christian', 'Alex')";
            statement.executeUpdate(insert);

            //OUTPUT data
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
