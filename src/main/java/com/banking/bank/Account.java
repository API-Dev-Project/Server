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
        if (amount > 0) {
            Transaction transaction = new Lodgement(customer, this, amount);
            transactions.add(transaction);
        } else {
            throw new InvalidAmountException();
        }
    }

    public void withdraw(Customer customer, double amount) throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException{
        if (amount > 0) {
            Transaction transaction = new Withdrawal(customer, this, amount);
            transactions.add(transaction);
        } else {
            throw new InvalidAmountException();
        }
    }

    public void transfer() {

    }

    protected void updateBalance(double amount) {
        balance = amount;
    }

    protected boolean canWithdraw(double amount) {
        return !(balance < 0) && !((balance - amount) < 0);
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
