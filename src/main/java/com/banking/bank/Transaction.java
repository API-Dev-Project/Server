package com.banking.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    enum Type {
        DEBIT("debit"),
        CREDIT("credit");

        private String type;

        Type(String type) {
            this.type = type;
        }
    }

    private Type type;
    private String timestamp;
    private double amount;
    private double postBalance;
    private int accountId;
    private int id;

    public Transaction() {
        timestamp = new String();
        amount = 0.0;
        postBalance = 0.0;
        accountId = 0;
    }

    public Transaction(Type type, double amount, int accountId) {
        this.type = type;
        this.amount = amount;

        setTimestamp();
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public Type getType() {
        return type;
    }

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTimestamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = sdf.format(date);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

