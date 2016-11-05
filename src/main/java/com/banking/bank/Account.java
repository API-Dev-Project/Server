package com.banking.bank;

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

    public long getAccountNumer() {
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

    public void lodge(Customer customer, long accountNumber, int sortCode , double amount) {
        Account account = customer.getAccount(accountNumber, sortCode);

        if (account != null && customer.isOwner(account)) {
            lodge(account, amount);
        } else {
            System.out.println("Customer isn't the owner of that account");
            //Throw customer !owner exception
        }
    }

    private void lodge(Account account, double amount) {
        System.out.println("Amount Successfully Lodged");
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
