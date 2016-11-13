package com.banking.mapping;

import com.banking.Configuration;

import java.sql.*;

/**
 * Created by Graham Murray on 10/11/16.
 */
public class PersistenceManager {

    private Configuration config;
    private Connection connection;

    public PersistenceManager() {
        config = new Configuration();
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
        if (!config.isTesting()) {
            statement.execute();
            closeConnection();
        }
    }

    private void createConnection() {
        try {
            Class.forName(config.getDbDriver());
            connection = DriverManager.getConnection(config.getDbConnection(), config.getDbUser(), config.getDbPassword());
        } catch( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
