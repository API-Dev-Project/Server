package com.banking.bank;

import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficentFundsException;
import com.banking.bank.exception.InvalidAmountException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {

    private List<Transaction> transactions;
    private Customer owner;
    private int sortCode;
    private long accountNumber;
    private double balance;
    private int id;

    public Account() {
        transactions = new ArrayList<>();
        owner = new Customer();
        sortCode = 0;
        accountNumber = 0;
        balance = 0;
        id = 0;
    }

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

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getOwner() {
        return owner;
    }

    private void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSortCode(int sortCode) {
        if (sortCode > 9999 || sortCode < 999) {
            //Throw invalid sort code exception
        } else {
            this.sortCode = sortCode;
        }
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getSortCode() {
        return sortCode;
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

    public void transfer(Account toAccount, double amount) throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException{
        withdraw(owner, amount);
        toAccount.lodge(toAccount.getOwner(), amount);
    }

    protected void updateBalance(double amount) {
        balance = amount;
    }

    protected boolean canWithdraw(double amount) {
        return !(balance < 0) && !((balance - amount) < 0);
    }

    private void generateAccountNumber() {
        Random rnd = new Random();
        accountNumber = 1000000 + rnd.nextInt(9999999);
    }
}
