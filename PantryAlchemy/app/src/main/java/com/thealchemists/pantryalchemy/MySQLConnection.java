package com.thealchemists.pantryalchemy;

import java.sql.*;

public class MySQLConnection {

    String url = "jdbc:mysql://myadress:3306/mydatabase";
    String userName = "user";
    String password = "password";

    // used to establish the connection with the specified url, username and password
    public static Connection getConnection(String url,String userName,String password) {
        Connection con = null;
        return test;
    }

    // creates a statement object that can be used to execute SQL queries
    public Statement createStatement() {
        Statement test = null;
        return test;
    }

    public void close() {
        return;
    }

    private static void request(){

        try {
            /* Initializing the connection */
            conn = DriverManager.getConnection(url, user, passwd);

            Statement statement = conn.createStatement();

            ResultSet resultset = statement.executeQuery(/* MY SQL reqquest */);

            while(resultset.next()){
                System.out.println(resultset.getString(/* THE COLUMN AND ROW I WANTED IN MY REQUEST */));       
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