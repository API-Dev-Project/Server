package com.banking.persistence;

/**
 * Created by Graham Murray on 10/11/16.
 */
public class Statement {

    /* Statements to Write */
    public static final String ADD_CUSTOMER = "INSERT INTO Customer(firstname, surname, email, address, username, password) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String ADD_ACCOUNT = "INSERT INTO Account(accountNumber, sortCode, balance, fk_owner) VALUES (?, ?, ?, ?)";
    public static final String ADD_TRANSACTION = "INSERT INTO Transaction(type, date, amount, balance, accountId) VALUES(?, ?, ?, ?, ?)";

    /* Statements to Read */
    public static final String GET_CUSTOMER_ID = "select * from Customer where id=?";
    public static final String GET_ACCOUNT_BY_CUSTOMER_ID  = "SELECT * FROM Account WHERE ownerId = ?";
    public static final String GET_TRANACTION_BY_ACCOUNT_ID  = "SELECT * FROM Transaction WHERE accountId = ?";
    public static final String GET_ALL_BY_CUSTOMER_ID = "SELECT Customer.*, Account.*, Transaction.* FROM Customer INNER JOIN Account ON Account.ownerId = Customer.id INNER JOIN Transaction ON Account.id = Transaction.accountId WHERE Customer.id = ?";

    /* Statements to Update */
    public static final String UPDATE_ACCOUNT_BALANCE = "";
}
