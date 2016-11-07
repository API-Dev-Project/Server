package com.banking.bank;

import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficentFundsException;
import com.banking.bank.exception.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Account {

    private List<Transaction> transactions;
    private int sortCode;
    private long accountNumber;
    private double balance;
    private Customer owner;

    public Account(int sortCode, Customer owner) {
        transactions = new ArrayList<>();
        balance = 0.0;
        setSortCode(sortCode);
        setOwner(owner);
        generateAccountNumber();
        this.owner.addAccount(this);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getSortCode() {
        return sortCode;
    }

    public Customer getOwner() {
        return owner;
    }

    public void lodge(Customer customer, double amount) throws InvalidAmountException, CustomerNotOwnerException{
        if (amount > 0 && customer.isOwner(this)) {
            balance += amount;
            transactions.add(new Transaction(Transaction.Type.CREDIT, amount, balance));
        } else {
            if (amount < 0) {
                throw new InvalidAmountException();
            } else if (!customer.isOwner(this)) {
                throw new CustomerNotOwnerException();
            }
        }
    }

    private void setSortCode(int sortCode) {
        if (sortCode > 9999 || sortCode < 999) {
            //Throw invalid sort code exception
        } else {
            this.sortCode = sortCode;
        }
    }

    private void setOwner(Customer owner) {
        this.owner = owner;
    }

    private void generateAccountNumber() {
        Random rnd = new Random();
        accountNumber = 1000000 + rnd.nextInt(9999999);
    }
}
