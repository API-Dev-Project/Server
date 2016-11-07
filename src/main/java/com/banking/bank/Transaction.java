package com.banking.bank;

public class Transaction {

    enum Type {
        DEBIT,
        CREDIT
    }

    private Type type;
    private double amount;
    private double accountBalance;

    public Transaction() {
        amount = 0;
        accountBalance = 0;
    }

    public Transaction(Type type, double amount, double accountBalance) {
        this.type = type;
        this.amount = amount;
        this. accountBalance = accountBalance;
    }

    public Type getType() {
        return type;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public double getAmount() {
        return amount;
    }
}

