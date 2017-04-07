package com.thealchemists.pantryalchemy;

import java.sql.*;

public class MySQLConnection {

    // used to establish the connection with the specified url, username and password
    public static Connection getConnection(String url,String userName,String password) {
        Connection test = null;
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
}