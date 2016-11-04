package com.banking.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Account {

    private List<Transaction> transactions;
    private int sortCode;
    private long accountNumber;
    private double balance;

    public Account(int sortCode) {
        transactions = new ArrayList<>();
        balance = 0.0;
        setSortCode(sortCode);
        generateAccountNumber();
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

    public void lodge(Customer customer, double amount) {

    }

    public void withdraw() {

    }

    private void setSortCode(int sortCode) {
        if (sortCode > 9999 || sortCode < 999) {
            //Throw invalid sort code exception
        } else {
            this.sortCode = sortCode;
        }
    }

    private void generateAccountNumber() {
        Random rnd = new Random();
        accountNumber = 1000000 + rnd.nextInt(9999999);
    }
}
