package com.banking.bank;

import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficentFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.mapping.MappingManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {

    private int id;
    private int sortCode;
    private long accountNumber;
    private double balance;
    private List<Transaction> transactions;
    private Customer owner;
    private MappingManager mappingManager;

    /*
     * This no args construction is used for the data access layer
     */
    public Account() {
        mappingManager = new MappingManager();
        transactions = new ArrayList<>();
        owner = new Customer();
        sortCode = 0;
        accountNumber = 0;
        balance = 0;
        id = 0;
    }

    /*
     * Constructor used to register a new account
     */
    public Account(int sortCode, Customer owner) {
        mappingManager = new MappingManager();
        transactions = new ArrayList<>();
        balance = 0.0;
        setSortCode(sortCode);
        setOwner(owner);
        generateAccountNumber();
        this.owner.addAccount(this);

        // Persist the new account
        mappingManager.getWriteMapping().addAccount(this);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        if (sortCode > 9999 || sortCode < 999) {
            //Throw invalid sort code exception
        } else {
            this.sortCode = sortCode;
        }
    }

    public void lodge(Customer customer, double amount) throws InvalidAmountException, CustomerNotOwnerException{
        if (amount > 0) {
            Transaction transaction = new Lodgement(customer, this, amount);
            addTransaction(transaction);
        } else {
            throw new InvalidAmountException();
        }
    }

    public void withdraw(Customer customer, double amount) throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException{
        if (amount > 0) {
            Transaction transaction = new Withdrawal(customer, this, amount);
            addTransaction(transaction);
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

    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        mappingManager.getWriteMapping().addTransaction(transaction);
    }

    private void generateAccountNumber() {
        Random rnd = new Random();
        accountNumber = 1000000 + rnd.nextInt(9999999);
    }
}
