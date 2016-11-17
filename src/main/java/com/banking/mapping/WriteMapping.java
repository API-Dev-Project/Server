package com.banking.mapping;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Graham on 10/11/16.
 */
public class WriteMapping {

    public void addCustomer(Customer customer) {
        PersistenceManager database = getDatabase();
        String query = StatementMapping.ADD_CUSTOMER;

        PreparedStatement statement = database.prepareStatement(query);
        try {
            statement.setString(1, customer.getFirstname());
            statement.setString(2, customer.getSurname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getUsername());
            statement.setString(6, customer.getPassword());

            database.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account, int customerId) {
        PersistenceManager database = getDatabase();
        String query = StatementMapping.ADD_ACCOUNT;

        PreparedStatement statement = database.prepareStatement(query);
        try {
            statement.setLong(1, account.getAccountNumber());
            statement.setInt(2, account.getSortCode());
            statement.setDouble(3, account.getBalance());
            statement.setInt(4, customerId);

            database.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(Transaction transaction) {
        PersistenceManager database = getDatabase();
        String query = StatementMapping.ADD_TRANSACTION;

        PreparedStatement statement = database.prepareStatement(query);
        try {
            statement.setString(1, String.valueOf(transaction.getType()));
            statement.setTimestamp(2, Timestamp.valueOf(transaction.getTimestamp()));
            statement.setDouble(3, transaction.getAmount());
            statement.setDouble(4, transaction.getPostBalance());
            statement.setInt(5, transaction.getAccountId());

            database.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PersistenceManager getDatabase() {
        return new PersistenceManager();
    }
}
