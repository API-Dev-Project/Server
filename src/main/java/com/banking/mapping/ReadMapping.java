package com.banking.mapping;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Graham Murray on 10/11/16.
 */
public class ReadMapping {

    public Customer getCustomerById(int id, boolean includeAccounts) {
        Customer customer  = new Customer();
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_CUSTOMER_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));

                if (includeAccounts) {
                    customer.setAccounts(getAccounts(customer.getId(), customer));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private List<Account> getAccounts(int customerId, Customer owner) {
        List<Account> accounts = new ArrayList<>();
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_ACCOUNT_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Account account  = new Account();
                account.setId(rs.getInt("id"));
                account.setOwner(owner);
                account.setAccountNumber(rs.getInt("accountNumber"));
                account.setSortCode(rs.getInt("sortCode"));
                account.setBalance(rs.getDouble("balance"));
                account.setTransactions(getTransactions(account.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private List<Transaction> getTransactions(int acccountId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_TRANSACTION_BY_ACCOUNT_ID);
            statement.setInt(1, acccountId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setId(rs.getInt("id"));
                transaction.setAccountId(rs.getInt("accountId"));
                transaction.setType(rs.getString("type"));
                transaction.setTimestamp(rs.getTimestamp("date").toString());
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setPostBalance(rs.getDouble("balance"));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public int getCustomerByID(String email) {
        int id = 0;

        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_CUSTOMER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean doesCustomerExist(String email) {
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_CUSTOMER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            int rowCount = 0;
            if (rs.last()) {
                rowCount = rs.getRow();
                return rowCount > 0 ? true:false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean doesAccountExist(int accountNumber) {
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_ACCOUNT_BY_ACCNUM);
            statement.setInt(1, accountNumber);
            ResultSet rs = statement.executeQuery();

            int rowCount = 0;
            if (rs.last()) {
                rowCount = rs.getRow();
                return rowCount > 0 ? true:false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}