package com.banking.bank;

public class Transaction {

    enum Type {
        DEBIT,
        CREDIT
    }

    private Type type;
    private double amount;
    private double balanceAfter;
    private int id;

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
        this.id = 0;
    }

    public Type getType() {
        return type;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    protected void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public double getAmount() {
        return amount;
    }
}

