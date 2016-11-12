package com.banking.mapping;

/**
 * Created by Graham Murray on 10/11/16.
 */
public class StatementMapping {

    /* Statements to Write */
    public static final String ADD_CUSTOMER = "INSERT INTO Customer(firstname, surname, email, address, username, password) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String ADD_ACCOUNT = "INSERT INTO Account(accountNumber, sortCode, balance, fk_owner) VALUES (?, ?, ?, ?)";
    public static final String ADD_TRANSACTION = "INSERT INTO Transaction(type, date, amount, balance, accountId) VALUES(?, ?, ?, ?, ?)";

    /* Statements to Read */
    public static final String GET_CUSTOMER_ID = "select * from Customer where id=?";
    public static final String GET_ACCOUNT_BY_CUSTOMER_ID  = "SELECT * FROM Account WHERE ownerId = ?";
    public static final String GET_TRANACTION_BY_ACCOUNT_ID  = "SELECT * FROM Transaction WHERE accountId = ?";

    /* Statements to Update */
    public static final String UPDATE_ACCOUNT_BALANCE = "UPDATE Account SET balance = ? WHERE id = ?";
}
