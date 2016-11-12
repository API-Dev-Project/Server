package com.banking.persistence;

import java.sql.*;

/**
 * Created by Graham Murray on 10/11/16.
 */
public class Database {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/bank-api";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pa55w0rd!";

    private Connection connection;

    public Database() {
        createConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void execute(PreparedStatement statement) throws SQLException {
        statement.execute();
        closeConnection();
    }

    private void createConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
