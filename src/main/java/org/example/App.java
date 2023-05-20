package org.example;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the main class. It contains a prepareStatement that inserts a value into a table
 * and creates accommodation objects.
 */
public class App {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    static final String USER = "postgres";
    static final String PASS = "ROOT";

    public static void main(String[] args) {


        Connection conn = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            preparedStatement = conn.prepareStatement
                    ("insert into room_fair(id,value,season) values(?,?,?)");
            preparedStatement.setInt(1, 9);
            preparedStatement.setInt(2, 109);
            preparedStatement.setString(3, "winter");
            preparedStatement.executeUpdate();

            String sql = "SELECT * FROM accommodation;";


            ResultSet resultSet = stmt.executeQuery(sql);

            List<Accommodation> accommodationsList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String bed_type = resultSet.getString("bed_type");
                int max_guests = resultSet.getInt("max_guests");
                String description = resultSet.getString("description");

                Accommodation accommodation = new Accommodation(
                        id, type, bed_type, max_guests, description);

                accommodationsList.add(accommodation);
            }
            for (Accommodation accommodation : accommodationsList) {
                System.out.println("ID: " + accommodation.getId() +
                        ", type: " + accommodation.getType() + ", bed_type: "
                        + accommodation.getBed_type()
                        + "max_guests: " + accommodation.getMax_guests() + "description" +
                        accommodation.getDescription());

            }


                resultSet.close();
                stmt.close();
                conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally{
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


