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

    public Customer getCustomerById(int id) {
        Customer customer  = new Customer();
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_CUSTOMER_ID);
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
                customer.setAccounts(getAccounts(customer.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private List<Account> getAccounts(int customerId) {
        List<Account> accounts = new ArrayList<>();
        try {
            PersistenceManager database = new PersistenceManager();
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_ACCOUNT_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Account account  = new Account();
                account.setId(rs.getInt("id"));
                account.setAccountNumber(rs.getLong("accountNumber"));
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
            PreparedStatement statement = database.prepareStatement(StatementMapping.GET_TRANACTION_BY_ACCOUNT_ID);
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
}