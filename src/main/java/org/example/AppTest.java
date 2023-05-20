package org.example;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Comparator;

/**
 * This is a class that tests if the room , prices and price are listed.
 */

class AppTest {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    static final String USER = "postgres";
    static final String PASS = "ROOT";
    @Test
    public void testPrintRoomPrices() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM room_fair")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double value = resultSet.getDouble("value");
                String season = resultSet.getString("season");
                System.out.println("Room " + id + " price in season " + season + ": " + value);
            }
        }
    }

}