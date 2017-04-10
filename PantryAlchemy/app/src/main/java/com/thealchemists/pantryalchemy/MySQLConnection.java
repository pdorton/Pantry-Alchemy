//package com.thealchemists.pantryalchemy;

import java.sql.*;

public class MySQLConnection {

    private static void request() {

        String url = "jdbc:mysql://162.243.207.144:3306/pantry_database";
        String userName = "user";
        String password = "password";
        String query = "select name from pantry_database.ingredients;";
        Connection conn = null;

        try {
            /* Initializing the connection */
            conn = DriverManager.getConnection(url, userName, password);

            Statement statement = conn.createStatement();

            ResultSet resultset = statement.executeQuery(query);

            int columnIndex = 0;

            while(resultset.next() && columnIndex <= resultset.getFetchSize()){
                System.out.println(resultset.getString(columnIndex));       
            }
        }
        
        catch(SQLException e){
            System.out.println("SQL connection error: " + e.getMessage());
        }
        
        finally {
            if(conn != null) {
                try {
                    /* CLosing connection */
                    conn.close();
                }
                
                catch (SQLException e) {
                    System.out.println("Error while closing the connection: " + e.getMessage());
                }
            }
        }
    }
    public static void main(String[] args) {
        // Loading the MySQL Connector/J driver
        try { 
            Class.forName("com.mysql.jdbc.Driver");
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Error while loading the Driver: " + e.getMessage());
        }

        request();
    }
}