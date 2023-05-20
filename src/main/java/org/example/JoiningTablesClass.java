package org.example;

import java.sql.*;
import java.util.Comparator;

/**
 * This is a class that list the Joined tables.
 */

public class JoiningTablesClass {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    static final String USER = "postgres";
    static final String PASS = "ROOT";

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sqlJoin = "SELECT accommodation.id, accommodation.type, \n" +
                    " room_fair.value, room_fair.season\n" +
                    "FROM accommodation\n" +
                    "JOIN accommodation_room_fair_relation \n" +
                    "ON accommodation.id = \n" +
                    "accommodation_room_fair_relation.accommodation_id \n" +
                    "JOIN room_fair\n" +
                    "ON accommodation_room_fair_relation.accommodation_id = room_fair.id;\n" +
                    "\n" +
                    "\n";
            ResultSet resultSetJoin = stmt.executeQuery(sqlJoin);
            while (resultSetJoin.next()) {
                int id = resultSetJoin.getInt("id");
                String type = resultSetJoin.getString("type");
                Double value = resultSetJoin.getDouble("value");
                String season = resultSetJoin.getString("season");

                System.out.println("The id is: " + id);
                System.out.println("The type is: " + type);
                System.out.println("The value is: " + value);
                System.out.println("The season: " + season);
                System.out.println();
            }
            resultSetJoin.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

