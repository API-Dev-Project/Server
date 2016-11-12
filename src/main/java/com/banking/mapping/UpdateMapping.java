package com.banking.mapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by graham on 12/11/16.
 */
public class UpdateMapping {

    public void updateAccountBalance(int accountId, int balance) {
        PersistenceManager database = new PersistenceManager();
        String query = StatementMapping.UPDATE_ACCOUNT_BALANCE;

        PreparedStatement statement = database.prepareStatement(query);
        try {
            statement.setDouble(1, balance);
            statement.setInt(2, accountId);

            database.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
