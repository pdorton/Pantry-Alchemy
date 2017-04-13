//package com.thealchemists.pantryalchemy;

import java.sql.*;

public class MySQLConnection {

    String url = "jdbc:mysql://162.243.207.144:3306/pantry_database?useSSL=false";
    String userName = "admin";
    String password = "Pantry1!";
    Connection conn = null;
    
    
    public Connection startConnection() throws SQLException{
			return conn = DriverManager.getConnection(url, userName, password);
    }
    
    public void CloseConnection() throws SQLException{
		conn.close();
	}
}
